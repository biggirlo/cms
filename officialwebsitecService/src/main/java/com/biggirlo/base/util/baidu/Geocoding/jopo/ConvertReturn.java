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
 * create on 2018/2/12 14:44 
 */
public class ConvertReturn {
    private  Integer status; //Int	本次API访问状态，如果成功返回0，如果失败返回其他数字

    private RangeLocation[] result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RangeLocation[] getResult() {
        return result;
    }

    public void setResult(RangeLocation[] result) {
        this.result = result;
    }
}
