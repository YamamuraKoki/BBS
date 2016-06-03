<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
<!--
	function check() {
		if (window.confirm('ユーザーの状態を変更してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理

			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

	}

	-->

	<!--
	function change() {
		if (window.confirm('ユーザーを削除してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理

			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

	}

	-->
</script>

<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
	<div class="main-contents">
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

		<div class="header">
			<a href="newuser">新規アカウント登録</a> <a href="home">ホーム画面に戻る</a>
		</div>
		<div class="userView">
			<table border="1">
				<tr>
					<th>ログインID</th>
					<th>名前</th>
					<th>支店名</th>
					<th>部署・役職名</th>
					<th>ユーザー状態</th>
					<th>ユーザー状態の変更</th>
					<th>ユーザー編集</th>
					<th>ユーザー削除</th>

				</tr>
				<c:forEach items="${lists}" var="list">
					<tr>
						<td><c:out value="${list.loginId}"></c:out></td>
						<td><c:out value="${list.name}"></c:out></td>
						<td><c:out value="${list.branchName}"></c:out></td>
						<td><c:out value="${list.positionName}"></c:out></td>
						<c:if test="${list.userState == true}">
							<td>使用可能</td>
						</c:if>
						<c:if test="${list.userState == false}">
							<td>使用不可能</td>
						</c:if>

						<form action="managment" method="post" onSubmit="return check()">
							<td><input type="submit" value="状態の変更" /></td> <input
								type="hidden" name="id" value="${list.id}" /> <input
								type="hidden" name="userState" value="${list.userState}" />
						</form>
						<td><a href="setting?id=${list.id}"><input type="submit"
								value="編集" /></a></td>

						<form action="deleteUser" method="post" onSubmit="return change()">
							<td><input type="submit" value="削除" /></td> <input type="hidden"
								name="id" value="${list.id}" />
						</form>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>