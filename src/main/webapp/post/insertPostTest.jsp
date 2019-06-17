<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>사용자 등록</title>
<link rel="shortcut icon" href="favicon.ico" />
<!-- css, js -->
<%@include file="/common/basicLib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="${cp}/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	
	if($("#userInput").val() == "no") {
		$("#userFrm").submit();
	}
	
	if($("#right").val() == "update") {
		$("#frm").attr("action", "${cp}/updatePost");
		$("#titleRight").text("글 수정");
	}else if($("#right").val() == "rePost") {
		$("#frm").attr("action", "${cp}/insertRePost");
		$("#titleRight").text("답글 등록");
	}
	
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${cp}/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	});
	
	$("#addFile").on("click", function() {
		var fileLength = $(".filelable").length;
		
		if($(".myfiles").length + fileLength > 4) {
			alert("첨부파일은 최대 5개 입니다.");
			return;
		}
		newfile = $("<input/>", {"type" : "file", "class" : "myfiles", "name" : "myfile"});
		$(this).parent().append(newfile);
	});

	$(".deleteBtn").on("click", function() {
		
		var fileid = $(this).parent().find("input").val();
		var newHidden = $("<input/>", {"type" : "hidden", "name" : "file_id", "value" : fileid});
		$("#frm").append(newHidden);
		
		$(this).parent().remove();
	});
	
});


// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

</script>

<style type="text/css">
	#title{
		width: 768px;
	}
	input{
		margin-bottom: 10px;
	}
	#savebutton{
		width: 200px;
		height: 50px;
	}
</style>

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
					<div class="col-sm-10 blog-main">
						<h2 id="titleRight" class="sub-header">새글 등록</h2>
					
							<form id="userFrm" action="${cp}/login" method="get">
								<c:if test="${USER_INFO == null}">
									<input id="userInput" type="hidden" value="no"/>
								</c:if>
							</form>
					
						<form action="${cp}/insertPost" 
								method="post" id="frm" enctype="multipart/form-data">
							<input type="text" class="form-control" id="title" 
									name="title" value="${post_title}" placeholder="제목"/>
							<textarea name="smarteditor" id="smarteditor" rows="10"
										cols="100" style="width:766px; height:412px;">${post_content}</textarea>
							<br>
							
							<input type="button" id="addFile" value="첨부파일추가"/> <br>
							
							<c:forEach items="${myfileList}" var="myfileVO">
								<label class="filelable">${myfileVO.myfile_name} 
									<button type="button" class="deleteBtn">삭제</button>
									<input type="hidden" value="${myfileVO.myfile_id}"/>
								</label>
								<br>
							</c:forEach>
							
							<input type="hidden" name="boardid" value="${board_id}"/>
							<input type="hidden" name="post_id" value="${post_id}"/>
							<input type="hidden" name="parent" value="${parent}"/>
							
						</form>
						
						<input type="button" id="savebutton" value="작성완료" />
						<input type="hidden" id="right" value="${right}"/>
						
					</div>
				</div>
			</div>
			
		</div>
	</div>
</body>
</html>
