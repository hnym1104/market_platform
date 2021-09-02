<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<style type="text/css">
.container {
	display: flex;
	flex-direction: column;
}

header {
	border-bottom: 1px solid gray;
	border-top: 1px solid gray;
	height: 60px;
}

footer {
	border-top: 1px solid gray;
}

.content {
	display: flex;
}

.content nav {
	border-right: 1px solid gray;
}

.content aside {
	border-left: 1px solid gray;
}

nav, aside {
	flex-basis: 120px;
	flex-shrink: 0;
}

main {
	flex-grow: 1;
	padding: 5px;
}

main {
	padding-top: 50px;
	padding-bottom: 50px;
	padding-left: 50px;
	padding-right: 50px;
}

div.button {
	margin: auto;
	width: 90%;
}

div.button input {
	padding: 5px;
	width: 100%;
	font-size: 18px;
}

.writebutton {
	width: 100px;
	font-size: 13px;
	background-color: white;
	border-color: black;
	border-radius: 8px;
	cursor: pointer;
}

.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-0lax {
	text-align: left;
	vertical-align: top
}

.T {
	position: relative;
	bottom: 25px;
}

a {
	text-decoration: none;
}

a:link {
	color: black;
}

a:visited {
	color: black;
}

a:hover {
	color: #819FF7;
}

.postbutton {
	background-color: #30C6A7;
	border: none;
	border-radius: 8px;
	cursor: pointer;
}

.replysub {
	padding: 20px 20px;
	background-color: white;
	border-color: black;
	border-radius: 8px;
	cursor: pointer;
}

.replymod {
	background-color: white;
	border-color: black;
	border-radius: 8px;
	cursor: pointer;
}
</style>
<title>댓글 수정</title>
</head>
<body>
	<form>
		<div class="container">
			<header>
				<h2>
					<img style="position: relative; bottom: 10px;"
						src="https://cdn.pixabay.com/photo/2012/04/26/13/10/lightning-42458_1280.png"
						width="50"> <a class="T" href="/">천둥마켓</a>
				</h2>
			</header>
			<section class="content">
				<nav>
					<div id="nav">
						<%@ include file="../include/nav.jsp"%>
					</div>
					<br />
					<div class="button">
						<button class="writebutton" onClick="location.href='/board/write'">글쓰기</button>
					</div>
					<br />
				</nav>
				<main>
					<table class="tg" width="900" height="300">
						<thead>
							<tr>
								<th class="tg-0lax" colspan="4">PostNumber&#32;&#58;&#32;${view.gdsNum}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tg-0lax" rowspan="6" width="300"><img
									width="300" height="300"
									src="C:\SOFT2\board\src\main\webapp\resources${view.gdsThumbImg}"
									class="thumbImg" /></td>
								<td class="tg-0lax" colspan="3" style="text-align: right;">PostNumber&#32;&#58;&#32;${view.gdsNum}</td>
							</tr>
							<tr>
								<td class="tg-0lax" width="200" style="border-right: none;"><b>작성자</b>&#32;${view.gdsWriter}</td>
								<td style="border-right: none; border-left: none;"><b>작성일</b>&#32;<fmt:formatDate
										value="${view.gdsDate}" pattern="yyyy.MM.dd kk:mm" /></td>
								<td class="tg-0lax" width="200"
									style="text-align: right; border-left: none;"><b>조회수</b>&#32;${view.viewCnt}</td>
							</tr>
							<tr>
								<td class="tg-0lax" colspan="3" style="border-bottom: none;"><b>상품설명</b></td>
							</tr>
							<tr>
								<td class="tg-0lax" colspan="3" height="150"
									style="border-top: none;">${view.gdsDes}</td>
							</tr>
							<tr>
								<td class="tg-0lax" colspan="3" style="border-bottom: none;"><b>가격</b></td>
							</tr>
							<tr>
								<td class="tg-0lax" colspan="3" style="border-top: none;">${gdsPrice}￦</td>
							</tr>

						</tbody>

					</table>
					<br />
					<table class="tg" width="900">
						<thead>
							<tr>
								<th class="tg-0lax" colspan="4" style="border-bottom: none;">댓글
									&#40;${repCount}&#41;</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${reply}" var="reply">
								<tr>
									<td class="tg-0lax"
										style="border-top: none; border-right: none; border-bottom: none;"><b>작성자</b>&#32;${reply.repWriter}</td>
									<td class="tg-0lax" colspan="2" style="border: none;"></td>
									<td class="tg-0lax"
										style="border-left: none; border-top: none; border-bottom: none;"><input
										type="text" name="inputRepPwd" value="비밀번호" size="5"
										onFocus="this.value=''; return true;" /> <input type="hidden"
										name="repNum" value="${reply.repNum}">
										<button type="submit" formaction="/board/replymodifycheck"
											class="replymod" formmethod="post">수정</button>
								</tr>
								<tr>
									<td class="tg-0lax"
										style="border-top: none; border-right: none; border-bottom: none;">${reply.repContent}&#32;&#40;<fmt:formatDate
											value="${reply.repDate}" pattern="yyyy.MM.dd hh:mm" />&#41;
									</td>
									<td class="tg-0lax" colspan="2" style="border: none;"></td>
									<td class="tg-0lax"
										style="border-left: none; border-top: none; border-bottom: none;"></td>
								</tr>
							</c:forEach>
							<tr style="border: none;">
								<td class="tg-0lax"
									style="border-right: none; border-top: none; border-bottom: none;"><input
									type="text" name="repWriter" value="${delRepWriter}" /><input
									type="hidden" name="gdsNum" value="${view.gdsNum}"></td>
								<td class="tg-0lax" colspan="2" rowspan="2"
									style="border-left: none; border-top: none; border-right: none;"><input
									type="text" name="repContent" value="${delRepContent}"
									size="80" style="height: 50px" /></td>
								<td class="tg-0lax" rowspan="2"
									style="border-top: none; border-left: none;"><button
										type="submit" formaction="/reply/write" formmethod="post"
										class="replysub">등록</button></td>
							</tr>
							<tr style="border: none;">
								<td class="tg-0lax"
									style="border-top: none; border-right: none;"><input
									type="text" name="repPwd" value="${delRepPwd}" /></td>
							</tr>
						</tbody>
					</table>
					<br />
					<div style="float: right;">
						<input type="text" name="inputGdsPwd" value="게시글 비밀번호"
							onFocus="this.value=''; return true;" />
						<button type="submit" formaction="/board/modifycheck"
							class="postbutton" formmethod="post">게시글 수정</button>
						<button type="submit" formaction="/board/deletecheck"
							class="postbutton" formmethod="post">게시글 삭제</button>
					</div>
				</main>
				<aside></aside>
			</section>
			<footer> </footer>
		</div>
	</form>
</body>
</html>