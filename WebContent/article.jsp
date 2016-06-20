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
		<div class="header">
			<a href="home">ホーム画面に戻る</a><br />
		</div>
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="errorMessage">
					<li><c:out value="${errorMessage}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
		<div class="form-area" align="center">
			<h2>新規投稿</h2>
			<form action="article" method="post">
				<label>件名(50文字以内で入力してください)</label><br />
					<input type="text" value="${articleData.title }" maxlength="50"
					size="82" name="title" /><br />
				<label>カテゴリー</label>(10文字以内で入力してください)<br />
					<input value="${articleData.category }" maxlength="10" size="14" name="category" /> <br />
				<label>本文(1000文字以内で入力してください)</label><br />
					<textarea name="text" cols="100" rows="10" ><c:out
					value="${articleData.text }" /></textarea>
				<br /> <input type="submit" value="投稿">
			</form>
		</div>

	<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>