<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		
		<title>게시판 관리</title>
		
		<!-- css, js -->
		<%@include file="/common/basicLib.jsp" %>
		
		<style type="text/css">
			button{
				width: 70px;
			}
		</style>
		
		<script type="text/javascript">
			$(document).ready(function() {
				
				if($("#userInput").val() == "no") {
					$("#userFrm").submit();
				}
				
				
				$("#insertBtn").on("click", function() {
					$("#insertOrModify").val("insert");
					$("#frm").submit();
				});
				
				$(".modifyBtn").on("click", function() {
					$("#insertOrModify").val("modify");
					var id = $(this).data("boardid");
					var name = $(this).parent().parent().children("td:nth-child(1)").find("input").val();
					var use = $(this).parent().parent().children("td:nth-child(2)").find("select").val();
					$("#modifyUse").val(use);
					$("#modifyName").val(name);
					$("#modifyId").val(id);
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
							<h2 class="sub-header">게시판 관리</h2>
							
							<form id="userFrm" action="${pageContext.request.contextPath}/login" method="get">
								<c:if test="${USER_INFO == null}">
									<input id="userInput" type="hidden" value="no"/>
								</c:if>
							</form>
							
							<form id="frm" action="${pageContext.request.contextPath}/boardSetting" method="post">
								<input type="hidden" id="insertOrModify" name="insertOrModify"/>
								<input type="hidden" id="modifyName" name="modifyName"/>
								<input type="hidden" id="modifyUse" name="modifyUse"/>
								<input type="hidden" id="modifyId" name="modifyId"/>
								<div class="table-responsive">
									<table class="table table-striped">
										<colgroup>
											<col width="10%">
											<col width="10%">
											<col width="10%">
										</colgroup>
										<tr>
											<th>게시판이름</th>
											<th>사용유무</th>
											<th>생성 / 수정</th>
										</tr>
										
										<tr>
											<td><input name="insertBoardTxt" type="text"/></td>
											<td>
												<select name="selectBox">
													<option value="Y">사용</option>
													<option value="N">미사용</option>
												</select>
											</td>
											<td><button id="insertBtn" type="button">생성</button></td>
										</tr>
										
										<c:forEach items="${boardList}" var="board">
											<tr class="parentTr">
												<td><input type="text" value="${board.board_name}"/></td>
												<td>
													<select>
														<c:choose>
															<c:when test="${board.board_use == 'Y'}">
																<option selected value="Y">사용</option>
																<option value="N">미사용</option>
															</c:when>
															<c:otherwise>
																<option value="Y">사용</option>
																<option selected value="N">미사용</option>
															</c:otherwise>
														</c:choose>
													</select>
												</td>
												<td>
													<button class="modifyBtn" type="button" 
															data-boardid="${board.board_id}">수정</button>
												</td>
											</tr>
										</c:forEach>
										
									</table>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
