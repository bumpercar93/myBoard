<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">
		
		<title>${boardVO.board_name}</title>
		
		<!-- css, js -->
		<%@include file="/common/basicLib.jsp" %>
		
		<style type="text/css">
			.tableTr:hover{
				cursor: pointer;
			}
		</style>
		
		<script type="text/javascript">
			$(document).ready(function() {
				
				$(".tableTr").on("click", function() {
					var postid = $(this).data("postid");
					$("#postid").val(postid);
					var boardid = $(this).data("boardid");
					$("#boardid").val(boardid);
					$("#frm").submit();
				});
				
			});
		</script>
		
	</head>

	<body>
		<!-- header -->
		<%@include file="/common/header.jsp" %>
			
		<div class="container-fluid">
			<div class="row">
				<!-- left -->	
				<%@include file="/common/left.jsp" %>
		
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<div class="col-sm-8 blog-main">
							<h2 class="sub-header">${boardVO.board_name}</h2>
							<form id="frm" action="${pageContext.request.contextPath}/postInfo" method="get">
								<input type="hidden" id="postid" name="postid"/>
								<input type="hidden" id="boardid" name="boardid"/>
							</form>
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>글번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일시</th>
									</tr>
										<c:forEach items="${postList}" var="postVO">
											<c:choose>
												<c:when test="${postVO.post_use == 'N'}">
													<tr>
														<td></td>
														<td>[삭제된 게시글입니다]</td>
														<td></td>
														<td></td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr class="tableTr" data-postid="${postVO.post_id}" 
															data-boardid="${boardVO.board_id}">
														<td>${postVO.post_id}</td>
														<td>
															<c:forEach begin="1" end="${postVO.lv-1}">
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															</c:forEach>
															<c:if test="${postVO.lv != 1}">└▶</c:if>
															${postVO.post_title}
														</td>
														<td>${postVO.userId}</td>
														<td><fmt:formatDate value="${postVO.post_dt}" 
														pattern="yyyy-MM-dd a hh:mm:ss"/></td>
													</tr>
												</c:otherwise>
											</c:choose>
										</c:forEach>
								</table>
							</div>
							<a href="${pageContext.request.contextPath}/insertPost?board_id=${board_id}" 
								class="btn btn-default pull-right">새글 등록</a>
								
							<div class="text-center">
								<ul class="pagination">
									
									<c:choose>
										<c:when test="${page == 1}">
											<li class="disabled"><span>＜＜</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${pageContext.request.contextPath}/boardView?
												page=1&pageSize=${pageSize}&board_id=${board_id}">
												<span>＜＜</span></a>
											</li>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${page == 1}">
											<li class="disabled"><span>＜</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${pageContext.request.contextPath}/boardView?
												page=${page-1}&pageSize=${pageSize}&board_id=${board_id}">
												<span>＜</span></a>
											</li>
										</c:otherwise>
									</c:choose>
									
									<c:forEach var="i" begin="1" end="${paginationSize}" step="1">
										<c:choose>
											<c:when test="${page == i}">
												<li class="active">
													<span>${i}</span>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="${pageContext.request.contextPath}/boardView?page=${i}
													&pageSize=${pageSize}&board_id=${board_id}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
										
									<c:choose>
										<c:when test="${page == paginationSize}">
											<li class="disabled"><span>＞</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${pageContext.request.contextPath}/boardView?
												page=${page+1}&pageSize=${pageSize}&board_id=${board_id}">
												<span>＞</span></a>
											</li>
										</c:otherwise>
									</c:choose>
									
									<c:choose>
										<c:when test="${page == paginationSize}">
											<li class="disabled"><span>＞＞</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${pageContext.request.contextPath}/boardView?
												page=${paginationSize}&pageSize=${pageSize}&board_id=${board_id}">
												<span>＞＞</span></a>
											</li>
										</c:otherwise>
									</c:choose>
									
								</ul>
							</div>
									
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
