<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$("document").ready(function(){
		$("#close1").click(function(){		// 중복ID	
			opener.$("#id").val("").focus();
			window.close();
		});
		$("#close2").click(function(){		// 사용 가능한 ID
			opener.$("#pass").focus();
			window.close();
		});
	});
</script>

<c:if test="${result == 1}">
	중복 ID입니다. <br><br>
	<input type="button" value="닫기" id="close1">
</c:if>

<c:if test="${result != 1}">
	사용 가능한 ID입니다.<br><br>
	<input type="button" value="닫기" id="close2">
</c:if>




