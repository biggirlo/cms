package com.biggirlo.base.util.file.controller;

import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.HtmlClient;
import com.biggirlo.base.util.Restult;
import com.biggirlo.base.util.file.ImageClient;
import com.biggirlo.base.util.file.config.FileUploadConfig;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 */
@RestController
public class FileUploadController {

    private static Logger logg= Logger.getLogger(FileUploadController.class);

    private final ResourceLoader resourceLoader;


    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 读取文件
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ofwsc/{filename:.+}")
    public ResponseEntity<?> getAnonFile(@PathVariable String filename) {
        try {
            Resource resource = resourceLoader.getResource("file:" + "gw\\" + filename );
           if(resource.exists())
                return ResponseEntity.ok(resource);
           else
               return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 读取文件(需要权限访问)
     * @param filename
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/authc/file/{filename:.+}")
    public ResponseEntity<?> getAuthcFile(@PathVariable String filename) {
        try {
            Resource resource = resourceLoader.getResource("file:" + FileUploadConfig.getInstance().getUploadDir() + filename );
            if(resource.exists())
                return ResponseEntity.ok(resource);
            else
                return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 上传图片，ueditor
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/iamge/ueditor")
    public Restult uploadUeditorImage(@RequestParam(name = "uImage",required = false) MultipartFile image){
        try{
            return new Restult(Code.SUCCESS, ImageClient.upload(image));
        }catch (Exception e){
            logg.info("上传uditor图片失败");
            e.printStackTrace();
            return  new Restult(Code.SYSTEM_ERROR);
        }
    }


    /**
     * ueditor 初始化
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/ueditor")
    public Boolean ueditorInit(){
        return false;
    }

    /**
     * 上传html
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "/html")
    public Restult uploadHtml(@RequestParam(name = "html",required = false) MultipartFile html){
        try{
            return new Restult(Code.SUCCESS, HtmlClient.upload(html));
        }catch (Exception e){
            logg.info("上传html");
            e.printStackTrace();
            return  new Restult(Code.SYSTEM_ERROR);
        }
    }
}
