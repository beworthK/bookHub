<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<title>책 수정</title>
	</head>
	<body>
		<h1>책 수정</h1>
		<!-- action 값 생략됐으므로 기본값은 현재주소
			 update의 경로에 method로 지정한 post방식으로 호출되므로 이 URI에 매핑되는 컨트롤러에서 form 데이터를 받아서 처리
		-->
		<form method="POST">
			<p>제목 : <input type="text" name="title" value="${ data.title }" /></p>
			<p>카테고리 : <input type="text" name="category"  value="${ data.category }" /></p>
			<p>가격 : <input type="text" name="price"  value="${ data.price }" /></p>
			<p><input type="submit" value="저장" />
		</form>
	</body>
</html>