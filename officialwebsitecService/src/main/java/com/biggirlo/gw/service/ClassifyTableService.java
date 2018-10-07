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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biggirlo.base.service.BaseService;
import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import com.biggirlo.base.util.file.ImageClient;
import com.biggirlo.base.util.file.config.FileUploadConfig;
import com.biggirlo.system.jopo.jstree.TreeNode;
import com.biggirlo.system.util.UserLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biggirlo.gw.model.ClassifyTable;
import com.biggirlo.gw.mapper.ClassifyTableMapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * <pre>
 * 业务类
 * </pre>
 */
@Service("classifyTableService")
public class ClassifyTableService extends BaseService<ClassifyTable, Long> {

    @Autowired
    private  ClassifyTableMapper classifyTableMapper;


    @Autowired
    private OfficialWebsitePageService officialWebsitePageService;

    /**
     * 获取所有的分类，以树解构的方式
     * @return
     */
    public List<TreeNode> getJsTreeList() {
        //树结构
        List<TreeNode> nodes = new ArrayList<>();
        //设置根节点
        TreeNode root = new TreeNode();
        root.setId("0");
        root.setText("官网管理");
        root.setParent("#");
        nodes.add(root);

       nodes.addAll(classifyTableMapper.searchTreeNodes());

        return nodes;
    }

    /**
     * 保存
     * @param classifyTable
     * @param coversImgFile
     *@param logoFile @return
     */
    public Restult save(ClassifyTable classifyTable, MultipartFile coversImgFile, MultipartFile logoFile) throws IOException {
        Restult rs ;
        ClassifyTable classifyTableTemp = new ClassifyTable();
        classifyTableTemp.setCode(classifyTable.getCode());
        classifyTableTemp = selectOne(classifyTableTemp);
        if(classifyTable.getId() != null && (classifyTableTemp == null || classifyTableTemp.getId() ==  classifyTable.getId()) ) {
            classifyTable.setUpdateTime(new Date());
            classifyTable.setUpdateBy(UserLoginUtils.getCurrentUserId());
            //上传图片
            this.uploadImage(classifyTable,coversImgFile,logoFile);
            rs = new Restult(Code.SUCCESS, update(classifyTable));
            officialWebsitePageService.initTemp();
        }else if (classifyTable.getId() == null && classifyTableTemp == null) {
            classifyTable.setCreateTime(new Date());
            classifyTable.setCreateBy(UserLoginUtils.getCurrentUserId());
            //上传图片
            this.uploadImage(classifyTable,coversImgFile,logoFile);
            rs = new Restult(Code.SUCCESS, insert(classifyTable));
            officialWebsitePageService.initTemp();
        } else
            rs = new Restult(Code.REPEAT_KEYWORK.value(),"重复的分类编码");
        return rs;
    }

    /**
     * 上传图片
     * @param classifyTable
     * @param coversImgFile
     * @param logoFile
     */
    private void uploadImage(ClassifyTable classifyTable, MultipartFile coversImgFile, MultipartFile logoFile) throws IOException {

       /* if(!coversImgFile.isEmpty() )
            classifyTable.setCoversImg(ImageClient.upload(coversImgFile));*/

        if(!logoFile.isEmpty())
            classifyTable.setLogo(ImageClient.upload(logoFile));
    }
}