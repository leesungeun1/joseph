/**
 * g.js
 */

function write_check(){
	if($.trim($("#g_name").val())==""){
		alert("이름을 입력하세요!");
		$("#g_name").val("").focus();
		return false;
	}
	if($.trim($("#g_title").val())==""){
		alert("제목을 입력하세요!");
		$("#g_title").val("").focus();
		return false;
	}
	if($.trim($("#g_pwd").val())==""){
		alert("비밀번호를 입력하세요!");
		$("#g_pwd").val("").focus();
		return false;
	}
	if($.trim($("#g_cont").val())==""){
		alert("내용을 입력하세요!");
		$("#g_cont").val("").focus();
		return false;
	}
}
