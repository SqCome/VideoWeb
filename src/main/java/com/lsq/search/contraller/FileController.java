package com.lsq.search.contraller;


import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import com.lsq.search.utils.UploadFileResponse;
import com.lsq.search.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private User user;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserMapper userMapper;


    private UploadFileResponse uploadFileResponse;

//    @PostMapping("/uploadVideo")
//    public UploadFileResponse uploadFile(@RequestParam("video") MultipartFile file,@RequestParam("userName") String username) {
//
//        String targetLocation = userService.getLocation(username);
//
//        String fileName = fileStorageService.storeFile(file,targetLocation);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(fileName)
//                .toUriString();
//        uploadFileResponse = new UploadFileResponse();
//        uploadFileResponse.setAll(fileName,fileDownloadUri,
//                file.getContentType(), file.getSize());
//
//        return uploadFileResponse;
//    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file,"aa"))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,@RequestParam(value = "userName") String userName , HttpServletRequest request) {
        user = userMapper.getUserByName(userName);
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName,user,"Video");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
