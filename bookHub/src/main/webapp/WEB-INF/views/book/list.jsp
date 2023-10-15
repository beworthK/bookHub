<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL 에서 반복문을 사용하기 위해 core 라이브러리 선언 -->

<html>
	<head>
		<title>책 목록</title>
	</head>
	<body>	
		<h1>책 목록</h1>
		<p>
			<!-- 첵 검색 기능 
			기본 http 메소드는 GET. 생략할경우 GET 으로 가므로 [/list?keyword=검색어] 로 이동한다
			-->
		    <form>
		        <input type="text" placeholder="검색" name="keyword" value="${keyword}" />
		        <input type="submit" value="검색" />
		    </form>			
		</p>
		<table>
			<thead>
				<tr>
					<td>제목</td>
					<td>카테고리</td>
					<td>가격</td>
				</tr>
			</thead>
			<tbody>
			<!-- 반복문 forEach items : 반복할 객체. var : 목록의 한 행을 나타내는 변수명 -->
			<c:forEach var="row" items="${data}">
				<tr>
					<td>
						<a href="/detail?bookId=${row.book_id}">
							${row.title}
						</a>
					</td>
					<td>${row.category}</td>
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${row.price}" /></td>
				</tr>
			</c:forEach>
			
			</tbody>
		</table>
		<p>
			<a href="/create">생성</a>
		</p>
	</body>
</html>