package com.biggirlo.base.util.file.config;

import com.biggirlo.base.util.YamlLoadUtil;
import org.apache.log4j.Logger;
import org.springframework.core.env.PropertySource;

import java.io.File;

/**
 * 上传文件配置
 *
 */
public class FileUploadConfig {
    Logger logg= Logger.getLogger(FileUploadConfig.class);

    private static FileUploadConfig config = new FileUploadConfig();

    private  FileUploadConfig()  {
        PropertySource propertySource = YamlLoadUtil.getYamlLoad("classpath:application.yml");
        logg.info("读取application.ymlcorsConfig配置");

        this.targetDir =(propertySource.getProperty("file.targetDir") == null ? new File( FileUploadConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParent() + "\\"
                : propertySource.getProperty("file.targetDir").toString()) ;
        logg.info("读取targetDir配置->" + this.targetDir);

        this.uploadDir = (propertySource.getProperty("file.uploadDir") == null ?"file" : propertySource.getProperty("file.uploadDir").toString()) ;
        logg.info("读取uploadDir配置->" + this.uploadDir);

        this.hostPost = (propertySource.getProperty("file.hostPost") == null ?"file" : propertySource.getProperty("file.hostPost").toString()) ;
        logg.info("读取hostPost配置->" + this.hostPost);

        this.htmldir = (propertySource.getProperty("file.htmldir") == null ?"html" : propertySource.getProperty("file.htmldir").toString()) ;
        logg.info("读取htmldir配置->" + this.htmldir);

        this.htmlHostPost = (propertySource.getProperty("file.htmlHostPost") == null ?"html" : propertySource.getProperty("file.htmlHostPost").toString()) ;
        logg.info("读取htmlHostPost配置->" + this.htmlHostPost);

        this.wholeUploadDir = this.targetDir +  this.uploadDir + File.separator;

    }

    public static synchronized FileUploadConfig getInstance(){
        return config;
    }

    //上传项目路径，
    private String uploadDir;

    //目标地址
    private String targetDir;

    //完整地址
    private String wholeUploadDir;

    //项目的访问地址
    private String hostPost;

    //上传html的路径
    private String htmldir;

    //访问html的路径
    private String htmlHostPost;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    public String getWholeUploadDir() {
        return wholeUploadDir;
    }

    public void setWholeUploadDir(String wholeUploadDir) {
        this.wholeUploadDir = wholeUploadDir;
    }

    public String getHostPost() {
        return hostPost;
    }

    public void setHostPost(String hostPost) {
        this.hostPost = hostPost;
    }

    public String getHtmldir() {
        return htmldir;
    }

    public void setHtmldir(String htmldir) {
        this.htmldir = htmldir;
    }

    public String getHtmlHostPost() {
        return htmlHostPost;
    }

    public void setHtmlHostPost(String htmlHostPost) {
        this.htmlHostPost = htmlHostPost;
    }
}
