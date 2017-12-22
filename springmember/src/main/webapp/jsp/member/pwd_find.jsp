<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비번찾기</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
<link rel="stylesheet" type="text/css" href="./css/member.css" />
<script src="./js/jquery.js"></script>
<script>
 function check(){
	 if($.trim($("#id").val())==""){
		 alert("아이디를 입력하세요!");
		 $("#id").val("").focus();
		 return false;
	 }
	 if($.trim($("#name").val())==""){
		 alert("회원이름을 입력하세요!");
		 $("#name").val("").focus();
		 return false;
	 }
 }
</script>
</head>
<body>
 <div id="pwd_wrap">
 
 <c:if test="${empty pwdok}"> 
  <h2 class="pwd_title">비번찾기</h2>
  <form method="post" action="pwd_find_ok.nhn"
  onsubmit="return check()">  
   <table id="pwd_t">
    <tr>
     <th>아이디</th>
     <td><input name="id" id="id" size="14" class="input_box" /></td>
    </tr>
    
    <tr>
     <th>회원이름</th>
     <td><input name="name" id="name" size="14" class="input_box" /></td>
    </tr>
   </table>
   <div id="pwd_menu">
    <input type="submit" value="찾기" class="input_button" />
    <input type="reset" value="취소" class="input_button" 
    onclick="$('#id').focus();"/>
   </div>
   <div id="pwd_close">
    <input type="button" value="닫기" class="input_button"
    onclick="self.close();" />
    <!-- close()메서드로 공지창을 닫는다. self.close()는 자바스크립트이다. -->
   </div>
  </form>
  </c:if>
  
  
  <c:if test="${!empty pwdok}">
    <h2 class="pwd_title2">비번찾기 결과</h2>
    <table id="pwd_t2">
     <tr>
      <th>검색한 비번:</th>
      <td>${pwdok}</td>
     </tr>
    </table>
    <div id="pwd_close2">
    <input type="button" value="닫기" class="input_button"
    onclick="self.close();" />
    <!-- close()메서드로 공지창을 닫는다. self.close()는 자바스크립트이다. -->
    </div>
  </c:if> 
  
 </div>
</body>
</html>