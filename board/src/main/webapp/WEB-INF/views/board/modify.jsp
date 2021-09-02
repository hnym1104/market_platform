<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<style>
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

ul { //
	list-type: hyphen;
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

.modifybutton {
	width: 100px;
	background-color: white;
	border-color: black;
	border-radius: 8px;
	cursor: pointer;
	float: right;
	background-color: white;
	float: right;
}
</style>
<title>상품 정보 수정</title>
</head>
<body>
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
				<p style="line-height: 0.1">categories</p>
				<hr style="border: 0px;" />
				<div id="nav">
					<%@ include file="../include/nav.jsp"%>
				</div>
				<br />
				<div class="button">
					<button type="button" class="writebutton"
						style="font-size: 12px; border-radius: 5px; background-color: white;"
						onClick="location.href='/board/write'">글쓰기</button>
				</div>
				<br />
			</nav>
			<main>
				<b>게시글 수정</b>
				<hr />
				<form method="post" enctype="multipart/form-data">

					<div style="line-height: 170%;">

						<label>닉네임</label> <input type="text" name="gdsWriter"
							value="${view.gdsWriter}" /><br /> <label>비밀번호</label> <input
							type="text" name="gdsPwd" value="${view.gdsPwd}" /> <label>카테고리</label>
						<!-- 기존 값 가져오기 어떻게? -->
						<select name="cateCode">
							<option value="">선택</option>
							<option value="Toy" <c:if test="${curCate == 1}">selected</c:if>>Toy</option>
							<option value="Clothes"
								<c:if test="${curCate == 2}">selected</c:if>>Clothes</option>
							<option value="Fruits"
								<c:if test="${curCate == 3}">selected</c:if>>Fruits</option>
							<option value="Electronics"
								<c:if test="${curCate == 4}">selected</c:if>>Electronics</option>
							<option value="Books"
								<c:if test="${curCate == 5}">selected</c:if>>Books</option>
						</select><br /> <label>내용</label>
						<textarea cols="50" rows="5" name="gdsDes" width="600">${view.gdsDes}</textarea>
						<br /> <label>가격</label> <input type="text" name="gdsPrice"
							value="${view.gdsPrice}" />￦<br />

						<div class="inputArea">
							<label for="gdsImg">이미지</label> <input type="file" id="gdsImg"
								name="file" value="${view.gdsImg}" />
							<div class="select_img">
								<input type="hidden" name="gdsImg" value="${view.gdsImg}" /> <input
									type="hidden" name="gdsThumbImg" value="${view.gdsThumbImg}" />
							</div>

							<script>
								$("#gdsImg")
										.change(
												function() {
													if (this.files
															&& this.files[0]) {
														var reader = new FileReader;
														reader.onload = function(
																data) {
															$(".select_img img")
																	.attr(
																			"src",
																			data.target.result)
																	.width(500);
														}
														reader
																.readAsDataURL(this.files[0]);
													}
												});
							</script>
						</div>
						<input type="radio" name="gdsState" value="판매중" checked="checked" />
						판매중 <input type="radio" name="gdsState" value="예약중" /> 예약중 <input
							type="radio" name="gdsState" value="판매완료" /> 판매완료
						<button type="submit" class="modifybutton">수정</button>
					</div>
				</form>
			</main>
			<aside></aside>
		</section>
		<footer> </footer>
	</div>
</body>
</html>