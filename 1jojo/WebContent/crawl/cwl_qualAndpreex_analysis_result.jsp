<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="net.crawl.db.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>크롤링 결과</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
<!-- [출처] [JQuery] Tablesort 테이블정렬, 검색, 페이징 처리를 간단하게!|작성자 Printf -->
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
<script>
	$(function(){  //전체선택 체크박스 클릭
		$("#allCheck").click(function(){ //만약 전체 선택 체크박스가 체크된상태일경우
			if($("#allCheck").prop("checked")) { 	//해당화면에 전체 checkbox들을 체크해준다
				$("input[type=checkbox]").prop("checked",true); // 전체선택 체크박스가 해제된 경우
			} else { //해당화면에 모든 checkbox들의 체크를해제시킨다.
				$("input[type=checkbox]").prop("checked",false);
			}
		})
	})
	
		// 선택한 Checkbox 값을 가져오는 방법
	// var arr = $('input[name=_selected_]:checked').serializeArray().map(function(item) { return item.value }); //array 방식.
	function collectWord() {
		var str = $('input[name=word]:checked').serialize();// 이건 QueryString 방식으로
// 		alert(str);		//a=1&b=2&c=3&d=4&e=5 같은 형식
		if(str ==""){
			alert("선택된 단어가 없습니다.");
		}else{
// 			if($("#preex").prop("checked")) { 
// 				var isPreexOrQual="";
// 				if($("#preex").hasClass("active") === true) {// 속성값이 존재함.
// 					isPreexOrQual=preex;
// 				}else{
// 					isPreexOrQual=qual;
// 				}// 출처: http://ooz.co.kr/245 [이러쿵저러쿵]
// 			}
			str=str.replace( /word=/gi, "");
			str=str.replace( /&/gi, "-");
			var ref="/1jojo/CrawlPreexAndqualFilterAction.cr?word="+str;
			window.location.href=ref;
		}
		//alert로 확인해보면 javascript에선 인터넷 특유의 값으로 인식해버리는것 같다
	}

	function sortTable() {
		  var table, rows, switching, i, x, y, shouldSwitch;
		  table = document.getElementById("preexTable");
		  
		  switching = true;
		  /*Make a loop that will continue until
		  no switching has been done:*/
		  while (switching) {
		    //start by saying: no switching is done:
		    switching = false;
		    rows = table.getElementsByTagName("tr");
		    /*Loop through all table rows (except the
		    first, which contains table headers):*/
		    for (i = 1; i < (rows.length - 1); i++) {
		      //start by saying there should be no switching:
		      shouldSwitch = false;
		      /*Get the two elements you want to compare,
		      one from current row and one from the next:*/
		      x = rows[i].getElementsByTagName("td")[2];
		      y = rows[i + 1].getElementsByTagName("td")[2];
// 		      alert(x);
// 		      alert(x.innerHTML);
		      
		      //check if the two rows should switch place:
		      if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
		        //if so, mark as a switch and break the loop:
		        shouldSwitch= true;
		        break;
		      }
		    }
		    if (shouldSwitch) {
		      /*If a switch has been marked, make the switch
		      and mark that a switch has been done:*/
		      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		      switching = true;
		    }
		  }
		}	
</script>
</head>

<body>
<div>
<header>
	<c:import url="../member/header.jsp"/>
