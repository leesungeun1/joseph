<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script src="/1jojo/member/member.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	 // ID중복검사
	 $("#idcheck").click(function(){
		 if($("#id").val()==""){
			 alert("ID를 입력하세요");
			 $("#id").focus();
		 }else{
			 var ref="/1jojo/IdCheck.do?id="+$("#id").val();
			 window.open(ref,"idcheck","width=200,height=100");
		 } 	
	 });

	 });
	 </script>
</head>

<body>
<div class="container-fluid">
<header>

<c:import url ="header.jsp" />


</header>
<br>
<br>
<br>
</div>

<div class="container-fluid">
<div class="row content">

  <form method=post action="/1jojo/MemberInsert.do">
    <div class="form-group">
      <label for="id">사람인 ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
      <input type="button" id="idcheck" value="ID Check">
    
    </div>
    <div class="form-group">
      <label for="pass">Password:</label>
      <input type="password" class="form-control" id="pass" placeholder="Enter password" name="pass">
    </div>
    
    <button type="submit" class="btn btn-default">Sign up</button>
  </form>
  </div>
</div>
</body>
</html>