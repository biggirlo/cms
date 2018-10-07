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
import com.biggirlo.system.jopo.jstree.TreeNode;
import org.apache.ibatis.annotations.Mapper;
import com.biggirlo.gw.model.ClassifyTable;

import java.util.Collection;

/**
 * <pre>
 * 数据访问接口
 * </pre>
 */
@Mapper
public interface ClassifyTableMapper extends BaseMapper<ClassifyTable> {
    Collection<? extends TreeNode> searchTreeNodes();
}