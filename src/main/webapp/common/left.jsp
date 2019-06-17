<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp}/boardSetting">게시판 관리</a></li>
		
		<c:forEach items="${BOARD_LIST}" var="boardVO">
			<c:if test="${boardVO.board_use == 'Y'}">
				<li class="active"><a href="${cp}/boardView?board_id=${boardVO.board_id}">
									${boardVO.board_name}</a></li>
			</c:if>
		</c:forEach>
	
	</ul>
</div>