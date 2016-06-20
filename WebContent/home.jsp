<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
<!--
	function articleCheck() {
		if (window.confirm('この投稿を削除してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理
			return false; // 送信を中止
		}

	}

	-->
	<!--
	function commentCheck() {
		if (window.confirm('このコメントを削除してもよろしいですか？')) { // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行
		} else { // 「キャンセル」時の処理
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
		<div class="header">
			<c:if test="${ empty loginUser }">
				<a href="login">ログイン</a>
			</c:if>
			<c:if test="${not empty loginUser}">
				<h1 align="center">BSG掲示板システム</h1>
				<h2>
					<c:out value="${loginUser.name}"></c:out>
					さん、こんにちは。
				</h2>

						<div class="errorHomeMessages">
			<ul>
				<c:forEach items="${errorHomeMessages}" var="errorHomeMessage">
					<li><c:out value="${errorHomeMessage}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorHomeMessages" scope="session" />
		<div class="trueHomeMessages">
			<ul>
				<c:forEach items="${trueHomeMessages}" var="trueHomeMessage">
					<li><c:out value="${trueHomeMessage}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="trueHomeMessages" scope="session" />


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
				<br />
					<div class="search">
					<h3>投稿記事検索</h3>
					<form action="home" method="get">
						<label>カテゴリー <select name="categories" id="category"></label>
						<option value=""  />全件表示
							<c:forEach items="${categoryData}" var="category"
								varStatus="status">
								<option value="${category.category}"
									<c:if test="${resultCategory.equals(category.category)}">selected</c:if>>
									<c:out value="${category.category}" />
								</option>
							</c:forEach>
						</select> <br /><br />
						日付(両方選択してください。両日入力がなければカテゴリーのみで検索できます。)<br />
						<input type="date" name="startDay"  value="${startDay }"  />
						から <input type="date" name="finishDay" value="${finishDay }" />まで

							<input type="submit" value="検索" /><br /><br />
							<a href="home"><input type="button"
							value="検索リセット" /></a>
					</form>
					</div>
<br />
				<h2 align="center">～投稿の一覧～</h2>
					<c:forEach items="${articles}" var="article">
						<div class="articleView">
							<br />
							<span class="name">
								【投稿者】<c:out value="${article.name}" /></span>
								【件名】<c:out value="${article.title}" />
								【カテゴリー】<c:out value="${article.category}" />
							<span class="date">
								【投稿日時】<fmt:formatDate value="${article.insertDate}"
								 	pattern="yyyy/MM/dd HH:mm:ss" /></span><br /><br />
							<form action="deleteArticle" method="post" onSubmit="return articleCheck()">
								<c:if test="${loginUser.position == 1 }">
									<input type="submit" value="投稿を削除する" />
									<input type="hidden" name="id" value="${article.id}" />
								</c:if>
								<c:if test="${loginUser.id == article.userId && loginUser.position != 1}">
									<input type="submit" value="投稿を削除する" />
									<input type="hidden" name="id" value="${article.id}" />
								</c:if>
								<c:if
									test="${loginUser.position == 3 && loginUser.branch ==2 &&
								 article.branch == 2 && article.position == 4 }">
									<input type="submit" value="投稿を削除する" />
									<input type="hidden" name="id" value="${article.id}" />
								</c:if>
								<c:if
									test="${loginUser.position == 3 && loginUser.branch ==3 &&
								 article.branch == 3 && article.position == 4 }">
									<input type="submit" value="投稿を削除する" />
									<input type="hidden" name="id" value="${article.id}" />
								</c:if>
								<c:if
									test="${loginUser.position == 3 && loginUser.branch == 4 &&
								 article.branch == 4 && article.position == 4 }">
									<input type="submit" value="投稿を削除する" />
									<input type="hidden" name="id" value="${article.id}" />
								</c:if>
							</form>
							<br />

							<div class="text">
								【本文】
								<div class="articleText">
								<c:out value="${article.text}"  />
								</div>
							</div>
							<br />
						<br />
						<div class="line"></div>
						<c:forEach items="${comments}" var="comment">
								<c:if test="${article.id == comment.articleId}">
									<div class="commentView">
									<br />
									<span class="name">
										【投稿者】<c:out value="${comment.userName}" /></span>
									<span class="date">
										【投稿日時】<fmt:formatDate value="${comment.insertDate}"
											 pattern="yyyy/MM/dd HH:mm:ss" /></span><br /><br />
									<form action="deleteComment" method="post" onSubmit="return commentCheck()">
										<c:if test="${loginUser.position == 1 }">
											<input type="hidden" name="id" value="${comment.id}" />
											<input type="submit" value="コメントを削除する" />
										</c:if>
										<c:if test="${loginUser.id == comment.userId && loginUser.position != 1}">
											<input type="hidden" name="id" value="${comment.id}" />
											<input type="submit" value="コメントを削除する" />
										</c:if>
										<c:if test="${loginUser.position == 3 && loginUser.branch ==2 &&
								 			comment.branch == 2 && comment.position == 4}">
												<input type="hidden" name="id" value="${comment.id}" />
												<input type="submit" value="コメントを削除する" />
										</c:if>
										<c:if test="${loginUser.position == 3 && loginUser.branch ==3 &&
								 			comment.branch == 3 && comment.position == 4}">
												<input type="hidden" name="id" value="${comment.id}" />
												<input type="submit" value="コメントを削除する" />
										</c:if>
										<c:if test="${loginUser.position == 3 && loginUser.branch == 4 &&
								 			comment.branch == 4 && comment.position == 4}">
												<input type="hidden" name="id" value="${comment.id}" />
												<input type="submit" value="コメントを削除する" />
										</c:if>
									</form>
									<br />
									【本文】
										<div class="commentText" >
										<c:out value="${comment.text}" />
										</div>
									<br />
									</div>
								</c:if>
						</c:forEach>
						<br />
						<div class="comment">
						<form action="comments" method="post">
							<input type="hidden" name="articleId" value="${article.id }" />
							<textarea name="text" cols="100" rows="5" class="comment-box"></textarea><br />
							<input type="submit" value="コメントする">(500文字以内)<br /><br />
						</form>
						</div>
						</div>
						<br />
					</c:forEach>
					<br />
					<c:if test="${empty articles }">該当の投稿がありませんでした</c:if>
				</c:if>
			</div>
		<div class="copyright">CopyrightⓒKoki Yamamura</div>
	</div>
	</div>
</body>
</html>