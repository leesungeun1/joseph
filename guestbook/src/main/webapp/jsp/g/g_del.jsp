<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 삭제</title>
<link rel="stylesheet" type="text/css" href="./css/g.css" />
<script src="./js/jquery.js"></script>
<script type="text/javascript">
 function del_check(){
	 if($.trim($("#del_pwd").val())==""){
		 alert("비밀번호를 입력하세요!");
		 $("#del_pwd").val("").focus();
		 return false;
	 }
 }
</script>
</head>
<body>
 <div id="gDel_wrap">
  <h3 class="gDel_title">메이븐 방명록 삭제</h3>
  <form method="post" action="g_del_ok.do"
  onsubmit="return del_check();">
   <input type="hidden" name="g_no" value="${g.g_no}" />
   <table id="gDel_t">
    <tr>
     <th>비밀번호</th>
     <td>
     <input type="password" name="del_pwd" id="del_pwd"
     size="14" class="box" />
     </td>
    </tr>
   </table>
   <div id="gDel_menu">
   <input type="submit" value="삭제" class="input_b" />
   <input type="reset" value="취소" class="input_b"
   onclick="$('#del_pwd').focus();" />
   </div>
  </form>
 </div>
</body>
</html>