/**
 * 该项目仅供学习
 *  如有疑问或建议请致邮箱：645614025@qq.com
 * Created by 王雁欣
 * Date: 2017/10/27
 * Time: 20:15
 */
$(function () {
    var vm = new Vue({
        el: "#menuApp",
        data:{
            isShow: false ,//隐藏或者现实列表
            tree:null,//树代理对象
            dataTable:null,//dataTable代理对象
            url:{
                tree:  context.config.requestHost + '/gw/classify/tree',
                menus: context.config.requestHost + '/gw/classify/dataTable/list',
                base: context.config.requestHost + '/gw/classify/',
                save: context.config.requestHost + '/gw/classify/info',
                baselist: context.config.requestHost + "/gw/classify/list"
            },
            menuModal:{
                el:'#editModal',
                title:"新增分类"
            },
            modelFrom:{
                id:null,
                coversImg:null,
                logo: null,
                name:null,
                sort:null,
                content:null,
                present:null,
                code:null,
                parentId:0, //父级id 默认为0
                columnId:0, //栏目
            },
            coversImgShow:false,
            logoShow:false,
            //详情
            modelInfo:{},
            isDataTableView:true ,//是否是datatable视图,默认是
            isShowAddBtn:false //是否显示添加按钮
        },
        //init后初始化
        mounted:function () {
            //显示页面
            this.isShow = true;
            //初始化树
            this.treeInit();
            //初始化dataTables
            this.dataTablesInit();
            var shef = this
            this.menuModal.el = $("#editModal");
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
                    //判断是否重复点击
                    /*if(sheetP.modelFrom.parentId == e.node.id && e.node.id != 0 )
                        return;*/


                    // 获取当前节点
                    if(e.node.id > 0){
                        sheetP.isShowAddBtn = true;
                        sheetP.modelFrom.parentId = e.node.id;
                        sheetP.modelFrom.columnId = e.node.data;
                    }
                    else if(e.node.id < 0){
                        sheetP.isShowAddBtn = true;
                        sheetP.modelFrom.parentId = 0;
                        sheetP.modelFrom.columnId = - e.node.id;
                    }
                    else if( e.node.id == 0 ){
                        sheetP.isShowAddBtn = false;
                        sheetP.modelFrom.parentId = 0;
                        sheetP.modelFrom.columnId = - e.node.id;
                    }

                    //判断是否是子节点
                    if(e.node.children.length == 0 && e.node.id > 0 ){//详情
                        //重置数据模型
                        sheetP.reSetting;
                        context.method.get(sheetP.url.base+ sheetP.modelFrom.parentId ,function (requset) {
                            sheetP.modelInfo = requset.data;
                            if(sheetP.modelInfo.content)
                                sheetP.modelInfo.content = sheetP.modelInfo.content.replace(/_@/g, '<br/>').replace(/\n/g,"<br/>").replace(/\s/g,"&nbsp;");//IE9、FF、chrome
                            if(sheetP.modelInfo.present )
                                sheetP.modelInfo.present = sheetP.modelInfo.present.replace(/_@/g, '<br/>').replace(/\n/g,"<br/>").replace(/\s/g,"&nbsp;");//IE9、FF、chrome

                            sheetP.isDataTableView = false;
                            toastr.success('已加载"'+ sheetP.modelInfo.name +'"分类详情',"成功");
                        })
                    }else{
                        //刷新datatable
                        sheetP.dataTable.settings()[0].ajax.data = {
                           parentId :sheetP.modelFrom.parentId,
                            columnId :sheetP.modelFrom.columnId
                        };
                        sheetP.dataTable.ajax.reload();
                        sheetP.isDataTableView = true ;
                        toastr.success("已刷新分类列表","成功");
                    }
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
                        "parentId":this.modelFrom.parentId,
                        "columnId":this.modelFrom.columnId
                    }),
                    "columns":[
                        { "data": "id" ,"title":"userId","visible": false,"name": "ID"},
                        { "data": "code" ,"title":"分类编码","name": "CODE","searchable":true},
                        { "data": "name" ,"title":"分类名称","name": "NAME","searchable":true},
                        { "data": "logo" ,"title":"logo","name": "LOGO","searchable":false,
                            render: function(data, type, row, meta) {
                                var html = "";
                                if(data != null && data !='')
                                    html = '<img style="width: 70px;" src="' + data + '">';
                                return html;
                            }
                        },
                       /* { "data": "coversImg" ,"title":"封面图片","name": "COVERS_IMG","searchable":false,
                           render: function(data, type, row, meta) {
                               var html = "";
                               if(data != null && data !='')
                                   html = '<img style="width: 70px;" src="' + data + '">';
                               return html;
                            }
                        },*/
                        { "data": "sort" ,"title":"排序","name": "SORT","searchable":false},
                        { "data": "present" ,"title":"功能简介","name": "PRESENT","searchable":false},
                        { "data": "content" ,"title":"功能描述","name": "CONTENT","searchable":false,
                            render: function(data, type, row, meta){
                                var text = "";
                                if(data && data.length > 20 )
                                    text = data.substr(0,19) + "...";
                                else
                                    text= data ;
                                return "<span title='"+ data +"'>" + text +"</span>";
                            }
                        },
                        { "data": "createTime" ,"title":"创建时间","name": "CREATE_TIME","searchable":false},
                        { "data": "updateTime" ,"title":"更新时间","name": "UPDATE_TIME","searchable":false}
                    ]
                });
            },
            /**
             * 刷新树结构
             */
            refresh:function () {
                this.tree.jstree(true).refresh();
                toastr.success("已刷新分类树","成功");
            },
            reload:function () {
                this.dataTable.ajax.reload();
            },
            add:function () {
                this.reSetting();
                this.menuModal.title = "新增";
                this.menuModal.el.modal('show');
            },
            edit:function () {
                this.reSetting();
                this.menuModal.title = "编辑";
                var rows = this.dataTable.rows({selected:true});
                if(rows.data().length != 1){
                    toastr.info("请选择一条记录", "提示");
                    return;
                }
                var self = this;
                context.method.get(self.url.base+ rows.data()[0].id ,function (requset) {
                    self.modelFrom = requset.data;
                    //设置图片
                    console.log(self.modelFrom.coversImg);
                    if(self.modelFrom.logo != null && self.modelFrom.logo != '')
                        $('#logoSrc').attr("src", self.modelFrom.logo);
                    else
                        $('#logoSrc').removeAttr("src");

                    //设置图片
                    if(self.modelFrom.coversImg != null && self.modelFrom.coversImg != '')
                        $('#coversImgSrc').attr("src", self.modelFrom.coversImg);
                    else
                        $('#coversImgSrc').removeAttr("src");

                })
                this.menuModal.el.modal('show');
            },
            save:function(){
                //表单验证
                if(!this.validate())
                    return
                var shef = this;
                context.method.postMutip(this.url.save,new FormData($( "#modelFrom" )[0]),function (data) {
                    if(data.code == sysCode.SUCCESS ){
                        toastr.success(data.msg,"成功");
                        shef.dataTable.ajax.reload();
                        shef.menuModal.el.modal('hide');
                        shef.refresh();
                        shef.isDataTableView = true;
                    }else {
                        toastr.error(data.msg,"失败");
                    }
                },function(data){
                    toastr.error("保存失败","失败")
                });
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

                    },
                    messages:{
                        menuCode:'请输入分类编码',
                        menuName:'请输入分类名称',
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
})