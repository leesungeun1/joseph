<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="net.qnaboard.db.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
//	BoardBean board = (BoardBean)request.getAttribute("boarddata");
//  String nowpage = (String)request.getAttribute("page");
%>

<html>
<head>
	<title>QnA 게시판</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script>
		$(document).ready(function(){
			$("form").submit(function(){				
				if($("#board_subject").val()==""){
					alert("제목을 입력 하세요?");
					$("#board_subject").focus();
					return false;
				}
				if($("#board_content").val()==""){
					alert("내용을 입력 하세요?");
					$("#board_content").focus();
					return false;
				}
				if($("#board_pass").val()==""){
					alert("비밀번호를 입력 하세요?");
					$("#board_pass").focus();
					return false;
				}
			});			
		});	
	</script>
</head>

<body>
<!-- 게시판 수정 -->
<form action="/1jojo/QnaBoardModifyAction.qo" method="post" name="modifyform">
<input type="hidden" name="BOARD_NUM" value="${boarddata.board_num}">
<input type="hidden" name=page value="${page}">
<table cellpadding="0" cellspacing="0" align=center border=1>
	<tr align="center" valign="middle">
		<td colspan="5">QnA 게시판</td>
	</tr>
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">제 목</div>
		</td>
		<td>
			<input name="BOARD_SUBJECT" id="board_subject" size="50" maxlength="100" 
				value="${boarddata.board_subject}">
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<textarea name="BOARD_CONTENT" id="board_content" cols="67" rows="15">${boarddata.board_content}</textarea>
		</td>
	</tr>
	<%-- <%if(!(board.getBOARD_FILE()==null)){ %>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			&nbsp;&nbsp;<%=board.getBOARD_FILE() %>
		</td>
	</tr>
	<%} %> --%>
	
	<c:if test="${!empty boarddata.board_file}">
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</td>
		<td>
			&nbsp;&nbsp;${boarddata.board_file}
		</td>
	</tr>
	</c:if>
	
	
	<tr>
		<td height="16" style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input name="BOARD_PASS" id="board_pass" type="password">
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<input type=submit value="수정">
			<input type=button value="취소" onClick="history.go(-1)">
			</font>
		</td>
	</tr>
</table>
</form>
<!-- 게시판 수정 -->
</body>
</html>