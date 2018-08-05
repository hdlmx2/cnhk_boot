layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
    var servationTime = [
        {"value": "0", "text": "请选择时间段"},
        {"value": "1", "text": "9:00-11:00"},
        {"value": "2", "text": "11:00-13:00"},
        {"value": "3", "text": "13:00-15:00"},
        {"value": "4", "text": "16:00-18:00"}
    ]
    getReservationCount();
    form.verify({
        userName: function (val) {
            if (val == '') {
                return "姓名不能为空";
            }
        },

        cnhkProductId: function (val, item) {
            var value = $(item).val();
            if (value == '0') {
                return "请选择服务项目";
            }
        },
        reservationDate: function (val) {
            if (val == '') {
                return "请选择预约日期";
            }
        },
        serviceTimeSection: function (val, item) {
            var value = $(item).val();
            if (value == '0') {
                return "请选择服务时间段";
            }
        }

    })
    $("#refresh").click(function () {
        var time = $("#reservationDate").val();
        if (time == undefined || time == "") {
            top.layer.msg("请选择预约日期");

        } else {
            getReservationCount();
            top.layer.msg("刷新成功！");
        }
    })

    function getReservationCount() {
        $.ajax({
            url: '/cnhkManager/serviceTimeSectionCount',
            data: {
                "reservationDate": $("#reservationDate").val()
            },
            success: function (data) {
                var html = "<option value='" + servationTime[0].value + "'>" + servationTime[0].text + "</option>";
                for (var i = 1; i < servationTime.length; i++) {
                    var ddHtml = "<option value='" + servationTime[i].value + "'"
                    var flag = false;
                    //遍历已经预约的统计
                    if (data != undefined) {
                        for (var j = 0; j < data.length; j++) {
                            //半判断当前的option与统计的option是否一致
                            if (servationTime[i].value == data[j].time) {
                                flag = true;
                                var count = data[j].count;
                                if (count >= 2) {
                                    //预定已满
                                    ddHtml += " disabled=''>" + servationTime[i].text + "（已满）</option>";
                                } else if (count == 1) {
                                    ddHtml += " >" + servationTime[i].text + "（剩余1位）</option>"

                                } else {
                                    ddHtml += " >" + servationTime[i].text + "（剩余2位）</option>"
                                }
                                break;
                            }
                        }
                    }
                    if (flag == false) {
                        ddHtml += " >" + servationTime[i].text + "（剩余2位）</option>"
                    }
                    html += ddHtml;

                }
                $("#serviceTimeSection").html(html);
                form.render();

            }
        })

    }

    form.on("submit(reservationUpdate)", function (data) {
        $.ajax({
            url: '/cnhkManager/reservationUpdate',
            data: {
                "id": $("#reservationId").val(),
                "userName": $("#userName").val(),
                "phoneNumber": $("#phoneNumber").val(),
                "cnhkProductId": $("#cnhkProduct").val(),
                "reservationDate": $('#reservationDate').val(),
                "serviceTimeSectionId": $('#serviceTimeSection').val()
            },
            type: 'POST',
            async: false,
            cache: false,
            success: function (data) {
                /*top.layer.close(index);*/
                top.layer.msg("修改成功！");
                layer.closeAll("iframe");
                parent.location.reload();
            }

        });
        setTimeout(function () {
            top.layer.close(index);
            top.layer.msg("预约成功！");
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        }, 500);
        return false;
    })


})

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

