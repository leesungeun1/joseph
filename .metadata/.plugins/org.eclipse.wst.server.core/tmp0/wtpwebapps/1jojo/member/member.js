$(document).ready(function(){
	
/*	 // ID중복검사
	 $("#idcheck").click(function(){
		 if($("#email").val()==""){
			 alert("ID를 입력하세요");
			 $("#email").focus();
		 }else{
			 var ref="/171122m2/IdCheck.do?id="+$("#email").val();
			 window.open(ref,"idcheck","width=200,height=100");
		 } 	
	 });
*/
	// 유효성 검사
	$("form").submit(function(){	
		if($("#email").val()==""){
			alert("ID를 입력하세요");
			$("#id").focus();
			return false;
		}
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
		
	/*	if(isNaN($("#pass").val())){
			alert("숫자만 입력 가능합니다.");
			$("#pass").val("").focus();			
			return false;
		}		*/
		
		
		/*
		
		// 글 수정 유효성 검사
		$("#myform").submit(function(){
			
			if($("#pass").val()==""){
				alert("비밀번호를 입력하세요");
				$("#pass").focus();
				return false;
			}
			if($("#pass").val().length<4 ||
			   $("#pass").val().length >20){
						alert("4~20자까지 입력가능 합니다.");
						$("#pass").val("").focus();
				        return false;
			}	
			
			if(isNaN($("#pass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#pass").val("").focus();			
				return false;
			}		
			
			if($("#npass").val()==""){
				alert("새 비밀번호를 입력하세요");
				$("#npass").focus();
				return false;
			}
			if(isNaN($("#npass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#npass").val("").focus();
				return false;
			}
			if($("#npass").val().length<4 ||
					$("#npass").val().length >20){
					alert("4~20자까지 입력가능 합니다.");
					$("#npass").val("").focus();
					return false;
			}
			if($("#chpass").val()==""){
				alert("새 비밀번호를 입력하세요");
				$("#chpass").focus();
				return false;
			}
			if(isNaN($("#chpass").val())){
				alert("숫자만 입력 가능합니다.");
				$("#chpass").val("").focus();
				return false;
			}
			

			if($("#chpass").val().length<4 ||
					$("#chpass").val().length >20){
					alert("4~20자까지 입력가능 합니다.");
					$("#chpass").val("").focus();
					return false;
			}		
					
			if($("#npass").val() != $("#chpass").val()){
				alert("비밀번호가 일치하지 않습니다");
				$("#chpass").val("").focus();
				return false;
			}	
			
			
		});*/
		
	
	
	}); // submit() end
	
}); 