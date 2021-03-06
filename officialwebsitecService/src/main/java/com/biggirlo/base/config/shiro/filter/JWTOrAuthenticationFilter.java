/**
 * **********************************************************
 * 该项目仅用于学习
 * 有任何疑问或者建议请致邮件于 email:645614025@qq.com
 * **********************************************************
 * **********************************************************
 */
package com.biggirlo.base.config.shiro.filter;

import com.biggirlo.base.config.cors.CorsConfig;
import com.biggirlo.base.config.shiro.ShiroApplicationConfig;
import com.biggirlo.base.config.shiro.authenurl.UrlArryListUtil;
import com.biggirlo.system.model.SysHandle;
import com.biggirlo.system.util.UserLoginUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author 王雁欣
 * create on 2017/11/12 4:41 
 */
public class JWTOrAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    public JWTOrAuthenticationFilter( ){

    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setHeader("Access-control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", CorsConfig.getInstance().getAccessControlAllowMethods());
            httpResponse.setHeader("Access-Control-Allow-Headers", CorsConfig.getInstance().getAccessControlAllowHeaders());
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
       /* Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
        List<SysMenu> menus = (List<SysMenu>) subject.getSession().getAttribute(UserLoginUtils.LOGIN_USER_MENUS_NAME);
        System.out.print(httpRequest.getRequestURL());*/

        boolean isLogin = super.preHandle(request, response);
        //为正值的时候判断是否
        if(isLogin){
            this.isForbidden(request,response);
        }
        return isLogin;
    }


    /**
     * 判断是否有访问权限
     * @return
     */
    private void isForbidden(ServletRequest request,
                             ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String url = httpServletRequest.getServletPath().toString();

        //获取登录用户所拥有的角色
        Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
        List<SysHandle> sysHandles = (List<SysHandle>) subject.getSession().getAttribute(UserLoginUtils.LOGIN_USER_HANDLE_NAME);

        //判断是否有访问权限
        if(this.isInAuthUrls(url,httpServletRequest.getMethod(), UrlArryListUtil.getInstance().getSysHandles())
                && !this.isInAuthUrls(url,httpServletRequest.getMethod(),sysHandles)
                ){
            //无权限url
            String forbiddenUrl = ShiroApplicationConfig.getInstance().getForbidden();
            forbiddenUrl += "&loginType=json";
            log.trace("forbiddenUrl" + forbiddenUrl);
            WebUtils.issueRedirect(request, response, forbiddenUrl);
        }
    }


    /**
     * 判断是否在操作权限里面
     * @param url
     * @param sysHandles
     * @return
     */
    private boolean isInAuthUrls(String url, String method ,List<SysHandle> sysHandles) {
        for(SysHandle handle : sysHandles){
            if(handle.getUrl() != null && handle.getUrl().equals(url) && method.equals(handle.getType()))
                return true;
        }
        return false;
    }

}
