alert(1);

var eleTemp ;
var eleNameTemp ;
var isCanUpdate = true;//是否需要绑定事件
$(function () {
    $("body").children().on("click",function (event) {
        //target 就是这个对象
        var targ ;
        if (event.target)
            targ = event.target
        else if (event.srcElement)
            targ = event.srcElement
        if (targ.nodeType == 3) // defeat Safari bug
            targ = targ.parentNode

        //get the name of element
        var tname
        tname=targ.tagName

        //shop Propagation
        event.stopPropagation();

        //if targ has children don't changce textEdit or imgEdit
        if(isCanUpdate && $(targ).children().length == 0 && tname && tname != 'IMG' && tname !=''){
            //设置被点击事件的全局变量
            eleTemp = targ;
            eleNameTemp = tname;
            $(window.parent.document).find("#imgEdit").hide();
            $(window.parent.document).find("#textEdit").show().val($(targ).html());
        }else if(tname == 'IMG'){
            //设置被点击事件的全局变量
            eleTemp = targ;
            eleNameTemp = tname;
            $(window.parent.document).find("#textEdit").hide();
            $(window.parent.document).find("#imgEdit").show().find("img").attr("src",$(targ).attr("src"));
        }
        isCanUpdate = true;
    })
})
var changeImg = function () {
    isCanUpdate = false;
    var data = sessionStorage.getItem("iframeTemp");
    if(eleTemp && eleTemp != null && data != null && eleNameTemp != "IMG")
        $(eleTemp).html(data);
    else if(eleTemp && eleTemp != null && data != null && eleNameTemp == "IMG")
        eleTemp.src = data;
    sessionStorage.setItem("iframeTemp",null);

}