/**************************************************************************/
/**************************************************************************/
/*                                                                        */
/* Biggirlos                                       						  */
/* ************************************************************************/
/* 该项目仅用于学习
/* 有任何疑问或者建议请致邮件于 email:645614025@qq.com
/* **********************************************************
/* **********************************************************
*
/**************************************************************************/

/**
  * <pre>
  * 作   者：wangyanxin
  * 创建日期：2017-11-29
  * </pre>
  */

package com.biggirlo.gw.controller;

import com.biggirlo.base.util.DataTablesParam;
import com.biggirlo.gw.service.OfficialWebsitePageService;
import com.biggirlo.system.model.SysHandle;
import org.apache.log4j.Logger;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import org.springframework.web.bind.annotation.*;

import com.biggirlo.gw.model.ContentTable;
import com.biggirlo.gw.service.ContentTableService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <pre>
 * 表现层控制类
 * </pre>
 */
//@RefreshScope
@RestController
@RequestMapping(value = "/gw/content")
public class ContentTableController {
    
    Logger logg= Logger.getLogger(ContentTableController.class);

    @Resource(name = "contentTableService")
    private ContentTableService contentTableService;

    @Resource(name = "officialWebsitePageService")
    private OfficialWebsitePageService officialWebsitePageService;

    @RequestMapping("/{id}")
    public Restult get(@PathVariable("id") Long id) {
        Restult rs = new Restult();
        try{
            ContentTable  contentTable  = contentTableService.selectById(id);
            rs.setCodeData(Code.SUCCESS,contentTable);
        }catch (Exception e){
            e.printStackTrace();
            rs.setCode(Code.SYSTEM_ERROR);
        }
        return rs ;
    }

    /**
     * 获取列表
     * @param param
     * @param contentTable
     * @return
     */
    @RequestMapping(value = "/dataTable/list",method = RequestMethod.PUT)
    public Object getMenus(DataTablesParam param, ContentTable contentTable){
        logg.info("获取内容列表");
        return contentTableService.selectByDatetable(param,contentTable);
    }

    /**
     * 保存数据
     * @param contentTable
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public Restult save( ContentTable contentTable, @RequestParam(name = "coversImgFile",required = false) MultipartFile coversImgFile, @RequestParam(name = "logoFile",required = false) MultipartFile logoFile){
        try{
            logg.info("保存内容数据");
            return contentTableService.save(contentTable,logoFile,coversImgFile);
        }catch (Exception e){
            e.printStackTrace();
            logg.info("保存内容数据异常");
            return new Restult(Code.SYSTEM_ERROR);
        }
    }

    /**
     * 删除
     * @param map
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.DELETE)
    public Object delete(@RequestBody Map<String ,Long[]> map){
        try{
            Long[] ids = map.get("ids");
            int res = contentTableService.deletes(ids);
            if(res > 0 )officialWebsitePageService.initTemp();
            return new Restult(Code.SUCCESS,res);
        }catch (Exception e){
            e.printStackTrace();
            return new Restult(Code.SYSTEM_ERROR);
        }
    }
}