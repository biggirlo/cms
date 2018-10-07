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


/**
 * <pre>
 * 实体类
 * 数据库表名称：gw_content_table
 * </pre>
 */
@Table(name="GW_CONTENT_TABLE")
public class ContentTable extends BaseModel {
    private static final long serialVersionUID = 1L;

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
     * 
     * 
     * 数据库字段信息:logo VARCHAR(1024)
     */
    private String logo;

    /**
     * 
     * 
     * 数据库字段信息:covers_img VARCHAR(1024)
     */
    private String coversImg;

    /**
     * 
     * 
     * 数据库字段信息:title VARCHAR(50)
     */
    private String title;

    /**
     * 
     * 
     * 数据库字段信息:code VARCHAR(50)
     */
    private String code;

    /**
     * 
     * 
     * 数据库字段信息:classify_id INT(10)
     */
    private Long classifyId;

    /**
     * 
     * 
     * 数据库字段信息:is_show TINYINT(3)
     */
    private Integer isShow;

    /**
     * 
     * 
     * 数据库字段信息:less_present TEXT(65535)
     */
    private String lessPresent;

    /**
     * 
     * 
     * 数据库字段信息:full_present TEXT(65535)
     */
    private String fullPresent;

    /**
     * 
     * 
     * 数据库字段信息:content VARCHAR(1024)
     */
    private String content;

    private String url;

    private String author;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ContentTable() {
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
	
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
	
    public String getCoversImg() {
        return this.coversImg;
    }

    public void setCoversImg(String coversImg) {
        this.coversImg = coversImg;
    }
	
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
	
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getClassifyId() {
        return this.classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getIsShow() {
        return this.isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
	
    public String getLessPresent() {
        return this.lessPresent;
    }

    public void setLessPresent(String lessPresent) {
        this.lessPresent = lessPresent;
    }
	
    public String getFullPresent() {
        return this.fullPresent;
    }

    public void setFullPresent(String fullPresent) {
        this.fullPresent = fullPresent;
    }
	
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}