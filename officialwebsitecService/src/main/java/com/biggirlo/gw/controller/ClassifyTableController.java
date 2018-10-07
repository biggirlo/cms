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
import org.apache.log4j.Logger;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import org.springframework.web.bind.annotation.*;

import com.biggirlo.gw.model.ClassifyTable;
import com.biggirlo.gw.service.ClassifyTableService;
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
@RequestMapping(value = "/gw/classify")
public class ClassifyTableController {
    
    Logger logg= Logger.getLogger(ClassifyTableController.class);

    @Resource(name = "classifyTableService")
    private ClassifyTableService classifyTableService;

    @Resource(name = "officialWebsitePageService")
    private OfficialWebsitePageService officialWebsitePageService;

    @RequestMapping("/{id}")
    public Restult get(@PathVariable("id") Long id) {
        Restult rs = new Restult();
        try{
            ClassifyTable  classifyTable  = classifyTableService.selectById(id);
            rs.setCodeData(Code.SUCCESS,classifyTable);
            logg.info("读取id:" + id + "的分类");
        }catch (Exception e){
            e.printStackTrace();
            rs.setCode(Code.SYSTEM_ERROR);
            logg.info("读取id:" + id + "的分类异常");
        }
        return rs ;
    }

    /**
     * 获取列表
     * @param param
     * @param classifyTable
     * @return
     */
    @RequestMapping(value = "/dataTable/list",method = RequestMethod.PUT)
    public Object getMenus(DataTablesParam param, ClassifyTable classifyTable){
        logg.info("获取分类列表");
        return classifyTableService.selectByDatetable(param,classifyTable);
    }

    /**
     * 获取整个jstree树结构
     * @return
     */
    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    public Restult getAllJsTree(){
        Restult rs = new Restult();
        try {
            rs.setCodeData(Code.SUCCESS,classifyTableService.getJsTreeList());
            logg.info("读取分类树");
        }catch (Exception e){
            e.printStackTrace();
            rs.setCode(Code.SYSTEM_ERROR);
            logg.info("读取分类树异常");
        }
        return rs ;
    }

    /**
     * 保存数据
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public Restult save(ClassifyTable classifyTable , @RequestParam(name = "coversImgFile",required = false) MultipartFile coversImgFile, @RequestParam(name = "logoFile",required = false) MultipartFile logoFile){
        try{
            logg.info("保存栏目数据");
            return classifyTableService.save(classifyTable,coversImgFile,logoFile);
        }catch (Exception e){
            e.printStackTrace();
            logg.info("保存栏目数据异常");
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
            int res = classifyTableService.deletes(ids);
            if(res > 0) officialWebsitePageService.initTemp();
            return new Restult(Code.SUCCESS,res);
        }catch (Exception e){
            e.printStackTrace();
            return new Restult(Code.SYSTEM_ERROR);
        }
    }
}