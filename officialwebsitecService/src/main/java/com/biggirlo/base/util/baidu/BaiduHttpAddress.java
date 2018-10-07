/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util.baidu;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 王雁欣
 * create on 2018/1/6 13:05 
 */
public enum  BaiduHttpAddress {
    Geocding ("http://api.map.baidu.com/geocoder/v2/",RequestMethod.GET);

    BaiduHttpAddress (String url,RequestMethod method){
        this.url = url;
        this.method = method;
    }

    private String url;

    private RequestMethod method;

    public String URL(){
        return this.url;
    }

    public RequestMethod METHOD() {
        return method;
    }
}
