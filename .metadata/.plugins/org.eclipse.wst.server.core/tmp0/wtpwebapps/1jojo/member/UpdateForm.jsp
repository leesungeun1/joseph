<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원 정보 수정</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <script src="/171122m2/member/member.js"></script> -->



</head>
<body>

<div>
<header>

	<c:import url ="header.jsp" />

</header>
<br>
<br>
<br>


<script>

	$(document).ready(function(){
		
		$("#myform").submit(function(){
			
			if($("#pass").val()==""){
				alert("비밀번호를 입력하세요");
				$("#pass").focus();
				return false;
			}
			if($("#pass").val().length<8 ||
			   $("#pass").val().length >32){
						alert("8~32자까지 입력가능 합니다.");
						$("#pass").val("").focus();
				        return false;
			}	
		/* 	
			if(isNaN($("#pass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#pass").val("").focus();			
				return false;
			}		
			 */
			if($("#npass").val()==""){
				alert("새 비밀번호를 입력하세요");
				$("#npass").focus();
				return false;
			}
	/* 		if(isNaN($("#npass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#npass").val("").focus();
				return false;
			} */
			if($("#npass").val().length<8 ||
					$("#npass").val().length >32){
					alert("8~32자까지 입력가능 합니다.");
					$("#npass").val("").focus();
					return false;
			}
			if($("#chpass").val()==""){
				alert("새 비밀번호를 확인하세요");
				$("#chpass").focus();
				return false;
			}
		/* 	if(isNaN($("#chpass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#chpass").val("").focus();
				return false;
			}
			 */

			if($("#chpass").val().length<8 ||
					$("#chpass").val().length >32){
					alert("8~32자까지 입력가능 합니다.");
					$("#chpass").val("").focus();
					return false;
			}		
					
			if($("#npass").val() != $("#chpass").val()){
				alert("비밀번호가 일치하지 않습니다");
				$("#chpass").val("").focus();
				return false;
			}	
			
			
		});
		
	});

</script>



</div>

    <div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav list-group">
<!--     	<ul class = "text-success"> -->
      	<!-- <li class ="list-group-item active" > --><a class ="list-group-item active" href="/1jojo/UpdateForm.do">정보 수정</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/CrawlGetSavedDataAction.cr">검색 기록</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/CrawlDeleteSavedDataAction.cr">검색 기록 삭제</a><!-- </li> -->
      	<!-- <li class ="list-group-item"> --><a class ="list-group-item" href="/1jojo/DeleteMember.do">회원 탈퇴</a><!-- </li> -->
<!--     	</ul> -->
    </div>
    <div class="col-sm-8 text-left"> 
      
      <form id="myform"  method=post action="/1jojo/Update.do">
		<input type=hidden name="id" value="${member.id}">
		
		
		<table class="table table-bordered" align=center  >
		<caption><h3> 회원 정보 수정</h3></caption>
			<thead>
			<tr><td>ID</td>
		 		  <td>${member.id}</td>
	 		</tr>
	 		</thead>
	 		<tbody>
	 		<tr><td>기존 비밀번호</td>
	 			  <td><input type="password" id="pass" name="pass"></td>
	 		<tr><td>새 비밀번호</td>
	 		      <td><input type="password" id="npass" name="npass"></td> 	  
		    <tr><td>새 비밀번호 확인</td>
	 				<td><input type="password" id="chpass" ></td>
		    </tr>
		    <tr><td colspan=2 align=center>
					<input type=submit class="btn btn-default" value="수정">
					<input type=reset class="btn btn-default" value="취소">
		    </td>
			</tr>
			</tbody>
        </table>
      </form>
            
    </div>
    
  </div>
</div>
</body>
</html>