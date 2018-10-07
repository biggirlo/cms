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
import org.apache.log4j.Logger;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import com.biggirlo.system.model.SysUser;
import com.biggirlo.system.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import com.biggirlo.gw.model.MessageBoard;
import com.biggirlo.gw.service.MessageBoardService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <pre>
 * 表现层控制类
 * </pre>
 */
//@RefreshScope
@RestController
@RequestMapping(value = "/gw/message")
public class MessageBoardController {
    
    Logger logg= Logger.getLogger(MessageBoardController.class);

    @Resource(name = "messageBoardService")
    private MessageBoardService messageBoardService;

    @RequestMapping("/{id}")
    public Restult get(@PathVariable("id") Long id) {
        Restult rs = new Restult();
        try{
            MessageBoard  messageBoard  = messageBoardService.selectById(id);
            rs.setCodeData(Code.SUCCESS,messageBoard);
        }catch (Exception e){
            e.printStackTrace();
            rs.setCode(Code.SYSTEM_ERROR);
        }
        return rs ;
    }

    /**
     * 获取列表
     * @param param
     * @param messageBoard
     * @return
     */
    @RequestMapping(value = "/dataTable/list",method = RequestMethod.PUT)
    public Object getMenus(DataTablesParam param, MessageBoard messageBoard){
        logg.info("获取内容列表");
        return messageBoardService.selectByDatetable(param,messageBoard);
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
            int res = messageBoardService.deletes(ids);
            return new Restult(Code.SUCCESS,res);
        }catch (Exception e){
            e.printStackTrace();
            return new Restult(Code.SYSTEM_ERROR);
        }
    }
}