package com.lsq.search.utils.FileUtils;

import com.lsq.search.contraller.UserContraller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Classname downloadFile
 * @Description TODO
 * @Date 2021/3/31 18:26
 * @Auth LSQ
 */

@Component
public class DownloadFile {
    private static final Logger logger = LoggerFactory.getLogger(UserContraller.class);


    public String getFileContentType(Resource resource, HttpServletRequest request){
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

        return contentType;
    }
}
