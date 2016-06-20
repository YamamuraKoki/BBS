<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">

	function check() {
		if (window.confirm('ユーザーの利用状況を変更してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理

			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

	}

	function change() {
		if (window.confirm('ユーザーを削除してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理

			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

	}

</script>

<link href="./css/style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー管理画面</title>
</head>
<body>
	<div class="main-contents">

		<div class="header">
			<h2>ユーザー管理</h2>
			<c:if test="${ not empty trueManagmentMessages }">
			<div class="trueManagmnetMessages">
				<ul>
					<c:forEach items="${trueManagmentMessages}" var="trueManagmentMessage">
						<li><c:out value="${trueManagmentMessage}" />
					</c:forEach>
				</ul>
			</div>
			<c:remove var="trueManagmentMessages" scope="session" />
		</c:if>
			<a href="newuser">新規アカウント登録</a> <a href="home">ホーム画面に戻る</a>
		</div>
		<div class="userView">
			<h2 align="center">ユーザー情報一覧</h2>
			<br /><table border="5" align="center">
				<tr>
					<th class="loginTh" bgcolor="skyblue">ログインID</th>
					<th class="nameTh" bgcolor="skyblue">名前</th>
					<th class="branchTh" bgcolor="skyblue">支店名</th>
					<th class="positionTh" bgcolor="skyblue">部署・役職名</th>
					<th class="stateTh" bgcolor="skyblue">利用状況</th>
					<th class="stateChangeTh" bgcolor="skyblue">利用状況の変更</th>
					<th  bgcolor="skyblue">編集</th>
					<th  bgcolor="skyblue">削除</th>

				</tr>
				<c:forEach items="${lists}" var="list">
					<tr>
						<td  bgcolor="#FFFFF0"><c:out value="${list.loginId}"></c:out></td>
						<td bgcolor="#FFFFF0"><c:out value="${list.name}"></c:out></td>
						<td bgcolor="#FFFFF0"><c:out value="${list.branchName}"></c:out></td>
						<td bgcolor="#FFFFF0"><c:out value="${list.positionName}"></c:out></td>
						<c:if test="${list.userState == true}">
							<td bgcolor="#FFFFF0">利用中</td>
						</c:if>
						<c:if test="${list.userState == false}">
							<td bgcolor="#FFFFF0" class="userLock">停止中</td>
						</c:if>
						</div>

						<form action="managment" method="post" onSubmit="return check()">
							<td bgcolor="#FFFFF0"><c:if test="${loginUser.id == list.id == false &&
								 list.userState == true}" >
							<input type="submit" value="停止に変更" class="changeButton" /></td> <input
								type="hidden" name="id" value="${list.id}" /> <input
								type="hidden" name="userState" value="${list.userState}" />
							</c:if>
							<c:if test="${loginUser.loginId == list.loginId == false && list.userState == false}" >
							<input type="submit" value="使用に変更" class="changeButton" /> <input
								type="hidden" name="id" value="${list.id}" /> <input
								type="hidden" name="userState" value="${list.userState}" />
							</c:if>
						</form>
						<td bgcolor="#FFFFF0"><a href="setting?id=${list.id}"><input type="submit"
								value="ユーザー情報編集" /></a></td>

						<form action="deleteUser" method="post" onSubmit="return change()">
						<td bgcolor="#FFFFF0"><c:if test="${loginUser.id == list.id == false}" >
							<input type="submit" value="ユーザー削除" class="deleteButton" /></td>
							<input type="hidden" name="id" value="${list.id}" />
							</c:if>
						</form>
					</tr>
				</c:forEach>
			</table>
		</div>
		<br />
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>