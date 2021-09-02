<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
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

.tg {
	border-collapse: collapse;
	border-spacing: 0;
}

.tg td {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-0pky {
	border-color: inherit;
	text-align: left;
	vertical-align: top
}

.tg .tg-1pky {
	border-color: inherit;
	text-align: center;
	vertical-align: top
}

.tg .tg-des {
	display: inline-block;
	width: 520px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: normal;
	line-height: 1.5;
	max-height: 8em;
	text-align: left;
	word-wrap: break-word;
	display: -webkit-box;
	-webkit-box-orient: vertical;
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

.statebutton {
	position: relative;
	left: 35%;
}
</style>

<meta charset="UTF-8">
<title>상품 목록</title>
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
				<div id="nav">
					<%@ include file="../include/nav.jsp"%>
				</div>
				<div id="paging">
					<%@ include file="../include/paging.jsp"%>
				</div>
				<br />
				<div class="button">
					<button type="button" class="writebutton" style="font-size: 12px; border-radius: 5px; background-color: white;"
						onClick="location.href='/board/write'">글쓰기</button>
				</div>
				<br />
			</nav>
			<main>
				<c:if test="${check}">
					<table class="tg" width="900" , height="300">
						<thead>
							<tr>
								<th class="tg-0pky" colspan="3"><b>${cateCode}</b></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<tr>
									<td class="tg-0pky" rowspan="4" width="200"><img height="200" width="200"
										src="C:\SOFT2\board\src\main\webapp\resources${list.gdsThumbImg}"
										class="thumbImg" /></td>
									<td class="tg-0pky" width="500"><b>상품설명</b></td>
									<td class="tg-1pky" rowspan="2" width="200" align=center
										valign=middle>postnumber &#58; ${list.gdsNum}<br /> <br />
										<br />이름 &#58; ${list.gdsWriter}<br /> 조회수 &#58;
										${list.viewCnt} <br /> <fmt:formatDate
											value="${list.gdsDate}" pattern="yyyy.MM.dd" />
									</td>
								</tr>
								<tr class="tg-des">
									<td class="tg-0pky" height="100">${list.gdsDes}</td>
								</tr>
								<tr>
									<td class="tg-0pky" style="border-bottom: none;"><b>가격</b></td>
									<td class="tg-0pky" rowspan="2">
										<div class="statebutton">
											<c:if test="${list.gdsState == '판매중'}">
												<button type="button" style="background-color: #088A4B"
													onclick="location.href='/board/view/?gdsNum=${list.gdsNum}&cateCode=${cateCode}'">${list.gdsState}</button>
											</c:if>
											<c:if test="${list.gdsState == '예약중'}">
												<button type="button" style="background-color: #D7DF01"
													onclick="location.href='/board/view/?gdsNum=${list.gdsNum}&cateCode=${cateCode}'">${list.gdsState}</button>
											</c:if>
											<c:if test="${list.gdsState == '판매완료'}">
												<button type="button" style="background-color: #FF0000"
													onclick="location.href='/board/view/?gdsNum=${list.gdsNum}&cateCode=${cateCode}'">${list.gdsState}</button>
											</c:if>
										</div>
									</td>
								</tr>
								<tr>
									<td class="tg-0pky" style="border-top: none;">${list.gdsPrice}￦</td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</c:if>
			</main>
			<aside></aside>
		</section>
		<footer> </footer>
	</div>

</body>
</html>