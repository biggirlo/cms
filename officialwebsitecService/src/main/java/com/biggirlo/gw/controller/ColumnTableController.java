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
import com.biggirlo.system.model.SysUser;
import com.biggirlo.system.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import com.biggirlo.gw.model.ColumnTable;
import com.biggirlo.gw.service.ColumnTableService;
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
@RequestMapping(value = "/gw/column")
public class ColumnTableController {
    
    Logger logg= Logger.getLogger(ColumnTableController.class);

    @Resource(name = "columnTableService")
    private ColumnTableService columnTableService;

    @Resource(name ="officialWebsitePageService" )
    private OfficialWebsitePageService officialWebsitePageService;

    /**
     * 获取单个资源
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Restult get(@PathVariable("id") Long id) {
        Restult rs = new Restult();
        try{
            ColumnTable  columnTable  = columnTableService.selectById(id);
            rs.setCodeData(Code.SUCCESS,columnTable);

            logg.info("获取id：" + id + "的栏目");

        }catch (Exception e){
            e.printStackTrace();
            logg.info("获取id：" + id + "的栏目移除");
            rs.setCode(Code.SYSTEM_ERROR);
        }
        return rs ;
    }


    /**
     * 获取列表
     * @param param
     * @param columnTable
     * @return
     */
    @RequestMapping(value = "/dataTable/list",method = RequestMethod.PUT)
    public Object getMenus(DataTablesParam param, ColumnTable columnTable){
        logg.info("获取栏目列表");
        return columnTableService.selectByDatetable(param,columnTable);
    }

    /**
     * 保存数据
     * @param columnTable
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public Restult save(ColumnTable columnTable, @RequestParam(name = "logoFile",required = false) MultipartFile logoFile){
        try{
            logg.info("保存栏目数据");
            return columnTableService.save(columnTable,logoFile);
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
            int res = columnTableService.deletes(ids);
            if(res > 0) officialWebsitePageService.initTemp();
            return new Restult(Code.SUCCESS,res);
        }catch (Exception e){
            e.printStackTrace();
            return new Restult(Code.SYSTEM_ERROR);
        }
    }
}