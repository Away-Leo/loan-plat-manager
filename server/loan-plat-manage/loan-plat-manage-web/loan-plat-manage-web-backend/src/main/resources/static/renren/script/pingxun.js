var httpUrl = "https://www.pingxundata.com:8443";//正式环境
// var httpUrl = "http://192.168.1.171:8888";//本地开发环境
// var httpUrl = "http://192.168.1.100:1234";//测试环境
var loginUrl = httpUrl + "/common/passwordLogin.json";
var logoutUrl = httpUrl + "/common/logout.json";
var uploadUrl = httpUrl + "/common/upload.json";
var findByTypeUrl = httpUrl + "/common/findByType.json";
var appUrl = httpUrl+"/common/getAppList.json";
var appMarketUrl = httpUrl+"/common/getAppMarketList.json";
//产品

//图标
var saveBannerUrl = httpUrl + "/backend/banner/create.json";
var updateBannerUrl = httpUrl + "/backend/banner/update.json";
var bannerListUrl = httpUrl + "/backend/banner/findByCondition.json";
var findBannerByIdUrl = httpUrl + "/backend/banner/findById.json";
var enableBannerUrl = httpUrl + "/backend/banner/enable.json";
//首页
var homeDevelopmentUrl = httpUrl + "/backend/home/dayDevelopmentData.json";
//参数
var findParameterUrl = httpUrl + "/front/parameter/findParameter.json";
var saveParameterUrl = httpUrl + "/backend/parameter/update.json";
//用户
var userListUrl = httpUrl + "/backend/user/findByCondition.json";
var findByUserIdUrl = httpUrl + "/backend/user/findById.json";
var saveUserUrl = httpUrl + "/backend/user/create.json";
var updateUserUrl = httpUrl + "/backend/user/update.json";
var lockUrl = httpUrl + "/backend/user/lock.json";
var getUserPermissionUrl = httpUrl + "/backend/user/getUserPermission.json";
//角色
var roleListUrl = httpUrl + "/backend/role/findByCondition.json";
var findByRoleIdUrl = httpUrl + "/backend/role/findById.json";
var saveRoleUrl = httpUrl + "/backend/role/create.json";
var updateRoleUrl = httpUrl + "/backend/role/update.json";
var allResourceUrl = httpUrl + "/backend/role/findAllResource.json";
//客户管理
var customerListUrl = httpUrl + "/backend/customer/findByCondition.json";
//订单管理
var orderPayListUrl = httpUrl + "/backend/orderPay/findByCondition.json";
var orderFindByIdUrl = httpUrl + "/backend/orderPay/findById.json";
//会员等级
var levelListUrl = httpUrl + "/backend/level/findByCondition.json";
var saveLevelUrl = httpUrl + "/backend/level/create.json";
var findLevelByIdUrl = httpUrl + "/backend/level/findById.json";
var updateLevelUrl = httpUrl + "/backend/permisson/update.json";
function post(url,data) {
    var returnData;
    data["type"]="manager";
    var json = JSON.stringify(data);
    json = json.replace(/\r\n/g,"\n");
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        data: json,
        method:"post",
        dataType: "json",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        beforeSend:function () {
            $("body").mLoading();
        },
        success: function (data) {
            returnData = data;
        },
        complete:function () {
            $("body").mLoading("hide");
        },
        error: function () {
            showTipMessage("数据请求错误!",true);
        }
    });
    return returnData;
}

function get(url,data) {
    var returnData;
    data = data;
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        data: data,
        method:"get",
        dataType: "json",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        crossDomain: true,
        beforeSend:function () {
            $("body").mLoading();
        },
        success: function (data) {
            returnData = data;
        },
        complete:function () {
            $("body").mLoading("hide");
        },
        error: function () {
            showTipMessage("数据请求错误!",true);
        }
    });
    return returnData;
}
function uploadFile(id){
    var result="";
    $.ajaxFileUpload({
        url:uploadUrl,//服务器端程序
        secureuri: false,
        fileElementId:id,//input框的ID
        method:"post",
        async: false,
        dataType: 'json',//返回数据类型
        success: function (data){//上传成功
            $("#"+id+"Img").attr("src",data.data);
            $("#"+id+"Hide").val(data.data);
        },
        error: function (data, status, e)//服务器响应失败处理函数
        {
            showTipMessage(e,false);
        }
    });
    return result;
}
function logout() {
    var param = "1=1";
    var result =  get(logoutUrl,param);
    if(result.success){
        localStorage.clear();
        window.location.href="/renren/login.html";
    }
}
function getArgsFromHref(sHref, sArgName)
{   var args    = sHref.split("?");
    var retval = 0;
    if(args[0] == sHref) /*参数为空*/
    {
        return retval; /*无需做任何处理*/
    }
    var str = args[1];
    args = str.split("&");
    for(var i = 0; i < args.length; i ++)
    {
        str = args[i];
        var arg = str.split("=");
        if(arg.length <= 1) continue;
        if(arg[0] == sArgName) retval = arg[1];
    }
    return retval;
}
function getCurrentDate() {
    var dtCur = new Date();
    var yearCur = dtCur.getFullYear();
    var monCur = dtCur.getMonth() + 1;
    var dayCur = dtCur.getDate();
    var timeCur = yearCur + "年" + (monCur < 10 ? "0" + monCur : monCur) + "月"
        + (dayCur < 10 ? "0" + dayCur : dayCur)+"日";
    return timeCur;
}
function getWeek() {
    var str = "";
    var week = new Date().getDay();
    if (week == 0) {
        str = "星期日";
    } else if (week == 1) {
        str = "星期一";
    } else if (week == 2) {
        str = "星期二";
    } else if (week == 3) {
        str = "星期三";
    } else if (week == 4) {
        str = "星期四";
    } else if (week == 5) {
        str = "星期五";
    } else if (week == 6) {
        str = "星期六";
    }
    return str;
}
function backUserList() {
    window.location.href="user/userlist.html";
}

