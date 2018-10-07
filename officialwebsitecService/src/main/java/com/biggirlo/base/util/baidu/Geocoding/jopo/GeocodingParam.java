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
 * @author 王雁欣 请求参数
 * create on 2018/1/6 12:34 
 */
public class GeocodingParam {
    //	待解析的地址。最多支持84个字节。
    //可以输入三种样式的值，分别是：
    //1、标准的结构化地址信息，如北京市海淀区上地十街十号 【推荐，地址结构越完整，解析精度越高】
    //2、支持“*路与*路交叉口”描述方式，如北一环路和阜阳路的交叉路口
    //第二种方式并不总是有返回结果，只有当地址库中存在该地址描述时才有返回。
    private  String address;

    //地址所在的城市名。用于指定上述地址所在的城市，当多个城市都有上述地址时，该参数起到过滤作用，但不限制坐标召回城市
    private String city;

    //可选参数，添加后返回国测局经纬度坐标或百度米制坐标 坐标系说明	string	gcj02ll（国测局坐标）、bd09mc（百度墨卡托坐标）	bd09ll（百度经纬度坐标）
    private String ret_coordtype;

    //用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key”
    private String ak;

    //若用户所用ak的校验方式为sn校验时该参数必须
    private String sn;

    //输出格式为json或者xml	string	json或xml	xml	否
    private String output;

    //	将json格式的返回值通过callback函数返回以实现jsonp功能
    private String callback;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRet_coordtype() {
        return ret_coordtype;
    }

    public void setRet_coordtype(String ret_coordtype) {
        this.ret_coordtype = ret_coordtype;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
