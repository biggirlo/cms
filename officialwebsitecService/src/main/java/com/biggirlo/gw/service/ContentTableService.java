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

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.biggirlo.base.service.BaseService;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.DataTablesParam;
import com.biggirlo.base.util.Restult;
import com.biggirlo.base.util.file.ImageClient;
import com.biggirlo.base.util.file.config.FileUploadConfig;
import com.biggirlo.system.util.UserLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biggirlo.gw.model.ContentTable;
import com.biggirlo.gw.mapper.ContentTableMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 业务类
 * </pre>
 */
@Service("contentTableService")
public class ContentTableService extends BaseService<ContentTable, Long> {

    @Autowired
    private  ContentTableMapper contentTableMapper;

    @Autowired
    private OfficialWebsitePageService officialWebsitePageService;
    /**
     * 返回datables数据结果
     * @param param
     */
    public DataTablesParam selectByDatetable(DataTablesParam param, ContentTable obj){

        //启动分页
        PageHelper.startPage(param.getStart(), param.getLength());
        Map<String,Object> result = new HashMap<>();
        PageInfo<ContentTable> page = new PageInfo<ContentTable>(contentTableMapper.selectLess(obj));
        param.setRecordsTotal(page.getTotal());
        param.setRecordsFiltered(page.getTotal());
        param.setData( page.getList());
        return param;
    }


    /**
     * 保存
     * @param contentTable
     * @param logo
     * @param coversImg
     * @return
     * @throws IOException
     */
    public Restult save(ContentTable contentTable, MultipartFile logo, MultipartFile coversImg) throws IOException {

        Restult rs ;
        ContentTable contentTableTemp = new ContentTable();
        contentTableTemp.setCode(contentTable.getCode());
        contentTableTemp = selectOne(contentTableTemp);
        if(contentTable.getId() != null && (contentTableTemp == null || contentTableTemp.getId() ==  contentTable.getId()) ) {
            contentTable.setUpdateTime(new Date());
            contentTable.setUpdateBy(UserLoginUtils.getCurrentUserId());
            //上传图片
            this.uploadImage(contentTable,coversImg,logo);
            //this.creatHtmlFile(contentTable.getFullPresent(),"werw");
            rs = new Restult(Code.SUCCESS, update(contentTable));
            officialWebsitePageService.initTemp();
        }else if (contentTable.getId() == null && contentTableTemp == null) {
            contentTable.setCreateTime(new Date());
            contentTable.setCreateBy(UserLoginUtils.getCurrentUserId());
            //上传图片
            this.uploadImage(contentTable,coversImg,logo);
            rs = new Restult(Code.SUCCESS, insert(contentTable));
            officialWebsitePageService.initTemp();
        } else
            rs = new Restult(Code.REPEAT_KEYWORK.value(),"重复的内容编码");
        return rs;
    }

    /**
     * 上传图片
     * @param classifyTable
     * @param coversImgFile
     * @param logoFile
     */
    private void uploadImage(ContentTable classifyTable, MultipartFile coversImgFile, MultipartFile logoFile) throws IOException {
        if(!coversImgFile.isEmpty() )
            classifyTable.setCoversImg(ImageClient.upload(coversImgFile));

        if(!logoFile.isEmpty())
            classifyTable.setLogo( ImageClient.upload(logoFile));
    }


}