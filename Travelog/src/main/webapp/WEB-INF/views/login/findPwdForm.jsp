<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- CSS 연결 -->
	<link href="${pageContext.request.contextPath}/css/login/findPwdForm.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/findPwdForm.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	
    <form class="info-wrapper">
		<h2>비밀번호 찾기</h2>
		<div id="errorMsg"></div> <!-- 여기다가 에러 메시지 출력 -->

		<input type="text" id="mname" name="name" placeholder="이름을 입력하세요." required="required">
		<input type="text" id="memail" name="email" placeholder="이메일을 입력하세요." required="required">
		<input type="text" id="mid" name="mid" placeholder="아이디를 입력하세요." required="required">

		<button type="submit" id="findPwdBtn">비밀번호 찾기</button>
		<button type="reset" id="cancelBtn">취소</button>
	</form>
</body>
</html>