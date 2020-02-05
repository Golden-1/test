<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
<form action="http://localhost:8080/ral/user/register.do" mothod="post">
用户名：<input type="text" name="username"/><br/>
密码：<input type="text" name="password"/><br/>
名字：<input type="text" name="name"/><br/>
<input type="submit" value="提交"/>
</form>
</body>
</html>