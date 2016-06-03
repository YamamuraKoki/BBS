<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${editUser.name}の編集</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="main-contents">

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

		<form action="setting" method="post">
			<br /> <label for="loginId">ログインID</label> <input name="loginId"
				value="${editUser.loginId}" id="loginId" />(半角英数字6文字以上20文字以下で入力してください)
			<br /> <label for="password">パスワード</label> <input name="password"
				type="password" id="password" />(半角文字6文字以上255文字以下で
			入力してください。変更がない場合はそのまま登録してください。) <br /> <label for="checkPassword">パスワード（再確認）</label>
			<input name="checkPassword" type="password" id="checkPassword" /><br />

			<label for="name">名前</label> <input name="name"
				value="${editUser.name}" />(10文字以下で入力してください)<br /> <label
				for="branch">支店</label> <select name="branch" id="branch">
				<c:forEach items="${branchs}" var="branch" varStatus="status">
					<option value="${status.count}"
						<c:if test="${status.count == editUser.branch}">selected</c:if>>
						<c:out value="${branch.name}" />
					</option>
				</c:forEach>
			</select> <br /> <label for="position">部署・役職</label> <select name="position"
				id="position">
				<c:forEach items="${positioned}" var="position" varStatus="status">
					<option value="${status.count}"
						<c:if test="${status.count == editUser.position}">selected</c:if>>
						<c:out value="${position.name}" />
					</option>
				</c:forEach>
			</select> <input type="hidden" name="id" value="${editUser.id}" /> <br /> <input
				type="submit" value="登録" /><a href="managment"><input
				type="button" value="管理画面に戻る" /></a>
		</form>
		<div class="copyright">Copyright(c)Koki Yamamura</div>
	</div>
</body>
</html>