function left() {
    if(localStorage.getItem("userName").indexOf("admin")!=-1){
        $("#admin").css("display","");
    }

    var data = get(getUserPermissionUrl,"");
    var rootId="";
    var html = "";
    var currentParent=0;
    $.each(data.data, function (j) {
        if(window.location.href.indexOf(data.data[j].url)!=-1){
            currentParent = data.data[j].parentId-1;
        }
    });
    $.each(data.data, function (j) {
            if (data.data[j].parentId == 0) {
                rootId = data.data[j].id;
                if(j>0) {
                    html += "     </ul>";
                    html += "    </div>";
                }
                html +="   <div class=\"sBox\" id=\"channel\">";
                if(j == currentParent) {
                    html += "  <div class=\"subNav sublist-up\"><span class=\"title-icon glyphicon glyphicon-chevron-up\"></span><span class=\"sublist-title\">"+data.data[j].name+"</span>";
                    html += "     </div>";
                    html += "     <ul class=\"navContent\" style=\"display:block\">";
                }else{
                    html += "  <div class=\"subNav sublist-down\"><span class=\"title-icon glyphicon glyphicon-chevron-down\"></span><span class=\"sublist-title\">"+data.data[j].name+"</span>";
                    html += "     </div>";
                    html += "     <ul class=\"navContent\" style=\"display:none\">";
                }
                $.each(data.data, function (j) {
                    if (rootId == data.data[j].parentId) {
                        html += "    <li id=\""+data.data[j].permission+"\">";
                        html += "     <div class=\"showtitle\" style=\"width:100px;\"><img src=\"../img/leftimg.png\"/>"+data.data[j].name+"</div>";
                        html += "     <a href=\""+data.data[j].url+"\"><span class=\"sublist-icon glyphicon "+data.data[j].parentIds+"\"></span><span class=\"sub-title\">"+data.data[j].name+"</span></a> </li>";
                    }

                    if(j==data.data.length-1)
                    {
                        html += "    </div>";
                    }
                });
            }

    });
    $("#menutree").append(html);
    /*左侧导航栏显示隐藏功能*/
    $(".subNav").click(function () {
        /*显示*/
        if ($(this).find("span:first-child").attr('class') == "title-icon glyphicon glyphicon-chevron-down") {
            $(this).find("span:first-child").removeClass("glyphicon-chevron-down");
            $(this).find("span:first-child").addClass("glyphicon-chevron-up");
            $(this).removeClass("sublist-down");
            $(this).addClass("sublist-up");
        }
        else {
            $(this).find("span:first-child").removeClass("glyphicon-chevron-up");
            $(this).find("span:first-child").addClass("glyphicon-chevron-down");
            $(this).removeClass("sublist-up");
            $(this).addClass("sublist-down");
        }
        // 修改数字控制速度， slideUp(500)控制卷起速度
        $(this).next(".navContent").slideToggle(300).siblings(".navContent").slideUp(300);
    });
    /*左侧导航栏缩进功能*/
    $(".left-main .sidebar-fold").click(function () {

        if ($(this).parent().attr('class') == "left-main left-full") {
            $(this).parent().removeClass("left-full");
            $(this).parent().addClass("left-off");
            $(this).parent().parent().find(".right-product").removeClass("right-full");
            $(this).parent().parent().find(".right-product").addClass("right-off");
        }
        else {
            $(this).parent().removeClass("left-off");
            $(this).parent().addClass("left-full");

            $(this).parent().parent().find(".right-product").removeClass("right-off");
            $(this).parent().parent().find(".right-product").addClass("right-full");

        }
    });

    $(".dropdown .changecolor li").click(function () {
        var style = $(this).attr("id");
        $("link[title!='']").attr("disabled", "disabled");
        $("link[title='" + style + "']").removeAttr("disabled");

        $.cookie('mystyle', style, {expires: 7}); // 存储一个带7天期限的 cookie
    })
    var cookie_style = $.cookie("mystyle");
    if (cookie_style != null) {
        $("link[title!='']").attr("disabled", "disabled");
        $("link[title='" + cookie_style + "']").removeAttr("disabled");
    }

    /*左侧鼠标移入提示功能*/
    $(".sBox ul li").mouseenter(function () {
        if ($(this).find("span:last-child").css("display") == "none") {
            $(this).find("div").show();
        }
    }).mouseleave(function () {
        $(this).find("div").hide();
    });

    $(".dropdown .changecolor li").click(function () {
        var style = $(this).attr("id");
        $("link[title!='']").attr("disabled", "disabled");
        $("link[title='" + style + "']").removeAttr("disabled");
        $.cookie('mystyle', style, {expires: 7}); // 存储一个带7天期限的 cookie
    })
    var cookie_style = $.cookie("mystyle");
    if (cookie_style != null) {
        $("link[title!='']").attr("disabled", "disabled");
        $("link[title='" + cookie_style + "']").removeAttr("disabled");
    }
}

