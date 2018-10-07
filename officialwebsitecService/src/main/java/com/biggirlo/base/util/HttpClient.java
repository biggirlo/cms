/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util;

/**
 *  请求客户端工具类
 * @author 王雁欣
 * create on 2017/11/30 22:59 
 */
public class HttpClient {

    /*private static App app = App.getInstance();

    public static Object post(String url,Object obj) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(url);

        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(app.getAppSecret(), app.getNonce(), curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", app.getAppKey());
        httpPost.addHeader("Nonce",app.getNonce());
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", app.getContentType());

        // 设置请求的参数

        httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairs(obj), "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        // 打印执行结果
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        return null;
    }


    *//**
     * 获取请求参数
     * @return
     *//*
    private static List<NameValuePair> getNameValuePairs(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(object != null){
            Class objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for(Field field :fields){
                String name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method m = objectClass.getMethod("get" + name);
                Object value = m.invoke(object);
                if(value != null)
                    nvps.add(new BasicNameValuePair(  field.getName(), value.toString()));
            }
        }

        return  nvps;
    }*/
}
