<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>ActiveMQ Demo程序</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script type="text/javascript"
	src="<%=basePath%>static/jquery-1.11.0.min.js"></script>
</head>   
<body>  
<br/><br/><br/>  
用户姓名：<input type="text" id="username" />  
用户密码：<input type="text" id="password" />  
用户性别：<input type="text" id="sex" />  
 <input type="button" value="推送用户信息" id="pushUser" />   
   
<br/><br/><br/>  
新闻标题：<input type="text" id="title" />  
新闻内容：<input type="text" id="content" />  
新闻路径：<input type="text" id="url" />  
新闻作者：<input type="text" id="author" />  
 <input type="button" value="推送新闻信息" id="pushNews" />   
  
  
<br/><br/><br/>  
客户姓名：<input type="text" id="name" />  
客户地址：<input type="text" id="address" />  
客户手机：<input type="text" id="mobile" />  
 <input type="button" value="推送客户信息" id="pushClient" />   
  
  
  
  
<script type="text/javascript">  
    $("#pushUser").click(function(){  
        var data = {  
                username : $("#username").val(),  
                password : $("#password").val(),  
                sex      : $("#sex").val()  
        };  
        ajaxDo("/push/user",data);  
    });  
    $("#pushNews").click(function(){  
        var data = {  
                title    : $("#title").val(),  
                content  : $("#content").val(),  
                author   : $("#author").val(),  
                url      : $("#url").val()  
        };  
        ajaxDo("/push/news",data);  
    });  
    $("#pushClient").click(function(){  
        var data = {  
                name     : $("#name").val(),  
                address  : $("#address").val(),  
                mobile   : $("#mobile").val()  
        };  
        ajaxDo("/push/client",data);  
    });  
      
function ajaxDo(url,data){  
     $.ajax({  
            url:url ,  
            type: "post",  
            dataType: "json",  
            data: data,  
            success:function(result){  
               if(result.success){  
                   var obj = JSON.stringify(result.data);  
                   alert(obj);  
               }else{  
                   alert(result.msg);  
               }  
            }  
        });  
}     
  
</script>  
  
</body>  
</html>  