function topFunc() {
    var userName = localStorage.getItem("userName");
    $("#showUserName").text(userName);
    if(userName == "null"||userName==undefined){
        window.location.href="/static/login.html";
    }
    /*换肤*/
    $(".dropdown .changecolor li").click(function(){
        var style = $(this).attr("id");
        $("link[title!='']").attr("disabled","disabled");
        $("link[title='"+style+"']").removeAttr("disabled");

        $.cookie('mystyle', style, { expires: 7 }); // 存储一个带7天期限的 cookie
    })
    var cookie_style = $.cookie("mystyle");
    if(cookie_style!=null){
        $("link[title!='']").attr("disabled","disabled");
        $("link[title='"+cookie_style+"']").removeAttr("disabled");
    }
}

function showTipMessage(message,flag) {
    layer.alert(message,{icon:2,yes:function(index){
        if (flag) {
            window.location.href = "/renren/login.html";
        }else{
            layer.close(index);
        }
    }});
}

function exitSystem() {
    layer.confirm('您确定退出系统吗？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        logout();
    }, function(index){
        layer.close(index);
    });
}
function modifyPwd() {
    layer.ready(function () {
        layer.open({
            type: 1,
            title: '修改密码',
            maxmin: true,
            area: ['400px', '300px'],
            content: " <div style=\"text-align:center;\"><form id='updatePwdForm'>" +
                     " <ul style='margin-top: 20px;'><li>原密码：<input type=\"password\" name='oldPassword'></li><br>" +
                     " <li>新密码：<input type=\"password\" name='password'></li><br>"+
                     " <li>确认密码：<input type=\"password\" name='confirmPassword'></li>" +
                     " <li style='margin-top: 20px;'><input type=\"button\" onclick='updatePassword()' name='save' value='修改'/>&nbsp;&nbsp;<input type=\"reset\" name='save' value='重置'/></li></ul>" +
                    "</form></div>",
            end: function () {

            }
        });
    });
}
function updatePassword() {
    var data = $("#updatePwdForm").serializeArray();
    console.log(data);
    var values={};
    for (var item in data) {
        values[data[item].name] = data[item].value;
    }
    var result = post(updatePasswordUrl,values);
    if(result.success){
        layer.closeAll();
    }else{
        showTipMessage(result.message,false);
    }
}
//excel导出
function exportExcel(excelFlag) {
    var data = $("#searchForm").serialize();
    window.location.href="/excel/exportExcel?excelFlag="+excelFlag+"&"+data;
}
function initAppList(){
    var param = "type=bannerPosition";
    var result = get(appUrl,param);
    var resultData = result.data;
    var sHtml = "<option value=\"all\">全部</option>";
    $.each(resultData, function (j) {
        var sTrHtml = "";
        sTrHtml += "<option value='"+resultData[j].code+"'>";
        sTrHtml += resultData[j].name;
        sTrHtml += "</option>";
        sHtml += sTrHtml;
    });
    $("#appMarketList").append(sHtml);
}

