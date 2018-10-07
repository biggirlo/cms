/**
 * 该项目仅供学习
 *  如有疑问或建议请致邮箱：645614025@qq.com
 * Created by 王雁欣
 * Date: 2017/10/27
 * Time: 20:15
 */
    var contentApp = new Vue({
        el: "#contentApp",
        data:{
            isOpenHmtlEidt:false,
            isShow: false ,//隐藏或者现实列表
            tree:null,//树代理对象
            dataTable:null,//dataTable代理对象
            url:{
                tree: context.config.requestHost + '/gw/classify/tree',
                menus: context.config.requestHost + '/gw/content/dataTable/list',
                base: context.config.requestHost + '/gw/content/',
                save: context.config.requestHost + '/gw/content/info',
                baselist: context.config.requestHost + "/gw/content/list"
            },
            contentModal:{
                el:'#editModal',
                title:"新增内容",
                //url:'../../officialWebsite/content/ueditor.html',
                //url:'../../officialWebsite/content/page.html',
            },
            modelFrom:{
                id:null,
                code:null,
                title:null,
                name:null,
                isShow:null,
                sort:null,
                coversImg:null,
                logo: null,
                lessPresent:null,
                fullPresent:null,
                content:null,
                author:null,
                url:null,
                classifyId:0, //父级id 默认为0
            },
            //筛选
            search:{
                code:null,
                name:null,
                isShow:null
            },
            //详情
            modelInfo:{},
            isShowEdit:false ,//是否是编辑的视图,默认不是
            isShowAddBtn:false, //是否显示添加按钮
            coversImgShow:false,
            logoShow:false,

        },
        //init后初始化
        mounted:function () {
            //显示页面
            this.isShow = true;
            //初始化树
            this.treeInit();
            //初始化dataTables
            this.dataTablesInit();
            var shef = this;
            this.contentModal.el = $("#editModal");
        },
        methods:{
            /**
             * 树控件初始化
             */
            treeInit:function () {
                //请求路径
                var sheetP = this;
                this.tree = $('#menuTree').jstree({
                    'plugins': [ "types"],
                    'core': {
                        "themes" : {
                            "responsive": false
                        },
                        'data': function (obj, callback) {
                            var shef = this;
                            context.method.get(sheetP.url.tree,function(result) {
                                var jsonarray = result.data;
                                callback.call(shef, jsonarray);
                            });
                        }
                    },
                    "types" : {
                        "default" : {
                            "icon" : "fa fa-folder icon-state-warning icon-lg"
                        },
                        "file" : {
                            "icon" : "fa fa-file icon-state-warning icon-lg"
                        }
                    }
                }).bind("activate_node.jstree", function (obj, e) {
                    // 获取当前节点
                    sheetP.modelFrom.classifyId = e.node.id;
                    if( e.node.id > 0 )
                        sheetP.isShowAddBtn = true;
                    else
                        sheetP.isShowAddBtn = false;

                    //刷新datatable
                    sheetP.dataTable.settings()[0].ajax.data = {
                        classifyId :sheetP.modelFrom.classifyId,
                        "code":sheetP.search.code,
                        "name":sheetP.search.name,
                        "isShow":sheetP.search.isShow
                    };
                    sheetP.dataTable.ajax.reload();
                    sheetP.isDataTableView = true ;
                    toastr.success("已刷新内容列表","成功");
                })
            },
            /**
             * dataTable 初始化
             */
            dataTablesInit:function () {
                var menusUrl = this.url.menus;
                this.dataTable = $("#menuTable").DataTable({
                    "dom": '<"top">rt<"bottom"flp><"clear">',
                    serverSide: true,
                    "bSort": false,
                    "searching": false,
                    //服务器加载数据地址
                    "ajax":context.method.dataTableAjax( menusUrl,{
                        "classifyId":this.modelFrom.classifyId,
                        "code":this.search.code,
                        "name":this.search.name,
                        "isShow":this.search.isShow
                    }),
                    "columns":[
                        { "data": "id" ,"title":"userId","visible": false,"name": "ID"},
                        { "data": "code" ,"title":"内容编码","name": "CODE","searchable":true},
                        { "data": "name" ,"title":"内容名称","name": "NAME","searchable":true},
                        { "data": "title" ,"title":"内容标题","name": "TITLE","searchable":true},
                        { "data": "logo" ,"title":"logo","name": "LOGO","searchable":false,
                            render: function(data, type, row, meta) {
                                var html = "";
                                if(data != null && data !='')
                                    html = '<img style="width: 70px;" src="' + data + '">';
                                return html;
                            }
                        },
                        { "data": "coversImg" ,"title":"封面图片","name": "COVERS_IMG","searchable":false,
                            render: function(data, type, row, meta) {
                                var html = "";
                                if(data != null && data !='')
                                    html = '<img style="width: 70px;" src="' + data + '">';
                                return html;
                            }
                        },
                        { "data": "sort" ,"title":"排序","name": "SORT","searchable":false},
                        { "data": "isShow" ,"title":"是否显示","name": "IS_SHOW","searchable":false,
                            render: function(data, type, row, meta) {
                                if (data === 1) {
                                    return '<span class="label label-sm label-success"> 是 </span>';
                                } else {
                                    return '<span class="label label-sm label-default"> 否 </span>';
                                }
                            }},
                        { "data": "lessPresent" ,"title":"简介","name": "LESS_PRESENT","searchable":false,
                            render: function(data, type, row, meta) {
                                var text = "";
                                if (data && data.length > 20)
                                    text = data.substr(0, 19) + "...";
                                else
                                    text = data;
                                return "<span title='" + data + "'>" + text + "</span>";
                            }},
                        { "data": "content" ,"title":"功能描述","name": "CONTENT","searchable":false,
                            render: function(data, type, row, meta){
                                var text = "";
                                if(data && data.length > 20 )
                                    text = data.substr(0,19) + "...";
                                else
                                    text= data ;
                                return "<span title='"+ data +"'>" + text +"</span>";
                            }},
                    ]
                });
            },
            /**
             * 刷新树结构
             */
            refresh:function () {
                this.tree.jstree(true).refresh();
                toastr.success("已刷新内容树","成功");
            },
            reload:function () {

                this.dataTable.settings()[0].ajax.data = {
                    classifyId :this.modelFrom.classifyId,
                    "code":this.search.code,
                    "name":this.search.name,
                    "isShow":this.search.isShow
                };
                this.dataTable.ajax.reload();
            },
            add:function () {
                this.contentModal.title = "新增";
                this.isShowEdit = true;
                //$('#ueditor').attr('src', this.contentModal.url);

                //this.menuModal.el.modal('show');
            },
            edit:function () {
                //window.frames[0].document.documentElement.outerHTML = '1111'

                this.contentModal.title = "编辑";
                var rows = this.dataTable.rows({selected:true});
                if(rows.data().length != 1){
                    toastr.info("请选择一条记录", "提示");
                    return;
                }
                var self = this;
                context.method.get(self.url.base+ rows.data()[0].id ,function (requset) {
                    self.modelFrom = requset.data;
                    self.isShowEdit = true;
                    $("#coversImgSrc").attr("src", self.modelFrom.coversImg);
                    $("#logoSrc").attr("src", self.modelFrom.logo);
                    //$('#ueditor').attr('src', self.contentModal.url);

                    //往页面插入代码
                  /* setTimeout(function () {
                       window.frames[0].document
                       var body = window.frames[0].document.body;
                       var jquery = window.frames[0].document.createElement("script");
                       jquery.id = "jquery";
                       jquery.type = "text/javascript";
                       jquery.src = "../../../assets/global/plugins/jquery.min.js";
                       body.appendChild(jquery);

                       var bootstrap = window.frames[0].document.createElement("script");
                       bootstrap.id = "bootstrap";
                       bootstrap.type = "text/javascript";
                       bootstrap.src = "../../../assets/global/plugins/bootstrap/js/bootstrap.min.js";
                       body.appendChild(bootstrap);

                       var common = window.frames[0].document.createElement("script");
                       common.id = "common";
                       common.type = "text/javascript";
                       common.src = "../../common/common.js";
                       body.appendChild(common);

                       var outselt = window.frames[0].document.createElement("script");
                       outselt.id = "outselt";
                       outselt.type = "text/javascript";
                       outselt.src = "js/page.js";
                       body.appendChild(outselt);


                       console.log(body);

                   },3000)*/
                })
            },
            save:function(){

                //表单验证
                if(!this.validate()){
                    return
                }
                var shef = this;

                //取得子窗口的html代码
               /* var jquery = window.frames[0].document.getElementById('jquery');
                var bootstrap = window.frames[0].document.getElementById('bootstrap');
                var common =  window.frames[0].document.getElementById('common');
                var outselt =  window.frames[0].document.getElementById('outselt');
                var hideEvenBtn =  window.frames[0].document.getElementById('hideEvenBtn');

                if(jquery && jquery != null)
                    jquery.parentNode.removeChild(jquery);
                if(bootstrap && bootstrap != null)
                     bootstrap.parentNode.removeChild(bootstrap);
                if(common && common != null)
                common.parentNode.removeChild(common);
                if(outselt && outselt != null)
                    outselt.parentNode.removeChild(outselt);
                if(hideEvenBtn && hideEvenBtn != null)
                    hideEvenBtn.parentNode.removeChild(hideEvenBtn);
                $("#fullPresent").val(window.frames[0].document.documentElement.outerHTML);*/
                /*if(shef.modelFrom.url != null && shef.modelFrom.url !=''){
                    $("#fullPresent").val("");
                }else {
                    this.modelFrom.fullPresent = $("#fullPresent").val();
                }*/
                this.modelFrom.fullPresent = $("#fullPresent").val();

                context.method.postMutip(this.url.save,new FormData($( "#modelFrom" )[0]),function (data) {
                    if(data.code == sysCode.SUCCESS ){
                        toastr.success(data.msg,"成功");
                        shef.dataTable.ajax.reload();
                        shef.out();
                    }else {
                        toastr.error(data.msg,"失败");
                    }
                },function(data){
                    toastr.error("保存失败","失败")
                });
            },
            //退出编辑
            out:function () {

                var shef = this;
                if(webPageState.isNeedSave){
                    //询问框
                    this.$messagebox.show({'title':'询问','describe':'您未保存数据,确定退出吗'},{cb:function () {
                        webPageState.isNeedSave = false;
                        this.reSetting();
                        this.isShowEdit = false;
                    },buttonName:['取消','确定']});
                }else {
                    this.reSetting();
                    this.isShowEdit = false;
                }
            },
            /**
             * 打开上传图片的窗口
             * @param id
             */
            uploadWindow:function (id) {
                $(id).click();
            },
            /**
             * 展示图片
             */
            coversImgFileChange:function (evt) {
                var files = evt.target.files;
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#coversImgSrc').attr("src", e.target.result).show();
                };
                reader.readAsDataURL(files[0]);
                this.coversImgShow =true;
            },
            logoShowFileChange:function (evt) {
                var files = evt.target.files;
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#logoSrc').attr("src", e.target.result).show();
                };
                reader.readAsDataURL(files[0]);
                this.logoShow =true;
            },
            /**
             * html文件改变
             */
            changeHtml:function (evt) {
                var formData = new FormData();
                var seft = this;
                context.method.postMutip( context.config.requestHost +  "/html",new FormData($( "#modelFrom" )[0]),function (data) {
                    if(data.code == sysCode.SUCCESS ){
                        seft.modelFrom.url = data.data;
                    }else {
                        toastr.error("上传html失败","错误");
                    }
                },function(data){
                    toastr.error("上传html失败","错误");
                });
            },
            /**
             * 打开编辑框
             */
            openHmtlEidt:function () {
                this.isOpenHmtlEidt = true;
                this.appenHandler();
            },
            closeHmtlEidt:function () {
                this.isOpenHmtlEidt = false;
            },
            appenHandler:function () {
                setTimeout(function () {
                   /* window.frames[0].document
                    var body = window.frames[0].document.body;

                    var bootstrap = window.frames[0].document.createElement("script");
                    bootstrap.id = "bootstrap";
                    bootstrap.type = "text/javascript";
                    bootstrap.src = context.config.hostPort +  "/official-website/static/assets/global/plugins/bootstrap/js/bootstrap.min.js";
                    body.appendChild(bootstrap);

                    var common = window.frames[0].document.createElement("script");
                    common.id = "common";
                    common.type = "text/javascript";
                    common.src = context.config.hostPort +  "/official-website/static/page/common/common.js";
                    body.appendChild(common);

                    var outselt = window.frames[0].document.createElement("script");
                    outselt.id = "outselt";
                    outselt.type = "text/javascript";
                    outselt.src = context.config.hostPort +  "/official-website/static/page/officialWebsite/content/js/page.js";
                    body.appendChild(outselt);*/

                    window.frames[0].document
                    var body = window.frames[0].document.body;
                    window.frames[0].document.jQuery.getScript(context.config.hostPort +  "/official-website/static/page/officialWebsite/content/js/page.js");

                    console.log(body);

                },1000)
            },
            /**
             * 上传图片
             */
            uploadIMg:function () {
                $("#imgEdit").find("input").click();
            },
            uploadHtml2:function () {
                $("#html").click();
            },
            /**
             * 改变图片上传事件
             * @param evt
             */
            /*uploadImgChange:function (evt) {
                if($("#uImage").val()==null)
                    return
                context.method.postMutip( context.config.requestHost +  "/iamge/ueditor",new FormData($( "#modelFrom" )[0]),function (data) {
                    if(data.code == sysCode.SUCCESS ){
                        $("#imgEdit").find("img").attr("src",data.data);
                        $("#uImage").val(null);
                        var frame = document.getElementById('page');
                       /!* if(frame.contentWindow.getElementById("hideEvenBtn"))
                             console.log(frame.contentWindow.getElementById("hideEvenBtn"));*!/
                       // $("#page")[0].contentWindow().iClick()
                        //console.log(window.frames[0]);
                        //
                       sessionStorage.setItem("iframeTemp",data.data);
                       $( window.frames[0].document.getElementById("hideEvenBtn")).click();

                    }else {
                        toastr.error("修改图片失败","失败");
                    }
                },function(data){
                    toastr.error("修改图片失败","失败");
                });
            },*/
            /**
             * 编辑框内容改变
             *
             */
            changeText:function () {
                var text = $("#textEdit");
                if( text.val() !=null  && text.val().trim() != '' ){
                    sessionStorage.setItem("iframeTemp",text.val().trim());
                    $( window.frames[0].document.getElementById("hideEvenBtn")).click();
                }
            },
            /**
             * 重置
             */
            reSetRearch:function(){
                this.search.code = null;
                this.search.name = null;
                this.search.isShow = null;
            },
            //重置
            reSetting:function () {
                this.modelFrom.id = null;
                this.modelFrom.coversImg = null;
                this.modelFrom.logo = null;
                this.modelFrom.name = null;
                this.modelFrom.sort = null;
                this.modelFrom.content = null;
                this.modelFrom.present = null;
                this.modelFrom.code = null;
                this.modelFrom.title = null;
                this.modelFrom.isShow = null;
                this.modelFrom.lessPresent = null;
                this.modelFrom.fullPresent = null;
                this.modelFrom.content = null;
                this.modelFrom.author = null;
                this.modelFrom.url = null;
                this.coversImgShow = false;
                this.logoShow = false;
                $("input[type=file]").val(null);
            },
            //表单验证
            validate:function(el){
                return $("#modelFrom").validate({
                    rules:{
                        name:'required',
                        code:'required',
                        isShow:'required',
                        title:'required',
                    },
                    messages:{
                        menuCode:'请输入分类编码',
                        menuName:'请输入分类名称',
                        isShow:'请选择是否上架',
                        title:'请输入标题',
                    },
                }).form();
            },
            //删除
            del:function () {
                var rows = this.dataTable.rows({selected:true});
                if(rows.data().length == 0){
                    toastr.info("请选择删除记录", "提示");
                    return;
                }
                var self = this;
                this.$messagebox.show({'title':'询问','describe':'您确定删除所选择数据？'},{cb:function () {
                    var ids = new Array();
                    for(var i = 0 ; i < rows.data().length ; i++){
                        ids.push(rows.data()[i].id);
                        var node = self.tree.jstree("get_node",rows.data()[i].id);
                        ids = ids.concat(node.children_d);
                    }
                    context.method.delete(self.url.baselist ,{
                        ids : ids
                    },function (requset) {
                        if(requset.code == sysCode.SUCCESS){
                            self.dataTable.ajax.reload();
                            self.refresh();
                            toastr.success("删除成功","成功");
                        }else {
                            toastr.error(requset.msg,"失败");
                        }
                    })
                },buttonName:['取消','确定']});

            },
        }
    })