package com.biggirlo.base.util.file;

import com.biggirlo.base.util.file.config.FileUploadConfig;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传工具类
 */
public class ImageClient {

    private static Logger logg= Logger.getLogger(ImageClient.class);

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            logg.info("文件为空");
            return null;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        String filePath  = FileUploadConfig.getInstance().getWholeUploadDir() ;
        // 解决中文问题，liunx下中文路径，图片显示问题
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        file.transferTo(dest);

        return FileUploadConfig.getInstance().getHostPost() + "/" + FileUploadConfig.getInstance().getUploadDir()+ "/" +  fileName;
    }

}
