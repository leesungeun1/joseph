<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%-- <%@ page import="java.text.SimpleDateFormat" %> --%>
<%@ page import="net.crawl.db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>크롤링 결과</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function sortTable() {
		  var table, rows, switching, i, x, y, shouldSwitch;
		  table = document.getElementById("resultTable");
		  
		  switching = true;
		  /*Make a loop that will continue until no switching has been done:*/
		  while (switching) {
		    //start by saying: no switching is done:
		    switching = false;
		    rows = table.getElementsByTagName("tr");
		    /*Loop through all table rows (except the first, which contains table headers):*/
		    for (i = 1; i < (rows.length - 1); i++) {
		      //start by saying there should be no switching:
		      shouldSwitch = false;
		      /*Get the two elements you want to compare, one from current row and one from the next:*/
		      x = rows[i].getElementsByTagName("td")[4];
		      y = rows[i + 1].getElementsByTagName("td")[4];
		      //check if the two rows should switch place:
		      if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
		        //if so, mark as a switch and break the loop:
		        shouldSwitch= true;
		        break;
		      }
		    }
		    if (shouldSwitch) {
		      /*If a switch has been marked, make the switch and mark that a switch has been done:*/
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
			<c:import url="../member/header.jsp" />
		</header>
	</div>
	<br>
	<br>
	<br>
	<!-- 게시판 리스트 -->
	<div class="container">
		<div class="row content">
			<div class="text-left">
				<p>검색한 단어 목록 : ${words}</p>
				<p>공고에 포함된 있는 단어 수/검색 단어 수 : 자신이 가진 역량과 얼마나 채용공고에서 요구하는 바와 얼마나 유사한지 알 수 있습니다.</p>
				<button type="button" class="btn btn-default" onclick="location.href='./CrawlSaveAction.cr'">분석 결과 및 관련 업체 링크 저장(계정당 1개뿐)</button>
				<table class="table table-striped" id="resultTable">

					<!-- 레코드가 있으면 -->
					<c:if test="${search_list_count > 0 }">

						<tr align="center" valign="middle">
							<td colspan="5">검색 결과</td>
						</tr>

						<tr align="center" valign="middle">
							<td style="font-family: Tahoma; font-size: 8pt;" height="26">
								<div align="center">검색 식별 번호</div>
							</td>
							<td style="font-family: Tahoma; font-size: 8pt;">
								<div align="center">회사명</div>
							</td>
							<td style="font-family: Tahoma; font-size: 8pt;" width="25%">
								<div align="center">지원자격 모음</div>
							</td>
							<td style="font-family: Tahoma; font-size: 8pt;" width="25%">
								<div align="center">우대사항 모음</div>
							</td>
							<td style="font-family: Tahoma; font-size: 8pt;" width="25%">
								<div align="center">공고에 포함된 있는 단어 수/검색 단어 수 <button class="btn-default" onclick="sortTable()">▼</button></div>
							</td>
						</tr>

						<c:forEach var="b" items="${filteredList}" varStatus="status">
							
							<tr align="center" valign="middle">
								<td height="23" style="font-family: Tahoma; font-size: 10pt;">
									<!-- 번호 출력 부분 -->
									<c:out value="${b.search_com_No}" />
								</td>

								<td style="font-family: Tahoma; font-size: 10pt;">
									<div align="left">
										<a href="https://www.saramin.co.kr/${b.com_link}">
											${b.com_name} </a>
									</div>
								</td>

								<td style="font-family: Tahoma; font-size: 10pt;">
									${b.com_qual}</td>
								<td style="font-family: Tahoma; font-size: 10pt;">
									${b.com_preex}</td>
								<td style="font-family: Tahoma; font-size: 10pt;">
									${ matchRatio[status.index] }</td>
							</tr>

						</c:forEach>

					</c:if>

					<!-- 레코드가 없으면 -->
					<c:if test="${search_list_count == 0 }">
						<tr align="center" valign="middle">
							<td colspan="5">검색 결과</td>
							<td align=right><font size=2>검색 결과가 없습니다.</font></td>
						</tr>
					</c:if>

					<tr align="right">
						<td colspan="5">
							<button type="button" class="btn btn-default" onclick="location.href='./CrawlSaveAction.cr'">분석 결과 및 관련 업체 링크 저장(계정당 1개뿐)</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>