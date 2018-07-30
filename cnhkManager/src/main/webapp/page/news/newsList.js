layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var reservationDate = $("#reservationDate").val();

    //新闻列表
    var tableIns = table.render({

        elem: '#newsList',
        url: '/cnhkManager/reservation',
        cellMinWidth: 95,
        page: true,
        height: "full-125",
        limit: 20,
        limits: [10, 15, 20, 25],
        id: "newsListTable",
        cols: [[
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'id', title: 'ID', width: 60, align: "center"},
            {field: 'userName', title: '姓名', width: 100, align: "center"},
            {field: 'phoneNumber', title: '手机', width: 150, align: "center"},
            {field: 'reservationDate', title: '预约日期', align: 'center'},
            {field: 'serviceTimeSection', title: '预约时间段', align: 'center'},
            {field: 'serviceId', title: '服务项目', align: 'center'},
            /*{
                field: 'isArrivalsStore', title: '是否到店', align: 'center', templet: function (d) {
                    return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" >'
                }
            },*/
            {
                field: 'optionTime', title: '操作时间', align: 'center', minWidth: 110, templet: function (d) {
                    return d.optionTime.substring(0, 10);
                }
            },
            {title: '操作', width: 170, templet: '#newsListBar', fixed: "right", align: "center"}
        ]]
    });

    //是否置顶
    form.on('switch(newsTop)', function (data) {
        var index = layer.msg('修改中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            if (data.elem.checked) {
                layer.confirm();
                layer.msg("到店成功！");
            } else {
                layer.msg("取消到店！");
            }
        }, 500);
    })

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click", function () {

        if ($("#userName").val() != '' || $("#phoneNumber").val() != '' || $("#reservationDate").val() != "请选择时间段") {
            table.reload("newsListTable", {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    userName: $("#userName").val(), //搜索的关键字
                    phoneNumber: $("#phoneNumber").val(), //搜索的关键字
                    reservationDate: $("#reservationDate").val()  //搜索的关键字
                }
            })
        } else {
            layer.msg("请输入搜索的内容");
        }
    });

    //添加文章
    function addNews(edit) {
        var index = layui.layer.open({
            title: "修改预约",
            type: 2,
            content: "newsAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".newsName").val(edit.newsName);
                    body.find(".abstract").val(edit.abstract);
                    body.find(".thumbImg").attr("src", edit.newsImg);
                    body.find("#news_content").val(edit.content);
                    body.find(".newsStatus select").val(edit.newsStatus);
                    body.find(".openness input[name='openness'][title='" + edit.newsLook + "']").prop("checked", "checked");
                    body.find(".newsTop input[name='newsTop']").prop("checked", edit.newsTop);
                    form.render();
                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(index);
        })
    }

    $(".addNews_btn").click(function () {
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if (data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/re", {
                    newsId: newsId  //将需要删除的newsId作为参数传入
                }, function (data) {
                    tableIns.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addNews(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此预约？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("reservationDel", {
                    id: data.rid  //将需要删除的newsId作为参数传入
                }, function (data) {
                    tableIns.reload();
                })
                layer.close(index);
            });
        } else if (layEvent === 'look') { //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });

})