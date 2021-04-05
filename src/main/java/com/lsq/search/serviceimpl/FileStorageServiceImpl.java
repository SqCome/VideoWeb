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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Classname FileStorageServiceImpl
 * @Description TODO
 * @Date 2021/3/28 14:38
 * @Auth LSQ
 */

@CrossOrigin
@Service
public class FileStorageServiceImpl implements FileStorageService {


    /**
     * 存储文件
     * @param file
     * @param user
     * @param fileType
     * @return
     */
    @Override
    public String storeFile(MultipartFile file,User user,String fileType) {
            // 为文件指定唯一的名字
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Random random = new Random();
            String unique = random.nextInt(89999 + 10000) + simpleDateFormat.format(new Date());
            String fileName = unique+".mp4";

            try {
                // 将文件存储在相应路径上，如果有重名文件将覆盖
                Path targetLocation = Paths.get(user.getTargetLocation(),fileType).resolve(fileName);
                String fileUrl =  targetLocation.toString();
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return fileUrl;
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
        }

    /**
     * 加载文件资源
     * @param url
     * @return
     */
    @Override
        public Resource loadFileAsResource(String url) {
            try {
                Path filePath = Paths.get(url).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MyFileNotFoundException("File not found " );
                }
            } catch (MalformedURLException ex) {
                throw new MyFileNotFoundException("File not found " , ex);
            }
        }
}
