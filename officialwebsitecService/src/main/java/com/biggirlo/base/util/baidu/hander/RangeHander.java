/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util.baidu.hander;

import com.alibaba.fastjson.JSONObject;
import com.biggirlo.base.util.baidu.BaiduConfig;
import com.biggirlo.base.util.baidu.Geocoding.jopo.RangeParam;
import com.biggirlo.base.util.baidu.Geocoding.jopo.ConvertReturn;
import com.biggirlo.base.util.baidu.Geocoding.jopo.RangeReturn;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

/**
 *
 * @author 王雁欣
 * create on 2018/2/12 13:31 
 */
public class RangeHander {

    //gps坐标转百度
    private static final String CONVERT_URL = "http://api.map.baidu.com/geoconv/v1/?coords=YOURLON,YOURLAT&from=1&to=5&ak=YOURAK";

    //批量算路
    private static final String RANGLE_URL = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=YOURORIGINS&destinations=YOURDESTINATIONS&ak=YOURAK";

    public static ConvertReturn getGeocoder(RangeParam rangeParam) throws IOException {
        String url = CONVERT_URL.replace("YOURLAT",rangeParam.getLat().toString());
        url = url.replace("YOURLON",rangeParam.getLon().toString());
        url = url.replace("YOURAK", BaiduConfig.getInstance().getAk()).replaceAll(" ","").trim();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);


        // 执行请求
        HttpResponse response = httpClient.execute(httpGet);

        String resutl = EntityUtils.toString(response.getEntity(), "utf-8");
        //resutl = resutl.substring("showLocation&&showLocation(".length(),resutl.length()-1);

        ConvertReturn json = JSONObject.parseObject(resutl,ConvertReturn.class);

        return json;
    }

    /**
     * 批量算路
     * @return
     */
    public static RangeReturn getRangle(String origins, String destinations) throws IOException {
        origins = URLEncoder.encode(origins, "utf-8");
        String url = RANGLE_URL.replace("YOURORIGINS",origins);
        url = url.replace("YOURDESTINATIONS",destinations);
        url = url.replace("YOURAK", BaiduConfig.getInstance().getAk()).replaceAll(" ","").trim();

        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url);
        // 执行请求
        HttpResponse response = httpClient.execute(httpGet);

        String resutl = EntityUtils.toString(response.getEntity(), "utf-8");
        //resutl = resutl.substring("showLocation&&showLocation(".length(),resutl.length()-1);

        RangeReturn json = JSONObject.parseObject(resutl,RangeReturn.class);

        return json;
    }
}
