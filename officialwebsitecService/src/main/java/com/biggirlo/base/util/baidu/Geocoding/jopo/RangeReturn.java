/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util.baidu.Geocoding.jopo;

/**
 *
 * @author 王雁欣
 * create on 2018/2/12 16:47 
 */
public class RangeReturn {

    private Integer status;

    private RangeResult[] result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RangeResult[] getResult() {
        return result;
    }

    public void setResult(RangeResult[] result) {
        this.result = result;
    }
}
