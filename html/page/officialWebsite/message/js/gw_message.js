
$(function () {
    var vm = new Vue({
        el:"#messageApp",
        data: {
            isShow: false ,//隐藏或者现实列表
            dataTable:null,
            url:{
                dataTable: context.config.requestHost +  '/gw/message/dataTable/list',
                colunm: context.config.requestHost + "/gw/message/",
                colunmList: context.config.requestHost + "/gw/message/list",
            },
            logoShow:false,
        },
        //init后初始化
        mounted:function () {
            var shef = this ;
            //显示页面
            this.isShow = true;
            this.dataTablesInit();

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
                        { "data": "name" ,"title":"姓名","name": "NAME","searchable":false},
                        { "data": "moble" ,"title":"手机","name": "MOBlE","searchable":false},
                        { "data": "context" ,"title":"内容","name": "CONTEXT","searchable":false},
                        { "data": "createTime" ,"title":"时间","name": "CREATE_TIME","searchable":false},
                    ]
                });
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
            }

        }
    })
})

