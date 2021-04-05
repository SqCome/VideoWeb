package com.lsq.search.contraller;


import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.entity.Video;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import com.lsq.search.service.VideoService;
import com.lsq.search.utils.FileUtils.DownloadFile;
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

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
public class FileController {

    @Autowired
    private User userUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private Video videoUtil;
    
    @Autowired
    private VideoService videoService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DownloadFile downloadFile;


    /**
     * 上传视频资源
     * @param file
     * @param username
     * @param title
     * @return
     */
    @PostMapping("/uploadVideo")
    public ResEntity uploadFile(@RequestParam("video") MultipartFile file,
                                @RequestParam("username") String username,
                                @RequestParam("title") String title) {
        
        videoUtil.setTitle(title);
        userUtil = userMapper.getUserByName(username);
        return videoService.uploadVideo(videoUtil,file,userUtil);
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file,"aa"))
//                .collect(Collectors.toList());
//    }


    /**
     * 根据视频id，下载视频资源
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/downloadVideo")
    public ResponseEntity<Resource> downloadVideo(@RequestParam(value = "id") int id,HttpServletRequest request) {
        // 加载文件资源
        Resource resource = fileStorageService.loadFileAsResource(videoService.getVideoUrl(id));
        //
        String contentType = downloadFile.getFileContentType(resource,request);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    /**
     * 加载头像
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/downloadIcon")
    public ResponseEntity<Resource> downloadIcon(@RequestParam(value = "id") int id,HttpServletRequest request) {
        // 加载文件资源
        Resource resource = fileStorageService.loadFileAsResource(userService.getIconUrl(id));
        //
        String contentType = downloadFile.getFileContentType(resource,request);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
