<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="net.crawl.db.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>크롤링 결과</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
							검색 결과 개수 : ${search_list_count }
							<button type="button" class="btn btn-default" onclick="location.href='./CrawlAnalysisAction.cr'">검색결과 분석</button>
				<table class="table table-striped">

					<!-- 레코드가 있으면 -->
					<c:if test="${search_list_count > 0 }">

						<tr align="center" valign="middle">
							<td colspan="4">검색 결과</td>
							<!-- 		<td align=right> -->
							<!-- 		</td> -->
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
						</tr>

						<!-- 화면 출력 번호 -->
						<%-- 	<c:set var="num" value="${listcount-(page-1)*10}"/> 	 --%>

						<c:forEach var="b" items="${search_list}">

							<tr align="center" valign="middle">
								<td height="23" style="font-family: Tahoma; font-size: 10pt;">
									<!-- 번호 출력 부분 --> <c:out value="${search_com_No}" />
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
							</tr>

						</c:forEach>

					</c:if>

					<!-- 레코드가 없으면 -->
					<c:if test="${search_list_count == 0 }">
						<tr align="center" valign="middle">
							<td colspan="4">검색 결과</td>
							<td align=right><font size=2>검색 결과가 없습니다.</font></td>
						</tr>
					</c:if>

					<tr align="right">
						<td colspan="4">
<%-- 						검색 결과 식별 번호 : ${search_com_No} 검색 갯수 : ${search_list_count} --%>
							<button type="button" class="btn btn-default" onclick="location.href='./CrawlAnalysisAction.cr'">검색결과 분석</button></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>