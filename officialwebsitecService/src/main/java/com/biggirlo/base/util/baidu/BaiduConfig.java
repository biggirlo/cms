/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.util.baidu;

import com.biggirlo.base.config.shiro.ShiroApplicationConfig;
import com.biggirlo.base.util.YamlLoadUtil;
import org.apache.log4j.Logger;
import org.springframework.core.env.PropertySource;

/**
 *
 * @author 王雁欣
 * create on 2018/1/6 14:15 
 */
public class BaiduConfig {

    Logger logg= Logger.getLogger(ShiroApplicationConfig.class);

    private static BaiduConfig baiduConfig;

    private BaiduConfig(){
        PropertySource propertySource = YamlLoadUtil.getYamlLoad("classpath:application.yml");
        logg.info("读取application.ymlcorsConfig配置");

        this.ak = propertySource.getProperty("baidu.ak").toString();
        logg.info("获取 baidu ak:" + this.ak);

    }

    private String ak;

    public static synchronized BaiduConfig getInstance() {
        if(baiduConfig == null)
            baiduConfig = new BaiduConfig();
        return baiduConfig;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }
}
