<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>사용자 상세조회</title>

<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>

<style type="text/css">
	img{
		width: 180px;
		height: 200px;
	}
	button{
		margin: 20px;
		width: 100px;
	}
	#reply{
		display: inline;
		margin-top : 40px;
		margin-left : 100px;
		width: 40%;
		height: 50px;
	}
	#replyBtn{
		display : inline;
		height: 50px;
		margin-bottom: 20px;
	}
	#labelrr{
		margin-top: 20px;
	}
	.deleteReplyBtn{
		width: 40px;
 		background: white;
 		border-color: white;
 		border-style: none;
		color: blue;
		font-size: 0.9em;
	}
	td{
		height: 50px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
		if($("#userInput").val() == "no") {
			$("#userFrm").submit();
		}
		
		$("#replyBtn").on("click", function() {
			var inputReply = $("#reply").val();
			if(inputReply.trim().length == 0) {
				alert("댓글을 입력해주세요!");
				return;
			}
			var postid = $(this).data("postid");
			$("#postid").val(postid);
			var boardid = $(this).data("boardid");
			$("#boardid").val(boardid);
			$("#frm").submit();
		});
		
		$(".deleteReplyBtn").on("click", function() {
			var replyid = $(this).data("replyid");
			$("#replyid").val(replyid);
			var postid = $(this).data("postid");
			$("#postid").val(postid);
			var boardid = $(this).data("boardid");
			$("#boardid").val(boardid);
			$("#frm").attr("action", "${cp}/deleteReply");
			$("#frm").submit();
		});
		
		$("#deleteBtn").on("click", function() {
			var postid = $(this).data("postid");
			$("#postid").val(postid);
			var boardid = $(this).data("boardid");
			$("#boardid").val(boardid);
			$("#frm").attr("action", "${cp}/deletePost");
			$("#frm").submit();
		});
		
		$("#modifyBtn").on("click", function() {
			var boardid = $(this).data("boardid");
			$("#boardid").val(boardid);
			var postid = $(this).data("postid");
			$("#postid").val(postid);
			var title = $("#titleLa").text();
			$("#post_title").val(title);
			var content = $("#contentLa").html();
			$("#post_content").val(content);
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "${cp}/insertPost");
			$("#frm").submit();
		});
		
		$("#reInsertBtn").on("click", function() {
			var boardid = $(this).data("boardid");
			$("#boardid").val(boardid);
			var postid = $(this).data("postid");
			$("#postid").val(postid);
			
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "${cp}/insertRePost");
			$("#frm").submit();
		});
		
	});
</script>

</head>

<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">${postVO.post_title}</h2>
						
						<form id="userFrm" action="${cp}/login" method="get">
							<c:if test="${USER_INFO == null}">
								<input id="userInput" type="hidden" value="no"/>
							</c:if>
						</form>
						
						<form id="frm" class="form-horizontal" role="form" method="post"
								action="${cp}/postInfo">
							
							<input type="hidden" id="postid" name="postid" />
							<input type="hidden" id="boardid" name="boardid" />
							<input type="hidden" id="post_title" name="post_title" />
							<input type="hidden" id="post_content" name="post_content" />
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<label id="titleLa" class="control-label">${postVO.post_title}</label>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">글내용</label>
								<div class="col-sm-10">
									<label id="contentLa" class="control-label">${postVO.post_content}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
									<c:forEach items="${myfileList}" var="myfileVO">
											<a href="${cp}
											/fileDownload?myfile_id=${myfileVO.myfile_id}"> 
											${myfileVO.myfile_name}</a><br>
									</c:forEach>
								</div>
							</div>
							
							<button id="reInsertBtn" type="button" class="btn btn-default"
							data-postid="${postVO.post_id}" data-boardid="${board_id}">답글</button>
							
							<c:if test="${USER_INFO.userId == postVO.userId}">
								<button id="modifyBtn" type="button" class="btn btn-default" 
								data-boardid="${board_id}" data-postid="${postVO.post_id}">수정</button>
							</c:if>
							
							<c:if test="${USER_INFO.userId == postVO.userId}">
								<button id="deleteBtn" type="button" data-postid="${postVO.post_id}" 
								data-boardid="${board_id}" class="btn btn-default">삭제</button>
							</c:if>
							
							<div class="form-group" id="labelrr">
								<label for="userNm" class="col-sm-2 control-label">댓글</label>
								<div class="col-sm-10">
								<table>
									<c:forEach items="${replyList}" var="replyVO">
										<tr>
											<c:choose>
												<c:when test="${replyVO.reply_use == 'N'}">
													<td>[삭제된 댓글입니다]</td>
													<td></td>
												</c:when>
												<c:otherwise>
													<td>${replyVO.reply_content}[${replyVO.userId}/
													<fmt:formatDate value="${replyVO.reply_dt}" 
															pattern="yyyy-MM-dd a hh:mm:ss"/>]</td>
													<c:if test="${USER_INFO.userId == replyVO.userId}">
														<td><button type="button" class="deleteReplyBtn" 
															data-postid="${postVO.post_id}" data-boardid="${board_id}"
															data-replyid="${replyVO.reply_id}">삭제</button></td>
														<input type="hidden" id="replyid" name="replyid" />
													</c:if>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</table>
								</div>
							</div>
							
							<input type="text" class="form-control" id="reply" name="reply"/>	
							<button id="replyBtn" data-postid="${postVO.post_id}" data-boardid="${board_id}"
							type="button" class="btn btn-default">댓글저장</button>
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
