/**
 * 该项目仅供学习
 *  如有疑问或建议请致邮箱：645614025@qq.com
 * Created by 王雁欣
 * Date: 2017/9/29
 * Time: 1:59
 */

var context = {
    config:{
        //hostPort:"http://192.168.0.114:8082",//项目地址
        hostPort:"http://biggirlos.natapp1.cc",//项目地址

        //requestHost:"http://192.168.0.114:8082/official-website", //请求地址
        requestHost:"http://biggirlos.natapp1.cc/xiaoshu", //请求地址
    },
    url:{
       login:  '../../../page/system/login/login.html'
    },
    tokenName:"Authorization",//token名称
    hostPortName:"XForwardedFor",//请求地址名称
    method:{
        get:function (url,success,error) {
            $('.demo-loading-btn').button('loading');
            $.ajax({
                url:url,
                type:'GET', //GET
                beforeSend: function(request) {
                    request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
                    request.setRequestHeader(context.hostPortName, context.config.hostPort);
                },
                success:function (data) {
                    context.ajaxSuccess(data,success);
                },
                error:function (code) {
                    context.error(error);
                }
            })
        },
        post:function (url,json,success,error) {
            $('.demo-loading-btn').button('loading');
            $.ajax({
                url:url,
                type:'POST', //GET
                beforeSend: function(request) {
                    request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
                    request.setRequestHeader(context.hostPortName, context.config.hostPort);
                },
                contentType:  "application/json; charset=utf-8",
                data:JSON.stringify(json),
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text,
                success:function (data) {
                    context.ajaxSuccess(data,success);
                },
                error:function (code) {
                    context.error(error);
                }
            })
        },
        delete:function (url,json,success,error) {
            $('.demo-loading-btn').button('loading');
            $.ajax({
                url:url,
                type:'DELETE', //GET
                beforeSend: function(request) {
                    request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
                    request.setRequestHeader(context.hostPortName, context.config.hostPort);
                },
                contentType:  "application/json; charset=utf-8",
                data:JSON.stringify(json),
                dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text,
                success:function (data) {
                    context.ajaxSuccess(data,success);
                },
                error:function (code) {
                    context.error(error);
                }
            })
        },
        put:function (url,json,success,error) {
            $('.demo-loading-btn').button('loading');
            $.ajax({
                url:url,
                type:'PUT', //GET
                data:json,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text,
                beforeSend: function(request) {
                    request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
                    request.setRequestHeader(context.hostPortName, context.config.hostPort);
                },
                success:function (data) {

                    context.ajaxSuccess(data,success);
                },
                error:function (code) {
                    context.error(error);
                }
            })
        },
        /**
         * 上传图片使用
         * @param url
         * @param json
         * @param success
         * @param error
         */
        postMutip:function (url, json, success, error) {
            $('.demo-loading-btn').button('loading');
            $.ajax({
                url:url,
                type:'POST', //GET
                beforeSend: function(request) {
                    request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
                    request.setRequestHeader(context.hostPortName, context.config.hostPort);
                    //request.setRequestHeader("Content-Type", "multipart/form-data");
                },
                data:json,
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text,
                cache: false,
                contentType: false,
                processData: false,
                success:function (data) {
                    context.ajaxSuccess(data,success);
                },
                error:function (code) {
                    context.error(error);
                }
            })
        },
        dataTableAjax:function (url,data) {
           return {
               "type": "PUT",
               "url": url,
               "data": data,
               beforeSend: function (request) {
                   request.setRequestHeader(context.hostPortName, context.config.hostPort);
                   request.setRequestHeader(context.tokenName, sessionStorage.getItem(context.tokenName));
               }
           };
        },
    },
    /**
     * ajax请求成功处理函数
     * @param data
     * @param success
     */
    ajaxSuccess:function (data,success) {
        $('.demo-loading-btn').button('reset');
        if(data.code == sysCode.UN_LOGIN)
            window.location.href = context.url.login;
        else if(data.code == sysCode.FORBIDDEN){
            toastr.error("您目前无权限操作","错误")
        } else
            success(data);
    },
    error : function (error) {
        $('.demo-loading-btn').button('reset')
        if(error)
            error
        else
            toastr.error("请求错误","错误");
    }
}

/**
 * 系统返回码
 * 与后台约定，不可随意修改
 * @type {{UN_LOADING: number}}
 */
var sysCode = {
    SUCCESS:10000,//请求成功
    SYSTEM_ERROR:10001,//系统错误
    UN_LOGIN:10002,//未登陆
    LOGIN_ERROR_UN_EXIST_NAME_PASSWORD:10003,//用户名或者密码为空
    LOGIN_ERROR_FALIE_NAME_PASSWORD:10004,//用户名或者密码错误
    FORBIDDEN:10403//用户无操作权限访问
}

/**
 *
 * @type {{}}
 */
var webPageState = {
    isNeedSave : false,//当前页面是否需要保存,默认不需要
}