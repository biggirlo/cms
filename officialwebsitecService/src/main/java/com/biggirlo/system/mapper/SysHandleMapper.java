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
  * 作   者：王雁欣
  * 创建日期：2017-9-9
  * </pre>
  */

package com.biggirlo.system.mapper;

import com.biggirlo.base.mapper.BaseMapper;
import com.biggirlo.system.jopo.jstree.TreeNode;
import com.biggirlo.system.jopo.search.HandleRoleSearch;
import com.biggirlo.system.model.SysHandle;

import java.util.List;

/**
 * <pre>
 * 数据访问接口
 * </pre>
 */

public interface SysHandleMapper extends BaseMapper<SysHandle> {

    /**
     * 得到TreeNode.class的操作列表
     * @return
     */
    List<TreeNode> searchHandleToTreeNode();

    /**
     * 获取所有的操作节点，并附上角色关联
     * @param roId
     * @return
     */
    List<TreeNode> searchHandleToTreeNodeByRole(Long roId);


    /**
     * 根据角色获取所有的操作
     * @param search
     * @return
     */
    List<SysHandle> searchByRoles(HandleRoleSearch search);
}