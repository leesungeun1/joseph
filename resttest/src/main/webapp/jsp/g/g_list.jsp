<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>maven list test</title>
<link rel="stylesheet" type="text/css" href="/resttest/css/g.css" />
</head>
<body>
 <table align="center" border="1" width="600">
  <tr>
   <th colspan="5"> 게시판 목록 </th>
  </tr>
  <tr>
   <th>번호</th> <th>제목</th> <th>이름</th> <th>조회수</th>
   <th>날짜</th>
  </tr>
  <c:if test="${!empty glist}">
   <c:forEach var="g" items="${glist}">
    <tr>
     <th>${g.g_no}</th> 
     <th>
     <a href="g_cont.do?g_no=${g.g_no}&state=cont">
     ${g.g_title}</a>
     </th>
     <th>${g.g_name}</th>
     <th>${g.g_hit}</th>
     <th>${g.g_date}</th>
    </tr>
   </c:forEach>  
  </c:if>
  <c:if test="${empty  glist}">
   <tr>
    <th colspan="5">목록이 없습니다!</th>
   </tr>
  </c:if>
  <tr>
   <th colspan="5">
   <input type="button" value="글쓰기" 
   onclick="location='/resttest/g_write.do'" />
   </th>
  </tr>
 </table>
</body>
</html>