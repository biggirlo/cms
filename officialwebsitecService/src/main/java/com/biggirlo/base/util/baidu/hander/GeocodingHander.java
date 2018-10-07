/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util.baidu.hander;

import com.alibaba.fastjson.JSONObject;
import com.biggirlo.base.util.baidu.Geocoding.jopo.GeocodReturn;
import com.biggirlo.base.util.baidu.Geocoding.jopo.GeocodingParam;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 *
 * @author 王雁欣
 * create on 2018/1/7 1:52 
 */
public class GeocodingHander {

    private static final String URL = "http://api.map.baidu.com/geocoder/v2/?address=YOURADDRESS&output=json&ak=YOURAK&callback=showLocation";

    public static GeocodReturn getGeocoder(GeocodingParam geocodingParam) throws IOException {
        String url = URL.replace("YOURADDRESS",geocodingParam.getAddress());
        /*if(geocodingParam.getAddress().equals(" 茂南区人民北路388号"))
            url.replace("YOURAK",geocodingParam.getAk()).replaceAll("\\s*","").replaceAll(" ","").trim();*/
        url = url.replace("YOURAK",geocodingParam.getAk()).replaceAll(" ","").trim();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);


        // 执行请求
        HttpResponse response = httpClient.execute(httpGet);

        String resutl = EntityUtils.toString(response.getEntity(), "utf-8");
        resutl = resutl.substring("showLocation&&showLocation(".length(),resutl.length()-1);

        GeocodReturn json = JSONObject.parseObject(resutl,GeocodReturn.class);

        return json;
    }


}
