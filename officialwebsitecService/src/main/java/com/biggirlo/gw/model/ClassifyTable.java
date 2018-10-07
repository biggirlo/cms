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

package com.biggirlo.gw.model;

import com.biggirlo.base.model.BaseModel;

import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * 实体类
 * 数据库表名称：gw_classify_table
 * </pre>
 */
@Table(name="GW_CLASSIFY_TABLE")
public class ClassifyTable extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 
     * 数据库字段信息:covers_img VARCHAR(1024)
     */
    private String coversImg;

    /**
     * 
     * 
     * 数据库字段信息:logo VARCHAR(1024)
     */
    private String logo;

    /**
     * 
     * 
     * 数据库字段信息:name VARCHAR(50)
     */
    private String name;

    /**
     * 
     * 
     * 数据库字段信息:sort INT(10)
     */
    @OrderBy
    private Long sort;

    /**
     * 字段名称：一级分类id为0
     * 
     * 数据库字段信息:parent_id INT(10)
     */
    private Long parentId;

    /**
     * 
     * 
     * 数据库字段信息:column_id INT(10)
     */
    private Long columnId;

    /**
     * 
     * 
     * 数据库字段信息:content VARCHAR(1024)
     */
    private String content;

    /**
     * 
     * 
     * 数据库字段信息:present VARCHAR(1024)
     */
    private String present;

    /**
     * 
     * 
     * 数据库字段信息:code VARCHAR(50)
     */
    private String code;

    //子类
    @Transient
    private List<ClassifyTable> classifese = new ArrayList<>();

    //所有内容
    @Transient
    private List<ContentTable> contents = new ArrayList<>();

    public ClassifyTable() {
    }	
    public String getCoversImg() {
        return this.coversImg;
    }

    public void setCoversImg(String coversImg) {
        this.coversImg = coversImg;
    }
	
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
	
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
    public Long getSort() {
        return this.sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
	
    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
	
    public Long getColumnId() {
        return this.columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }
	
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	
    public String getPresent() {
        return this.present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
	
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<ClassifyTable> getClassifese() {
        return classifese;
    }

    public void setClassifese(List<ClassifyTable> classifese) {
        this.classifese = classifese;
    }

    public List<ContentTable> getContents() {
        return contents;
    }

    public void setContents(List<ContentTable> contents) {
        this.contents = contents;
    }
}