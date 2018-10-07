package com.biggirlo.gw.controller;

import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import com.biggirlo.gw.ChangeHost;
import com.biggirlo.gw.model.ClassifyTable;
import com.biggirlo.gw.model.ColumnTable;
import com.biggirlo.gw.model.ContentTable;
import com.biggirlo.gw.model.MessageBoard;
import com.biggirlo.gw.service.ContentTableService;
import com.biggirlo.gw.service.MessageBoardService;
import com.biggirlo.system.util.UserLoginUtils;
import org.apache.log4j.Logger;
import com.biggirlo.gw.service.OfficialWebsitePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 *
 * 官网接口
 *
 */

@RestController
@RequestMapping("/ofwsc")
public class OfficialWebsitePageController {

    Logger logg= Logger.getLogger(OfficialWebsitePageController.class);

    @Autowired
    private OfficialWebsitePageService officialWebsitePageService;

    @Autowired
    private MessageBoardService messageBoardService;



    /**
     * 初始化首页
     * @return
     */
    @RequestMapping( value = "/init",method = RequestMethod.GET)
    public Restult init(){
        Restult restult ;
        logg.info("初始化官网信息");
        try{
            restult =new Restult(Code.SUCCESS,officialWebsitePageService.getInitInfo());
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("初始化官网信息异常");
            e.printStackTrace();

        }
        return restult;
    }

    /**
     * 获取栏目列表
     * @return
     */
    @RequestMapping( value = "/colums",method = RequestMethod.GET)
    public Restult getColums(){
        Restult restult ;
        logg.info("获取栏目列表信息");
        try{
            restult =new Restult(Code.SUCCESS,officialWebsitePageService.getColums());
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("获取栏目列表异常");
            e.printStackTrace();

        }
        return restult;
    }

    /**
     * 根据栏目编码获取栏目内容
     * @param code
     * @return
     */
    @RequestMapping(value = "/column/{code}",method = RequestMethod.GET)
    public Restult getColumnByCode(@PathVariable("code")String code){
        Restult restult ;
        logg.info("根据code获取栏目");
        try{
            ColumnTable columnTable = new ColumnTable();
            columnTable.setCode(code);
            return  officialWebsitePageService.getColumnInfo(columnTable);
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("根据code获取栏目异常");
            e.printStackTrace();

        }
        return restult;
    }

    /**
     * 根据栏目编码获取栏目内容
     * @param code
     * @return
     */
    @RequestMapping(value = "/classify/{code}",method = RequestMethod.GET)
    public Restult getClassfyTableByCode(@PathVariable("code")String code){
        Restult restult ;
        logg.info("根据code获取分类");
        try{
            ClassifyTable classify = new ClassifyTable();
            classify.setCode(code);
            return  officialWebsitePageService.getClassifyInfo(classify);
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("根据code获取分类异常");
            e.printStackTrace();

        }
        return restult;
    }

    /**
     * 根据栏目编码获取栏目内容
     * @param code
     * @return
     */
    @RequestMapping(value = "/content/{code}",method = RequestMethod.GET)
    public Restult getContentTableByCode(@PathVariable("code")String code){
        Restult restult ;
        logg.info("根据code获取内容");
        try{
            ContentTable contentTable = new ContentTable();
            contentTable.setCode(code);
            return  officialWebsitePageService.getontentCInfo(contentTable);
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("根据code获取内容异常");
            e.printStackTrace();

        }
        return restult;
    }

    /**
     * 留言板信息
     * @return
     */
    @RequestMapping(value = "/msg/info",method = RequestMethod.POST)
    public Restult saveMessage(@RequestBody MessageBoard msg){
        Restult restult ;
        logg.info("保存留言信息");
        try{
            msg.setCreateTime(new Date());
            return  new Restult(Code.SUCCESS,messageBoardService.insert(msg));
        }catch (Exception e){
            restult= new Restult(Code.SYSTEM_ERROR);
            logg.info("根据code获取内容异常");
            e.printStackTrace();
        }
        return restult;
    }

    @PostMapping("/change")
    public Restult changeHost(@RequestBody ChangeHost changeHost){
        officialWebsitePageService.changeHost(changeHost);

        return new Restult(Code.SUCCESS);

    }

    @GetMapping("/test")
    public Restult test(){
        return new Restult(Code.SUCCESS,"test");

    }

}
