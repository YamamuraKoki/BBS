<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<div class="loginMain-contents">

		<c:if test="${ not empty Messages }">
			<div class="Messages">
				<ul>
					<c:forEach items="${Messages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="Messages" scope="session" />
		</c:if>
		<form action="login" method="post">
			<br /> <label for="loginId">ログインID</label><br />
				<input name="loginId" value="${loginId }" id="loginId" /><br />
			<label for="password">パスワード</label><br />
				<input name="password" type="password" id="password" /><br /><br />
				<input type="submit" value="ログイン" /><br />
		</form>
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>