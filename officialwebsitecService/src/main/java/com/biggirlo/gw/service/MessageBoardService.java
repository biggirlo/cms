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

import com.biggirlo.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biggirlo.gw.model.MessageBoard;
import com.biggirlo.gw.mapper.MessageBoardMapper;

/**
 * <pre>
 * 业务类
 * </pre>
 */
@Service("messageBoardService")
public class MessageBoardService extends BaseService<MessageBoard, Long> {

    @Autowired
    private  MessageBoardMapper MessageBoardMapper;
}