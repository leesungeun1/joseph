<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메이븐 스프링 실습</title>
<link rel="stylesheet" type="text/css" href="/resttest/css/g.css" />
<script src="/resttest/js/jquery.js"></script>
<script src="/resttest/js/g.js"></script>
</head>
<body>
  <div id="g_wrap">
   <h2 class="g_title">메이븐 방명록 글쓰기</h2>
   <form method="post" action="/resttest/g_write_ok.do" 
   onsubmit="return write_check();">
    <table id="g_t">
     <tr>
      <th>이름</th>
      <td>
       <input name="g_name" id="g_name" size="14" class="box" />
      </td>
     </tr>
     <tr>
      <th>제목</th>
      <td>
      <input name="g_title" id="g_title" size="38" class="box" />
      </td>
     </tr>
     <tr>
      <th>비밀번호</th>
      <td>
      <input type="password" name="g_pwd" id="g_pwd" size="14"
      class="box" />
      </td>
     </tr>
     <tr>
      <th>내용</th>
      <td>
      <textarea name="g_cont" id="g_cont" rows="8" 
      cols="34"  class="box"></textarea>
      </td>
     </tr>
    </table>
    <div id="g_menu">
    <input type="submit" value="저장" class="input_b" />
    <input type="reset" value="취소" class="input_b" 
    onclick="$('#g_name').focus();" />
    </div>
   </form>
  </div>
</body>
</html>