function initProductList(){
    var param ="";
    var result = get(productAllUrl,param);
    var resultData = result.data;
    var sHtml = "";
    $.each(resultData, function (j) {
        var sTrHtml = "";
        sTrHtml += "<option value='"+resultData[j].id+"'>";
        sTrHtml += resultData[j].name;
        sTrHtml += "</option>";
        sHtml += sTrHtml;
    });
    $("#productSelectList").append(sHtml);
}
function initAppMarketList(){
    var param = "type=bannerPosition";
    var result = get(appMarketUrl,param);
    var resultData = result.data;
    var sHtml = "<option value=\"all\">全部</option>";
    $.each(resultData, function (j) {
        var sTrHtml = "";
        sTrHtml += "<option value='"+resultData[j].code+"'>";
        sTrHtml += resultData[j].name;
        sTrHtml += "</option>";
        sHtml += sTrHtml;
    });
    $("#channelNo").append(sHtml);
}
function initMarketOperateList(){
    var values = {};
    values["showType"]="1";
    values["pageNo"]=1;
    values["channelNo"]="backend";
    values["userType"]="manager";
    var result = post(userListUrl,values);
    var resultData = result.data.result;
    var sHtml = "<option value=\"all\">全部</option>";
    $.each(resultData, function (j) {
        var sTrHtml = "";
        sTrHtml += "<option value='"+resultData[j].id+"'>";
        sTrHtml += resultData[j].displayName;
        sTrHtml += "</option>";
        sHtml += sTrHtml;
    });
    $("#operateNo").append(sHtml);
}
//保存录入数据
function saveData() {
    var values = {};
    var data = $("#incomeForm").serializeArray();
    if($("#settleDate").val()==""||$("#settleDate").val()==undefined){
        showTipMessage("请选择收入账期！",false);
        return ;
    }
    for (var item in data) {
        values[data[item].name] = data[item].value;
    }
    var result = post(updatePostInfoUrl,values);
    if(result.success){
        initData(0);
        layer.closeAll();
    }else{
        showTipMessage(result.message,false);
    }
}
function saveContractData() {
    var values = {};
    var data = $("#incomeForm").serializeArray();
    for (var item in data) {
        values[data[item].name] = data[item].value;
    }
    var result = post(updateContractListUrl,values);
    if(result.success){
        initData(0);
        layer.closeAll();
    }else{
        showTipMessage(result.message,false);
    }
}
//保存商户明细回盘数据
function saveMerchantData() {
    var values = {};
    var data = $("#incomeForm").serializeArray();
    for (var item in data) {
        values[data[item].name] = data[item].value;
    }
    values["applyStartDate"] = $("#applyStartDate").val();
    var result = post(updateMerchantIncomeUrl,values);
    if(result.success){
        initData(0);
        layer.closeAll();
    }else{
        showTipMessage(result.message,false);
    }
}
/**
 * 选择所属APP
 */
function checkBelongApp() {
    $("input[name='belongApp']").each(function () {
        if($(this).prop("checked")){
            $(this).prop("checked",false);
        }else{
            $(this).prop("checked",true);
        }
    });
}

function changeStatus(id,productName,status,operateDesc,totalIncome) {
    layer.ready(function () {
        var htmlText="";
        if(status==5){
            htmlText='确认'+productName+operateDesc+'？<br>' +
                '确认账期：<input type="text" class="Wdate" id="settleDate" style="height:25px;" name="settleDate" id="d15" ' +
                ' onFocus="WdatePicker({dateFmt:\'yyyy-MM\''+',maxDate:\'%y-%M\',isShowClear:false,readOnly:true})"/><br>' +
                '回款金额：<input type="text" style="margin-top: 5px;height:25px;" id="incomeFee" value="'+totalIncome+'" name="incomeFee"/><br>' +
                '回款说明：<textarea rows="3" cols="20" style="margin-top: 5px"  id="incomeMemo" name="incomeMemo"></textarea>';
        }else{
            htmlText='确认'+productName+operateDesc+'？'+
                '<br>账期：<input type="text" class="Wdate" id="settleDate" style="margin-top:5px;height:25px;" name="settleDate" id="d15" ' +
                ' onFocus="WdatePicker({dateFmt:\'yyyy-MM\''+',maxDate:\'%y-%M\',isShowClear:false,readOnly:true})"/>' ;
        }
        layer.confirm(htmlText, {
            btn: ['确认', '取消'] //可以无限个按钮
        }, function(index, layero){
            var values={};
            values["productId"]=id;
            values["status"]=status;
            if($("#settleDate").val()==""||$("#settleDate").val()==undefined){
                showTipMessage("请选择确认账期！",false);
                return ;
            }
            values["settleDate"]=$("#settleDate").val();
            if(status==5){
                values["incomeFee"]=$("#incomeFee").val();
                values["incomeMemo"]=$("#incomeMemo").val();
            }
            var result = post(marketStatusUrl,values);
            if(!result.success){
                showTipMessage(result.message,false);
            }else{
                layer.closeAll();
                initData(0);
            }
        }, function(index){
        });
    });
}