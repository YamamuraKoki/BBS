<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
<!--
	function articleCheck() {
		if (window.confirm('この投稿を削除してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理

			window.alert('キャンセルされました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}

	}

	-->
	<!--
	function commentCheck() {
		if (window.confirm('このコメントを削除してもよろしいですか？')) { // 確認ダイアログを表示
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
<title>ホーム</title>
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
			<c:if test="${ empty loginUser }">
				<a href="login">ログイン</a>
			</c:if>
			<c:if test="${not empty loginUser}">
				<h2>
					<c:out value="${loginUser.name}"></c:out>
					としてログイン中
				</h2>
				<a href="article">新規投稿画面へ</a>
				<c:if test="${loginUser.position == 2 }">
					<a href="managment">ユーザー管理画面へ</a>
				</c:if>
				<a href="logout">ログアウト</a>
			</c:if>
		</div>
		<div class="articles">

			<div class="messages">
				<c:if test="${not empty loginUser}">
					<h2>投稿の一覧</h2>
					<c:forEach items="${messages}" var="message">
						<div class="messageView">
							<br /> <span class="name">【投稿者】<c:out
									value="${message.name}" /></span> <span class="date">【投稿日時】<c:out
									value="${message.insertDate}" /></span>
							<form action="deleteArticle" method="post"
								onSubmit="return articleCheck()">
								<input type="submit" value="投稿を削除する" /> <input type="hidden"
									name="id" value="${message.id}" />
							</form>
							<br /> <br />

							<div class="title">
								【件名】
								<c:out value="${message.title}" />
							</div>
							<br />
							<div class="category">
								【カテゴリー】
								<c:out value="${message.category}" />
							</div>
							<br />
							<div class="text">
								【本文】
								<c:out value="${message.text}" />
							</div>
							<br />
						</div>

						<c:forEach items="${comments}" var="comment">
							<div class="commentView">
								<c:if test="${message.id == comment.articleId}">
									<br />
									<br />
									<span class="name">【投稿者】<c:out
											value="${comment.userName}" /></span>
									<span class="date">【投稿日時】<c:out
											value="${comment.insertDate}" /></span>
									<form action="deleteComment" method="post"
										onSubmit="return commentCheck()">
										<input type="hidden" name="id" value="${comment.id}" />
										<input type="submit" value="コメントを削除する" />
									</form>
									<br />
									<br />
									<div class="text">
										【内容】
										<c:out value="${comment.text}" />
									</div>
									<br />
								</c:if>
							</div>
						</c:forEach>
						<div class="comment"></div>
						<form action="comments" method="post">
							<input type="hidden" name="articleId" value="${message.id }" />
							<textarea name="text" cols="30" rows="3" class="comment-box">
</textarea>
							<input type="submit" value="コメントする">(500文字以内)<br />
						</form>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div></div>
		<br /> <br />
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
</body>
</html>