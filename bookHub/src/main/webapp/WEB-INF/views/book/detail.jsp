<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 
	JSTL(JSP Standard Tag Library) : 컨트롤러에서 보내준 데이터를 뷰에 표현하기 위해 사용
	fmt : 포맷터 태그 라이브러리. 원본 데이터의 형식을 바꿔주느 역할
 -->

<html>
	<head>
		<title>책 상세</title>
	</head>
	<body>
		<h1>책 상세</h1>
		<p>제목 : ${ data.title } </p>
		<p>카테고리 : ${ data.category }</p>
		<!-- fmt:formatNumber : 숫자 포맷. maxFractionDigits="3" : 세자리 마다 [,(콤마)] -->
		<p>가격 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${data.price}" /></p> 
		<!-- fmt:formatDate : pattern 형태에 맞춰 날짜 포맷. -->
		<p>입력일 : <fmt:formatDate value="${data.insert_date}" pattern="yyyy.MM.dd HH:mm:ss" /></p>
		
		<p>
			<!-- 
				a 태그 : 이동 페이지 링크 걸어줌. 단순 페이지 이동이므로 GET으로 링크가 걸리는 a태그 사용 
				컨트롤러애서 따로 키값 설정한 bookId 
			-->
			<a href="/update?bookId=${bookId}">수정</a>
		</p>
		
		<!-- 
			삭제는 데이터 변경이 있으므로 POST 메소드 
			action : 서버의 URI 지칭. action 속성이 생략되는 경우, 브라우저의 기본값은 '현재 주소'다.
			현재 주소와 다른 URI 로 서버에 전달할 경우 action 속성을 명시적으로 설정한다.
		-->
		<form method="POST" action="/delete">
			<input type="hidden" name="bookId" value="${bookId}" />
			<input type="submit" value="삭제" />
		</form>
		<p>
			<a href="/list">목록으로</a>
		</p>
	</body>
</html>