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



    form.on("submit(addReservation)", function (data) {
        $.ajax({
            url: '/cnhkManager/reservationAdd',
            data: {
                "userName": $("#userName").val(),
                "phoneNumber": $("#phoneNumber").val(),
                "cnhkProductId": $("#cnhkProduct").val(),
                "reservationDate": $('#reservationDate').val(),
                "serviceTimeSectionId": $('#serviceTimeSectionAdd').val()
            },
            /*dataType: 'json',*/
            type: 'POST',
            async: false,
            cache: false,
            /* contentType: "application/json",*/
            success: function (data) {
                /*top.layer.close(index);*/
                top.layer.msg("预约成功！");
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