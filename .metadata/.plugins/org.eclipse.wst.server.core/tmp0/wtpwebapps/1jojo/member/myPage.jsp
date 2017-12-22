<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>마이 페이지</title>
<script src = "http://code.jquery.com/jquery-latest.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div>
<header>

<c:import url ="header.jsp" />


</header>
<br>
<br>
<br>

</div>
<div class="container-fluid text-center">    
  <div class="row content">
   <div class="col-sm-2 sidenav list-group">
<!--     	<ul class = "text-success"> -->
      	<!-- <li class ="list-group-item active" > --><a class ="list-group-item" href="/1jojo/UpdateForm.do">정보 수정</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/CrawlGetSavedDataAction.cr">검색 기록</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/CrawlDeleteSavedDataAction.cr">검색 기록 삭제</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/DeleteMember.do">회원 탈퇴</a><!-- </li> -->
<!--     	</ul> -->
    </div>
    <div class="container col-sm-8 text-left">

  
  <form>      
  <input type=hidden name="id" value="${sessionScope.id}">
  <input type=hidden name="pass" value="${sessionScope.pass}"> 
  <table class="table table-striped">
    <caption><h3>회원 정보</h3></caption>
    <thead>
      <tr>
        <th>ID</th>
        <th>Password</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${sessionScope.id}</td>
        <td>${sessionScope.pass}</td>
      </tr>
    
    </tbody>
  </table>
  </form>
</div>

 
  </div>
</div>
</body>
</html>