package com.lsq.search.serviceimpl;

import com.lsq.search.entity.User;
import com.lsq.search.exception.FileStorageException;
import com.lsq.search.exception.MyFileNotFoundException;
import com.lsq.search.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Classname FileStorageServiceImpl
 * @Description TODO
 * @Date 2021/3/28 14:38
 * @Auth LSQ
 */

@CrossOrigin
@Service
public class FileStorageServiceImpl implements FileStorageService {

        @Override
        public String storeFile(MultipartFile file,User user,String fileType) {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            try {
                // Check if the file's name contains invalid characters
                if(fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }

                // Copy file to the target location (Replacing existing file with the same name)
                Path targetLocation = Paths.get(user.getTargetLocation(),fileType).resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

                return fileName;
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

        @Override
        public Resource loadFileAsResource(String fileName,User user,String fileType) {
            try {
                Path filePath = Paths.get(user.getTargetLocation(),fileType).resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileNotFoundException("File not found " + fileName);
                }
            } catch (MalformedURLException ex) {
                throw new MyFileNotFoundException("File not found " + fileName, ex);
            }
        }
}
