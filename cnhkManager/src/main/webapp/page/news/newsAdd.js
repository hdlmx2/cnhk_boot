layui.use(['form', 'layer', 'layedit', 'laydate', 'upload'], function () {
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;

    form.verify({
        userName: function (val) {
            if (val == '') {
                return "姓名不能为空";
            }
        },

        phoneNumber: function (val) {
            if (val == '') {
                return "请填写手机号码";
            }
        },

        reservationDate: function (val) {
            if (val == '') {
                return "请选择预约日期";
            }
        },
        serviceTimeSection: function (val, item) {
            var value = $(item).val();
            if (value == "请选择时间段") {
                return "请选择预约日期";
            }
        }

    })
    form.on("submit(addNews)", function (data) {
        /*$.post("/cnhkManager/reservationAdd", {
            userName: $("#userName").val(),  //文章标题
            phoneNumber: $("#phoneNumber").val(),  //文章摘要
            cnhkProduct: $("#cnhkProduct").val(),//文章内容
            reservationDate: $('#reservationDate').val(),    //发布状态
            /!*serviceTimeSection: $('#serviceTimeSection').val(),    //发布时间*!/
        }, function (res) {

        })*/

        var data = {
            "userName": $("#userName").val(),  //文章标题
            "phoneNumber": $("#phoneNumber").val(),  //文章摘要
            "cnhkproductId": $("#cnhkProduct").val(),//文章内容
            "reservationDate": $('#reservationDate').val(),    //发布状态
            "serviceTimeSectionId": $('#serviceTimeSection').val(),    //发布时间
        }
        $.ajax({
            url: '/cnhkManager/reservationAdd',
            data: JSON.stringify(data),
            dataType: 'json',
            type: 'POST',
            async: false,
            cache: false,
            contentType: "application/json",
            success: function (data) {

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