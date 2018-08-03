layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var reservationDate = $("#reservationDate").val();
    var servationTime = [
        {"value": "0", "text": "请选择时间段"},
        {"value": "1", "text": "9:00-11:00"},
        {"value": "2", "text": "11:00-13:00"},
        {"value": "3", "text": "13:00-15:00"},
        {"value": "4", "text": "16:00-18:00"}
    ]
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
            {field: 'cnhkProduct', title: '服务项目', align: 'center'},
            /*{
                field: 'isArrivalsStore', title: '是否到店', align: 'center', templet: function (d) {
                    return '<input type="checkbox" name="newsTop" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" >'
                }
            },*/
            {
                field: 'operateTime', title: '操作时间', align: 'center', minWidth: 110, templet: function (d) {
                    return d.operateTime.substring(0, 10);
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

//获取当前时间，格式YYYY-MM-DD
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }

    //添加预约
    function addNews(edit) {
        var index = layui.layer.open({
            title: "新增预约",
            type: 2,
            content: "reservationAdd.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                $.ajax({
                    url: '/cnhkManager/serviceTimeSectionCount',
                    data: {
                        "reservationDate": getNowFormatDate()
                    },
                    success: function (data) {
                        var html = "<option value='0'>请选择时间段</option>";
                        for (var i = 1; i < servationTime.length; i++) {
                            var optionHtml = "<option value='" + servationTime[i].value + "' "
                            var flag = false;
                            //遍历已经预约的统计
                            for (var j = 0; j < data.length; j++) {
                                //半判断当前的option与统计的option是否一致
                                if (servationTime[i].value == data[j].serviceTimeSection) {
                                    flag = true;
                                    var count = data.count;
                                    if (count >= 2) {
                                        //预定已满
                                        optionHtml += " disable>" + servationTime[i].text + "（已满）</option>"
                                    } else if (count == 1) {
                                        optionHtml += ">" + servationTime[i].text + "（剩余1位）</option>"

                                    } else {
                                        optionHtml += ">" + servationTime[i].text + "（剩余2位）</option>"
                                    }
                                    break;
                                }
                            }
                            if (flag == false) {
                                optionHtml += ">" + servationTime[i].text + "（剩余2位）</option>"
                            }
                            html += optionHtml;
                        }

                        body.find("#serviceTimeSectionAdd").append(html);
                        form.render();

                    }
                }),
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回预约列表', '.layui-layer-setwin .layui-layer-close', {
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

    function createOption(data) {
        var body = layui.layer.getChildFrame('body', index);


    }

    //修改预约
    function editReservation(data) {
        var index = layui.layer.open({
            title: "修改预约",
            type: 2,
            content: "reservationEdit.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (data) {
                    body.find("#userName").val(data.userName);
                    body.find("#reservationId").val(data.rid);
                    body.find("#phoneNumber").val(data.phoneNumber);
                    body.find("#reservationDate").val(data.reservationDate);
                    body.find("#serviceTimeSection").val(data.serviceTimeSectionId);
                    var timedd = 'dd[lay-value=' + data.serviceTimeSectionId + ']';
                    var productdd = 'dd[lay-value=' + data.cnhkProductId + ']';
                    body.find('#cnhkProduct').siblings("div.layui-form-select").find('dl').find(productdd).click();
                    body.find('#serviceTimeSection').siblings("div.layui-form-select").find('dl').find(timedd).click();
                    form.render();
                    form.render();

                }
                setTimeout(function () {
                    layui.layer.tips('点击此处返回预约列表', '.layui-layer-setwin .layui-layer-close', {
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

    $(".add_btn").click(function () {
        addNews();
    })
    //列表操作
    table.on('tool(newsList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            editReservation(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此预约？', {icon: 3, title: '提示信息'}, function (index) {
                $.ajax({
                    url: '/cnhkManager/reservationDelete',
                    type: 'POST',
                    async: false,
                    cache: false,
                    dataType: 'json',
                    contentType: "application/json",
                    data: JSON.stringify({"id": data.rid}),
                    success: function (data) {
                        /*top.layer.close(index);*/
                        top.layer.msg("删除成功！");
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
                    }

                });

                /*layer.close(index);*/
            });
        } else if (layEvent === 'look') { //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });

})