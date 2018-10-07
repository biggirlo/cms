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

package com.biggirlo.gw.mapper;

import com.biggirlo.base.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.biggirlo.gw.model.ContentTable;

import java.util.List;

/**
 * <pre>
 * 数据访问接口
 * </pre>
 */
@Mapper
public interface ContentTableMapper extends BaseMapper<ContentTable> {

    /**
     * 获取只包含简单的内容
     * @param obj
     * @return
     */
    List<ContentTable> selectLess(ContentTable obj);

}