<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p style="line-height: 0.1;">categories</p>
<hr style="border: 0px;" />
<ul>
	<li><p>
			<c:if test="${curCate == 0}">
				<b><a href="/board/listPage?cateCode=ALL&num=1">ALL</a></b>
			</c:if>
			<c:if test="${curCate != 0}">
				<a href="/board/listPage?cateCode=ALL&num=1">ALL</a>
			</c:if></li>
	<li><P>
			<c:if test="${curCate == 1}">
				<b><a href="/board/listPage?cateCode=Toy&num=1">Toy</a></b>
			</c:if>
			<c:if test="${curCate != 1}">
				<a href="/board/listPage?cateCode=Toy&num=1">Toy</a>
			</c:if></li>
	<li><p>
			<c:if test="${curCate == 2}">
				<b><a href="/board/listPage?cateCode=Clothes&num=1">Clothes</a></b>
			</c:if>
			<c:if test="${curCate != 2}">
				<a href="/board/listPage?cateCode=Clothes&num=1">Clothes</a>
			</c:if></li>
	<li><p>
			<c:if test="${curCate == 3}">
				<b><a href="/board/listPage?cateCode=Fruits&num=1">Fruits</a></b>
			</c:if>
			<c:if test="${curCate != 3}">
				<a href="/board/listPage?cateCode=Fruits&num=1">Fruits</a>
			</c:if></li>
	<li><p>
			<c:if test="${curCate == 4}">
				<b><a href="/board/listPage?cateCode=Electronics&num=1">Electronics</a></b>
			</c:if>
			<c:if test="${curCate != 4}">
				<a href="/board/listPage?cateCode=Electronics&num=1">Electronics</a>
			</c:if></li>
	<li><p>
			<c:if test="${curCate == 5}">
				<b><a href="/board/listPage?cateCode=Books&num=1">Books</a></b>
			</c:if>
			<c:if test="${curCate != 5}">
				<a href="/board/listPage?cateCode=Books&num=1">Books</a>
			</c:if></li>
</ul>
