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
 * 数据库表名称：gw_column_table
 * </pre>
 */
@Table(name="GW_COLUMN_TABLE")
public class ColumnTable extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 
     * 数据库字段信息:code VARCHAR(50)
     */
    private String code;

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
     * 数据库字段信息:is_show_in_top TINYINT(3)
     */
    private Integer isShowInTop;

    /**
     * 
     * 
     * 数据库字段信息:logo VARCHAR(1024)
     */
    private String logo;

    /**
     * 
     * 
     * 数据库字段信息:present VARCHAR(1024)
     */
    private String present;

    /**
     * 
     * 
     * 数据库字段信息:name VARCHAR(50)
     */
    private String name;

    /**
     * 添加栏目链接
     */
    private String url;

    /**
     * 
     * 
     * 数据库字段信息:concent VARCHAR(1024)
     */
    private String concent;

    @Transient
    private List<ClassifyTable> classifyTables = new ArrayList<>();

    public ColumnTable() {
    }	
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	
    public Long getSort() {
        return this.sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
	
    public Integer getIsShowInTop() {
        return this.isShowInTop;
    }

    public void setIsShowInTop(Integer isShowInTop) {
        this.isShowInTop = isShowInTop;
    }
	
    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
	
    public String getPresent() {
        return this.present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
	
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
    public String getConcent() {
        return this.concent;
    }

    public void setConcent(String concent) {
        this.concent = concent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ClassifyTable> getClassifyTables() {
        return classifyTables;
    }

    public void setClassifyTables(List<ClassifyTable> classifyTables) {
        this.classifyTables = classifyTables;
    }
}