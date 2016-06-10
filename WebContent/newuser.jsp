<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー新規登録</title>
</head>
<body>
	<div class="newuserMain-contents">
		<div class="header">
			<a href="managment">ユーザー管理画面に戻る</a> <br />
		</div>

		<c:if test="${ not empty Messages }">
			<div class="errorMessages">
				<ul>
					<c:forEach items="${Messages}" var="message">
						<li><c:out value="${message}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="Messages" scope="session" />
		</c:if>
		<form action="newuser" method="post">
			<h2>ユーザー新規登録画面</h2>
			<label for="loginId">ログインID</label><br />
				<input name="loginId" value="${editUser.loginId }" id="login_id" />
					(6文字以上20文字以下の半角英数字で入力してください)<br />
			<label for="password">パスワード</label><br />
				<input name="password" type="password"id="password" />
					(6文字以上255文字以下の半角文字で入力してください)<br />
			<label for="checkPassword">パスワード（再確認)</label><br />
				<input name="checkPassword" type="password" id="checkPassword" />
					(確認のためもう一度入力してください)<br />
			<label for="name">名前</label><br />
				<input name="name" value="${editUser.name }" id="name" />
					(10文字以下で入力してください)<br />
			<label for="branch">支店</label><br />
			<select name="branch" id="branch">
				<c:forEach items="${branchs}" var="branch" varStatus="status">
					<option value="${status.count}">
						<c:out value="${branch.name}" />
					</option>
				</c:forEach>
			</select><br />
			<label for="position">部署・役職</label><br />
			 <select name="position" id="position">
				<c:forEach items="${positioned}" var="position" varStatus="status">
					<option value="${status.count}">
						<c:out value="${position.name}" />
					</option>
				</c:forEach>
			</select><br /><br />
			<input type="submit" value="登録" /> <br />
		</form>
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>