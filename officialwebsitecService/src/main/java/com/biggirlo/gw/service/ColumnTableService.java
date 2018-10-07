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

package com.biggirlo.gw.service;

import java.io.IOException;
import java.util.Date;

import com.biggirlo.base.service.BaseService;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import com.biggirlo.base.util.file.ImageClient;
import com.biggirlo.base.util.file.config.FileUploadConfig;
import com.biggirlo.gw.controller.ColumnTableController;
import com.biggirlo.system.util.UserLoginUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biggirlo.gw.model.ColumnTable;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 业务类
 * </pre>
 */
@Service("columnTableService")
public class ColumnTableService extends BaseService<ColumnTable, Long> {

    Logger logg= Logger.getLogger(ColumnTableController.class);

    @Autowired
    private OfficialWebsitePageService officialWebsitePageService;
    /**
     * 保存
     * @param columnTable
     * @param logoFile
     * @return
     */
    public Restult save(ColumnTable columnTable, MultipartFile logoFile) throws IOException {

        Restult rs ;
        ColumnTable columnTableTemp = new ColumnTable();
        columnTableTemp.setCode(columnTable.getCode());
        columnTableTemp = selectOne(columnTableTemp);
        if(columnTable.getId() != null && (columnTableTemp == null || columnTableTemp.getId() ==  columnTable.getId()) ) {
            columnTable.setUpdateTime(new Date());
            columnTable.setUpdateBy(UserLoginUtils.getCurrentUserId());
            //this.uploadImage(columnTable,logoFile);
            rs = new Restult(Code.SUCCESS, update(columnTable));
            officialWebsitePageService.initTemp();
        }else if (columnTable.getId() == null && columnTableTemp == null) {
            columnTable.setCreateTime(new Date());
            columnTable.setCreateBy(UserLoginUtils.getCurrentUserId());
            //this.uploadImage(columnTable,logoFile);
            rs = new Restult(Code.SUCCESS, insert(columnTable));
            officialWebsitePageService.initTemp();
        } else
            rs = new Restult(Code.REPEAT_KEYWORK.value(),"重复的栏目编码");
        return rs;
    }

    /**
     * 上传图片
     * @param classifyTable
     * @param logoFile
     */
    private void uploadImage(ColumnTable classifyTable, MultipartFile logoFile) throws IOException {

        if(!logoFile.isEmpty())
            classifyTable.setLogo(ImageClient.upload(logoFile));
    }
}