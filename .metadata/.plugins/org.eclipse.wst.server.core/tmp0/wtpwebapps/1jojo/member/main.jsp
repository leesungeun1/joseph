<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>크롤링 프로젝트</title>


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
</div>

<div class="jumbotron text-center" style="background-color:#ff8080 !important;">
  <h1>Company</h1> 
  <p>We specialize in blablabla</p> 
  
  <form method=post action="/1jojo/Login.do">
 
    <div class="form-group ">
      <label for="id">사람인 ID:</label>
      <input type="text" class="form-control" id="id" placeholder="Enter ID" name="id">
    </div>
    
    <div class="form-group ">
      <label for="pass">Password:</label>
      <input type="password" class="form-control" id="pass" placeholder="Enter password" name="pass">
    </div>
     
    <div class="checkbox ">
      <label><input type="checkbox" name="remember"> Remember me</label>
    </div>
     
    <button type="submit" class="btn btn-default ">Submit</button>
  </form>
  
  <!-- <form action="/171122m2/SearchAction.do" method="post" 
	enctype="multipart/form-data" name="homeform">
    <div class="input-group">
      <input type="text" class="form-control" size="50" placeholder="text" required>
      <div class="input-group-btn">
        <button type="button" class="btn btn-danger">Subscribe</button>
      </div>
    </div>
  </form> -->
</div>


</body>


</html>