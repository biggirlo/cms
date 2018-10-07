$(function () {
    var vm = new Vue({
        el: "#App",
        data:{
            columnlist:null,
            columnMap:{
                homePage:null, //官网首页栏目
                product:null, //产品栏目
                //。。。。
            },
            code:{
                homePageCode:"GW_HOMEPGE" ,//官网首页栏目代码
                product:"GW_PRODUCT" //官网首页栏目代码
                //。。。。。
            }
        },
        //初始化
        mounted:function () {
            this.init();
        },
        methods:{
            //初始化方法
            init:function () {
                var shef = this;
                //获取初始化数据
                $.ajax({
                    //.......
                    dataType:'json',
                    sucess:function (request) {
                        if(request.code == 10000){
                            shef.columnlist = request.data;
                            shef.columMapinit();
                        }else {
                            //。。。
                        }
                    }
                })
            },
            columMapinit:function () {
                //获取首页栏目
              this.columnMap.homePage = this.getColumByCode(this.code.homePage);
              this.columnMap.product = this.getColumByCode(this.code.product);
              //。。。。
            },
            getColumByCode:function (code) {
                for(var i = 0 ; i < columnlist.length ; i++){
                    var column = columnlist[i].code == code;
                    return column;
                }
            }
        }

    })
})