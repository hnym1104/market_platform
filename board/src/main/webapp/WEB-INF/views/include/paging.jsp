<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div style="font-size: 13px; position: relative; left: 13px;">
	<c:if test="${!prev}">
		<a href="/board/listPage?cateCode=${cateCode}&num=${curPage}">&lt;&lt;</a>
	</c:if>
	<c:if test="${prev}">
		<a href="/board/listPage?cateCode=${cateCode}&num=${curPage-1}">&lt;&lt;</a>
	</c:if>
	<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
		<c:if test="${curPage != num && num <= pageNum}">
			<a href="/board/listPage?cateCode=${cateCode}&num=${num}">${num}</a>
		</c:if>
		<c:if test="${curPage == num}">
			<b><a href="/board/listPage?cateCode=${cateCode}&num=${num}" style="font-size: 15px;">${num}</a></b>
		</c:if>
		<c:if test="${curPage != num && num > pageNum}">
			<a href="/board/listPage?cateCode=${cateCode}&num=${pageNum}">${num}</a>
		</c:if>
	</c:forEach>
	<c:if test="${!next}">
		<a href="/board/listPage?cateCode=${cateCode}&num=${curPage}">&gt;&gt;</a>
	</c:if>
	<c:if test="${next}">
		<a href="/board/listPage?cateCode=${cateCode}&num=${curPage+1}">&gt;&gt;</a>
	</c:if>
</div>