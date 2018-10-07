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

import javax.persistence.Table;


/**
 * <pre>
 * 实体类
 * 数据库表名称：gw_message_board
 * </pre>
 */
@Table(name="GW_MESSAGE_BOARD")
public class MessageBoard extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 
     * 
     * 数据库字段信息:moble VARCHAR(11)
     */
    private String moble;

    /**
     * 
     * 
     * 数据库字段信息:name VARCHAR(50)
     */
    private String name;

    /**
     * 
     * 
     * 数据库字段信息:context LONGTEXT(2147483647)
     */
    private String context;

    /**
     * 企业
     */
    private String company;

    private String emali;

    public String getMoble() {
        return this.moble;
    }

    public void setMoble(String moble) {
        this.moble = moble;
    }
	
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
    }
}