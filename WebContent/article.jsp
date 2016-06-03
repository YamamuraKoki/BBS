<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新規投稿画面</title>
</head>
<body>
	<div class="main-contents">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${Messages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="Messages" scope="session" />
		<div class="header">
			<a href="home">ホーム画面に戻る</a> <br />
		</div>
		<div class="form-area">
			<h2>新規投稿</h2>
			<form action="article" method="post">
				<br />件名 <input type="text" name="title" /> <br />カテゴリー <input
					name="category"></input> <br />本文
				<textarea name="text" cols="100" rows="5">
</textarea>
				<br /> <input type="submit" value="投稿">
			</form>
		</div>

	</div>

	<div class="copyright">CopyrightⓒKoki Yamamura</div>
</body>
</html>