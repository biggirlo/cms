package com.biggirlo.gw.util;

import com.biggirlo.base.util.Code;

public enum  GwCode{
    UNEXIT_COLUMN_CODE (40401,"不存在的栏目编码"),
    UNEXIT_CLASSIFY_CODE (40402,"不存在的分类编码"),
    UNEXIT_CONTENT_CODE (40403,"不存在的内容编码"),
    UNEXIT_CONTENT_ID (40404,"不存在的内容id"),
    VOID_COLUMN_CODE (40405,"栏目编码为空"),
    VOID_CLASSIFY_CODE (40406,"分类编码为空"),
    VOID_CONTENT_CODE (40407,"内容编码为空"),
    VOID_CONTENT_ID (40408,"内容id为空");

    private long code;
    private String msg;
    GwCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回值
     *
     * @return
     */
    public long value() {
        return this.code;
    }

    /**
     * 返回信息
     *
     * @return
     */
    public String msg() {
        return this.msg;
    }
}
