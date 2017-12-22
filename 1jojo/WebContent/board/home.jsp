<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>홈페이지 이름</title>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">  

    <nav class="navbar navbar-inverse" role="navigation">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">로고 </a>
      </div>
     
      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
          <li class="active"><a href="loginForm.jsp">로그인 </a></li>
          <li><a href="regForm.jsp">회원가입</a></li>
          <li><a href="board.jsp">게시판</a></li>
         <!-- <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">드롭다운 <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">서브메뉴 1</a></li>
              <li><a href="#">서브메뉴 2</a></li>
              <li><a href="#">서브메뉴 3</a></li>
            </ul>
          </li> -->
        </ul>
     </div><!-- /.navbar-collapse -->
    </nav>
    <article>
 <table>
 <tr>
 <td>
 <input type=text name=webadd value="">
 </td>
 <td>
 <input type=submit name=search value="검색"> 
 </table>
    </article>
</div> <!-- container 끝 -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/bootstrap.min.js"></script>
</body>


</html>