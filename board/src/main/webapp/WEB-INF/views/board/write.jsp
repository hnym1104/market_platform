<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

ul {
	list-type: hyphen;
}

textarea {
	width: 80%;
	height: 100px;
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

.writesubmitbutton {
	width: 100px;
	background-color: white;
	border-color: black;
	border-radius: 8px;
	cursor: pointer;
	float: right;
	background-color: white;
}
</style>
<title>상품 등록</title>
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
				<ul>
					<li><p>
							<a href="/">ALL</a></li>
					<li><p>
							<a href="/board/listPage/?cateCode=Toy&num=1">Toys</a></li>
					<li><p>
							<a href="/board/listPage/?cateCode=Clothes&num=1">Clothes</a></li>
					<li><p>
							<a href="/board/listPage/?cateCode=Fruits&num=1">Fruits</a></li>
					<li><p>
							<a href="/board/listPage/?cateCode=Electronics&num=1">Electronics</a></li>
					<li><p>
							<a href="/board/listPage/?cateCode=Books&num=1">Books</a></li>
				</ul>
				<br />
				<div class="button">
					<button type="button" class="writebutton"
						style="font-size: 12px; border-radius: 5px; background-color: white;"
						onClick="location.href='/board/write'">글쓰기</button>
				</div>
				<br />
			</nav>
			<main>
				<b>게시글 작성</b>
				<hr />
				<form method="post" enctype="multipart/form-data">
					<div style="line-height: 170%;">
						<label>닉&#32;네&#32;임</label> <input type="text" name="gdsWriter" /><br />
						<label>비밀번호</label> <input type="text" name="gdsPwd" /> <label>카테고리</label>
						<select name="cateCode">
							<option value="">선택</option>
							<option value="Toy">Toy</option>
							<option value="Clothes">Clothes</option>
							<option value="Fruits">Fruits</option>
							<option value="Electronics">Electronics</option>
							<option value="Books">Books</option>
						</select><br /> <label>내&#09;용</label>
						<textarea cols="50" rows="5" name="gdsDes" style=""></textarea>
						<br /> <label>가 &#09;격</label> <input type="text" name="gdsPrice" />￦<br />
						<br />

						<div class="inputArea">
							<label for="gdsImg">이&#32;미&#32;지</label> <input type="file"
								id="gdsImg" name="file" />
							<div class="select_img">
								<img src="" />
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
																	.width(300);
														}
														reader
																.readAsDataURL(this.files[0]);
													}
												});
							</script>
						</div>
						<br /> <br /> <input type="radio" name="gdsState" value="판매중"
							checked="checked" /> 판매중 <input type="radio" name="gdsState"
							value="예약중" /> 예약중 <input type="radio" name="gdsState"
							value="판매완료" /> 판매완료
						<button type="submit" class="writesubmitbutton"
							style="float: right;">등 &#09;록</button>
					</div>
				</form>
			</main>
			<aside></aside>
		</section>
		<footer> </footer>
	</div>
</body>
</html>