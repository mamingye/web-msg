<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%String path = request.getContextPath();%>
<link rel="stylesheet" href="${ctx}/static/plugins/amaze/css/formStyle.css">
<link rel="stylesheet" href="${ctx}/static/plugins/amaze/css/JINDU.css">
<jsp:include page="include/commonfile.jsp"/>
<html>
<head>
    <title>微博云剪----微博私信发送平台</title>
    <script src="<%=path%>/static/plugins/jquery/jquery-2.1.4.min.js"></script>
    <script src="<%=path%>/static/plugins/jquery/jquery.form.js"></script>
</head>
<body>
<jsp:include page="include/header.jsp"/>
<div style="margin-left:auto;margin-right:auto;">


<form id="frm" action="<%=path %>/user/sendMessage" class="smart-green" method="post" enctype="multipart/form-data" onsubmit="return checkLoginForm()">
    <h1>
        发送微博私信
    </h1>
    
    <label>
        <span><span style="color: red;">*</span>微博账号:</span>
        <input id="result" type="hidden" value="${result}" >
        <input id="account" type="text" name="account" value="${account}" />
    </label>
    <label>
        <span><span style="color: red;">*</span>微博密码:</span><br><br>
        <input id="password" type="password" name="password"  value="${password}"/>
    </label>
    <label>
        <span>私信内容:</span>
        <textarea id="message" name="message" style="height: 150px;"></textarea>
    </label>
    <div>
        <div style="text-align: left;font-weight: bold;margin-bottom: 10px;">接收人:
        	<span style="color: red;font-weight: normal;font-size: 12px;">*注：请务必按照模板格式（每行一个UID）导入接收人</span>
        </div>
        <input id="receive" type="file" name="receive" placeholder="" style="font-size: 16px;"/>
        <span style="position:relative;left:100px;top:-30px;display: inline-block;"><a href="<%=path %>/user/downloadTemplate" >下载模板</a></span>
  	</div>
     <div>
       <div style="font-size: 18px;text-align: left;font-weight: bold;color: red;margin-bottom: 10px;">*消息发送失败的uid</div>
       
       <div id="uid" style="text-align:left;overflow:auto;border:1px solid #ccc;height:150px;position:relative;background-color: white;margin-bottom: 10px;">
          
       </div>
    </div>
    <label>
        <span>&nbsp;</span>
        <input id="sub" type="button" class="button" value="发送" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" class="button" value="重置" />
    </label>
   
</form>
</div>
©2018<a href="https://jian.weibo.com/" style="margin-left:auto;margin-right:auto;">微博云剪</a>
</div>
<!-- 进度条 -->
<div id="ddd" class="wrapper" style="width:500px;height:50px;background:#EEE;position:fixed;top:-300px;left:0px;right:0px;bottom:0px;margin:auto;display:none">
    <p>请等待... </p>
    <div class="load-bar">
        <div class="load-bar-inner" data-loading="0"> <span id="counter"></span> </div>
    </div>

</div>
<%--<jsp:include page="include/footer.jsp"/>--%>

</body>
<script language="javascript">
	$(function(){
		$("#account").focus();
	});
    $("#sub").on("click", function(){
		if($("#account").val() == ""){
			alert("微博账号不能为空！");
			$("#account").focus();
			return;
		}
		if($("#password").val() == ""){
			alert("微博密码不能为空！");
			$("#password").focus();
			return;
		}
        processerbarShow();
        $("#frm").ajaxSubmit({
            success : function(data){
                processerbarHidden();
                var list = data.data;
                if(list){
	                for(var i=0;i<list.length;i++){
	                    $("#uid").append(list[i]+"<br>");
	                }
            	}
               alert(data.msg);
            },
            error : function(){
                processerbarHidden();
                alert("系统错误");
            }
        });
    });
    function processerbarShow(){
        document.getElementById("ddd").style.display="";
        $("#sub").attr('disabled',"disabled")
        var interval = setInterval(increment,100);
        var current = 0;

        function increment(){
            current++;
            // $('#counter').html(current+'%');
            if(current == 100) { current = 0; }
        }

        $('.load-bar').mouseover(function(){
            clearInterval(interval);
        }).mouseout(function(){
            interval = setInterval(increment,100);
        });
    };
    function processerbarHidden(){
        document.getElementById("ddd").style.display="none";
        $("#sub").removeAttr("disabled")
        var interval = setInterval(increment,100);
        var current = 0;

        function increment(){
            current++;
            //$('#counter').html(current+'%');
            if(current == 100) { current = 0; }
        }

        $('.load-bar').mouseover(function(){
            clearInterval(interval);
        }).mouseout(function(){
            interval = setInterval(increment,100);
        });
    };
</script>
</html>
