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
		<h1 align="center">BSG掲示板システム</h1>
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="errorMessage">
							<li><c:out value="${errorMessage}" />
						</c:forEach>
					</ul>
				</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
		<form action="login" method="post" align="center">
			<br /> <label for="loginId">ログインID</label><br />
				<input name="loginId" value="${loginId }" id="loginId" /><br />
			<label for="password">パスワード</label><br />
				<input name="password" type="password" id="password" /><br /><br />
				<input type="submit" value="ログイン" /><br /><br />
		</form>
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>