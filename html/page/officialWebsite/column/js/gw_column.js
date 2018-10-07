
$(function () {
    var vm = new Vue({
        el:"#columnApp",
        data: {
            isShow: false ,//隐藏或者现实列表
            dataTable:null,
            columnModal: {
                el: "#editColumnModal",
                title:"新增",
                isHasHardImg:true
            },
            columnFrom:{
                id:null,
                code:null,
                sort:null,
                isShowInTop:null,
                logo:null,
                present:null,
                name:null,
                concent:null,
                url: null
            },
            url:{
                save: context.config.requestHost  + '/gw/column/info',
                dataTable: context.config.requestHost +  '/gw/column/dataTable/list',
                colunm: context.config.requestHost + "/gw/column/",
                colunmList: context.config.requestHost + "/gw/column/list",
            },
            logoShow:false,
        },
        //init后初始化
        mounted:function () {
            var shef = this ;
            this.columnModal.el = $("#editColumnModal");
            //显示页面
            this.isShow = true;
            this.dataTablesInit();
            //this.userModal.isHasHardImg = false;
        },
        created: function () {
            // `this` 指向 vm 实例
        },
        methods:{
            /**
             * 初始化
             */
            dataTablesInit : function () {
                this.dataTable = $("#columnTable").DataTable({
                    "dom": '<"top">rt<"bottom"flp><"clear">',
                    serverSide: true,
                    "bSort": false,
                    "searching": false,
                    "language": {
                        searchPlaceholder: "输入用户名字"
                    },
                    //服务器加载数据地址
                    "ajax":context.method.dataTableAjax(this.url.dataTable,{
                        /*"id": 451*/
                    }),
                    "columns":[
                        { "data": "id" ,"title":"Id","visible": false,"name": "ID"},
                        { "data": "code" ,"title":"栏目编码","name": "CODE","searchable":true},
                        { "data": "name" ,"title":"栏目名称","name": "NAME","searchable":false},
                        { "data": "sort" ,"title":"排序","name": "SORT","searchable":false},
                        /*{ "data": "isShowInTop" ,"title":"是否顶部展示","name": "IS_SHOW_IN_TOP","searchable":false,
                            render: function(data, type, row, meta) {
                                if (data === 1) {
                                    return '<span class="label label-sm label-success"> 是 </span>';
                                } else {
                                    return '<span class="label label-sm label-default"> 否 </span>';
                                }
                        }},*/
                       /* { "data": "present" ,"title":"简介","name": "PRESENT","searchable":false,
                            render: function(data, type, row, meta){
                                var text = "";
                                if(data && data.length > 20 )
                                    text = data.substr(0,19) + "...";
                                else
                                    text= data ;
                                return "<span title='"+ data +"'>" + text +"</span>";
                            }
                            },*/
                        { "data": "concent" ,"title":"描述","name": "CONCENT","searchable":false,
                            render: function(data, type, row, meta){
                                var text = "";
                                if(data && data.length > 20 )
                                    text = data.substr(0,19) + "...";
                                else
                                    text= data ;
                                return "<span title='"+ data +"'>" + text +"</span>";
                            }},
                        { "data": "createTime" ,"title":"创建时间","name": "CREATE_TIME","searchable":false},
                        { "data": "updateTime" ,"title":"更新时间","name": "UPDATE_TIME","searchable":false}
                    ]
                });
            },
            add:function () {
                this.resetting();
                this.columnModal.title = "新增";
                this.columnModal.el.modal('show');
            },
            edit:function () {
                this.resetting();
                this.columnModal.title = "编辑";
                var rows = this.dataTable.rows({selected:true});
                if(rows.data().length != 1){
                    toastr.info("请选择一条记录", "提示");
                    return;
                }
                var self = this;
                context.method.get(self.url.colunm + rows.data()[0].id ,function (requset) {
                    self.columnFrom = requset.data;
                    $("#logoSrc").attr("src", self.columnFrom.logo);
                })
                this.columnModal.el.modal('show');
            },
            save:function(){
                //表单验证
                if(!this.validate())
                    return
                var shef = this;
                var formData = new FormData($( "#columnFrom" )[0]);
                context.method.postMutip(this.url.save,formData,function (data) {
                    if(data.code == sysCode.SUCCESS ){
                        toastr.success(data.msg,"成功");
                        shef.dataTable.ajax.reload();
                        shef.columnModal.el.modal('hide');
                    }else {
                        toastr.error(data.msg,"失败");
                    }

                },function(data){
                    toastr.error("保存失败","失败")
                });
            },
            //表单验证
            validate:function(el){
                return $("#columnFrom").validate({
                    rules:{
                        code:'required',
                        isShowInTop:'required',
                        name:'required'
                    },
                    messages:{
                        code:'请输入栏目编码',
                        isShowInTop:'请选择是否显示在顶部',
                        name:'请输入栏目名称'
                    },
                }).form();
            },
            /**
             * 打开上传图片的窗口
             * @param id
             */
            uploadWindow:function (id) {
                $(id).click();
            },
            /**
             * logo的图片改变事件
             * @param evt
             */
            logoShowFileChange:function (evt) {
                var files = evt.target.files;
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#logoSrc').attr("src", e.target.result).show();
                };
                reader.readAsDataURL(files[0]);
                this.logoShow =true;
            },
            //删除
            del:function () {
                var rows = this.dataTable.rows({selected:true});
                if(rows.data().length == 0){
                    toastr.info("请选择记录", "提示");
                    return;
                }
                var self = this;
                this.$messagebox.show({'title':'询问','describe':'您确定删除所选择数据？'},{cb:function () {
                    var ids = new Array(rows.data().length);
                    for(var i = 0 ; i < rows.data().length ; i++){
                        ids[i] = rows.data()[i].id;
                    }
                    context.method.delete(self.url.colunmList ,{
                        ids : ids
                    },function (requset) {
                        if(requset.code == sysCode.SUCCESS){
                            self.dataTable.ajax.reload();
                            toastr.success("删除成功","成功");
                        }else {
                            toastr.error(requset.msg,"失败");
                        }
                    })
                },buttonName:['取消','确定']});

            },
            //刷新
            reload:function () {
                this.dataTable.ajax.reload();
                toastr.success("已刷新","成功");
            },
            //重置
            resetting:function () {
                this.columnFrom ={
                        id:null,
                        code:null,
                        sort:null,
                        isShowInTop:null,
                        logo:null,
                        present:null,
                        name:null,
                        concent:null,
                        url: null
                }
                $("input[type=file]").val(null);
                this.logoShow = false;
            }
        }
    })
})

