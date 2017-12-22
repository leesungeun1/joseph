<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>

	$(document).ready(function(){
		
		// 상세보기(1개의 데이터 추출)
		$("#one").one("click",function(){
//		$("#one").click(function(){
			  var g_no = 1;			  
			  
			  $.ajax({
					type:'post',
					url:'/resttest/cont.do',
					data: {"g_no":g_no},					
					dataType:'text', 
					success:function(result){
						alert(result)						
						console.log("result: " + result);						
						
						//json을 javascript객체로 변환
						var obj = JSON.parse(result);
						
						$("#treeData").append(
		                   "<tr><td>번호</td>" + "<td>이름</td>" 
		                     + "<td>제목</td>" + "<td>비밀번호</td>"
		                     + "<td>내용</td>" + "<td>조회수</td>" 
		                     + "<td>날짜</td></tr>");
						
		      //    		$.each(result, function() {
		          			
		                	$("#treeData").append("<tr>" + "<td>"                 
		                        + obj.g_no + "</td>" + "<td>"
		                        + obj.g_name + "</td>" + "<td>"
		                        + obj.g_title + "</td>" + "<td>"
		                        + obj.g_pwd + "</td>" + "<td>"
		                        + obj.g_cont + "</td>" + "<td>"
		                        + obj.g_hit + "</td>" + "<td>"
		                        + obj.g_date + "</td>" + "</tr>");
		     //        	});	// each() end					
						
				}}); // ajax() end
		});	// click() end	
		
		
		
		
		// 전체 데이터 추출
		$("#all").one("click",function(){			 			  
			  
			  $.ajax({
					type:'get',
					url:'/resttest/g_list.do',										
					dataType:'text', 
					success:function(result){
						alert(result)						
						console.log("result: " + result);	
						
						var obj = JSON.parse(result);
						
						$("#allData").append(
		                   "<tr><td>번호</td>" + "<td>이름</td>" 
		                     + "<td>제목</td>" + "<td>비밀번호</td>"
		                     + "<td>내용</td>" + "<td>조회수</td>" 
		                     + "<td>날짜</td></tr>");
						
						var output='';
		          		$.each(obj, function(index,item) {         					          			
		          						          			
								output += "<tr>" + "<td>"
		                        output += item.g_no + "</td>" + "<td>"
		                        output += item.g_name + "</td>" + "<td>"
		                        output += item.g_title + "</td>" + "<td>"
		                        output += item.g_pwd + "</td>" + "<td>"
		                        output += item.g_cont + "</td>" + "<td>"
		                        output += item.g_hit + "</td>" + "<td>"
		                        output += item.g_date + "</td>" + "</tr>"               
		       							
		             	});	// each() end		             	
		          			$("#allData").append(output);
//	                        document.getElementById("allData").innerHTML = output;
						
				}});  // ajax() end
		});	// click() end	
		
	}); // ready() end
	
</script>

    
 <input type="button" value="상세보기" id="one">
 <input type="button" value="전체목록" id="all"> 
 
 <table border=1 id="treeData"></table> 
 <table border=1 id="allData"></table> 