</header>
</div><br><br><br>
<!-- 게시판 리스트 -->
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a data-toggle="tab" href="#preex">우대사항 빈도분석결과</a></li>
			<li><a data-toggle="tab" href="#qual">지원자격 빈도분석결과</a></li>
		</ul>
		<div class="tab-content">
			<div id="preex" class="tab-pane fade in active">
				<p>본인이 가진 역량을 체크하시오.</p>
					<table class="table table-striped" id="preexTable">
						<!-- 레코드가 있으면 -->
						<tr align="center" valign="middle">
							<th style="font-family: Tahoma;" height="26">
								<div align="center">
									<input type="checkbox" name="_selected_all_" id="allCheck">
								</div>
							</th>
							<th style="font-family: Tahoma;" height="26">
								<div align="center">우대사항</div>
							</th>
							<th style="font-family: Tahoma;">
								<div align="center">단어 출현 빈도수 <button class="btn-default" onclick="sortTable()">▼</button></div>
							</th>
						</tr>

						<c:forEach var="b" items="${cwl_qualAndpreex_analysis_result}">
							<c:if test="${not empty b.com_preex }">
								<!-- 만약에 우대사항 조건 빈도가 담긴 row라면 -->
								<tr align="center" valign="middle">
									<td style="font-family: Tahoma; font-size: 10pt;"><input
										type="checkbox" name="word" value="${b.com_preex}"> <!-- alert로 확인해보면 javascript에선 이상한 값으로 인식해버리는것 같다. -->
									</td>
									<td height="23" style="font-family: Tahoma;"><c:out
											value="${b.com_preex}" /></td>
									<td style="font-family: Tahoma; font-size: 10pt;">
										${b.com_frequency}</td>
								</tr>
							</c:if>
						</c:forEach>

						<!-- 레코드가 없으면 -->
						<c:if test="${search_list_count == 0 }">
							<tr align="center" valign="middle">
								<td colspan="4">검색 결과</td>
								<td align=right><font size=2>검색 결과 자체가 없습니다. 맞춤 채용 설정을 확인해보세요.</font></td>
							</tr>
						</c:if>

						<c:if test="${countPerWord_Preex == 0 }">
							<tr align="center" valign="middle">
								<td colspan="4">우대사항 검색 결과</td>
								<td align=right><font size=2>우대사항 검색 결과가 없습니다. 검색된 채용공고에는 우대사항으로 볼 수 있는 항목이 없습니다.</font></td>
							</tr>
						</c:if>

						<tr align="right">
							<td colspan="4">
								<!-- 			<button type="button" class="btn btn-default"  id="selectAllButton" onclick="selectAll();">전체 선택</button> -->
								<button type="button" class="btn btn-default"
									id="collectWordButton" onclick="collectWord();">체크한
									단어가 우대사항에 포함된 업체 링크 모음</button>
								<button type="button" class="btn btn-default"
									onclick="location.href='/1jojo/CrawlSaveAction.cr'">분석
									결과 및 관련 업체 링크 저장</button>
							</td>
						</tr>
					</table>
			</div><!-- preex -->
			<div id="qual" class="tab-pane fade">
				<table class="table table-striped" id="qualTable">
						<!-- 레코드가 있으면 -->
						<tr align="center" valign="middle">
							<th style="font-family: Tahoma;" height="26">
								<div align="center">
									<input type="checkbox" name="_selected_all_" id="allCheck">
								</div>
							</th>
							<th style="font-family: Tahoma;" height="26">
								<div align="center">지원자격</div>
							</th>
							<th style="font-family: Tahoma;">
								<div align="center">단어 출현 빈도수</div>
							</th>
						</tr>

						<c:forEach var="b" items="${cwl_qualAndpreex_analysis_result}">
							<c:if test="${not empty b.com_qual }">
								<!-- 만약에 우대사항 조건 빈도가 담긴 row라면 -->
								<tr align="center" valign="middle">
									<td style="font-family: Tahoma; font-size: 10pt;"><input
										type="checkbox" name="word" value="${b.com_qual}"> <!-- alert로 확인해보면 javascript에선 이상한 값으로 인식해버리는것 같다. -->
									</td>
									<td height="23" style="font-family: Tahoma;"><c:out
											value="${b.com_qual}" /></td>
									<td style="font-family: Tahoma; font-size: 10pt;">
										${b.com_frequency}</td>
								</tr>
							</c:if>
						</c:forEach>

						<!-- 레코드가 없으면 -->
						<c:if test="${search_list_count == 0 }">
							<tr align="center" valign="middle">
								<td colspan="4">검색 결과</td>
								<td align=right><font size=2>검색 결과 자체가 없습니다. 맞춤 채용 설정을 확인해보세요.</font></td>
							</tr>
						</c:if>

						<c:if test="${countPerWord_Qal == 0 }">
							<tr align="center" valign="middle">
								<td colspan="4">지원자격 검색 결과</td>
								<td align=right><font size=2>지원자격 검색 결과가 없습니다. 검색된 채용공고에는 우대사항으로 볼 수 있는 항목이 없습니다.</font></td>
							</tr>
						</c:if>

						<tr align="right">
							<td colspan="4">
								<button type="button" class="btn btn-default"
									id="collectWordButton" onclick="collectWord();">체크한 단어가 우대사항에 포함된 업체 링크 모음</button>
								<button type="button" class="btn btn-default"
									onclick="location.href='/1jojo/CrawlSaveAction.cr'">분석 결과 및 관련 업체 링크 저장</button>
							</td>
						</tr>
					</table>
			
			</div><!-- qual -->
		</div><!-- tab -->
	</div><!-- container -->

</body>
</html>