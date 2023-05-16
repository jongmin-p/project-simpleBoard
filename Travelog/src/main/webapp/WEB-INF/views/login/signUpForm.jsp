<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- CSS 연결 -->
	<link href="${pageContext.request.contextPath}/resources/css/login/signUpForm.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login/signUpForm.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />

    <form class="info-wrapper" action="">
        <h2>회원 가입</h2>
        <div id="errorMsg"></div> <!-- 여기다가 에러 메시지 출력 -->

        <div id="id_check"></div>
        <!-- 여기다가 메세지 띄울거임. (중복 여부 메시지) -->
        <div id="msg" class="msg"></div>

        <input type="text" id="member_id" name="member_id" placeholder="아이디를 입력해 주세요 (최소 5글자 이상)" /><br />
        <input type="password" id="member_pw" placeholder="비밀번호를 입력해 주세요 (최소 5글자 이상)" name="member_pw" autofocus /><br />
        <input type="text" id="member_name" placeholder="이름을 입력해 주세요" name="member_name" /><br />
        <input type="text" id="member_phone" placeholder="연락처를 입력해 주세요  (010-1234-5678)" name="member_phone" /><br />
        <input type="email" id="member_email" placeholder="이메일을 입력해 주세요" name="member_email" /><br />
        <!-- 유저 회원가입이니까 DB에서 mem_user 컬럼의 값을 자동으로 user 로 숨겨서 전달하기 -->
        <input type="hidden" name="member_user" value="user" />

        <button type="submit" id="signUpBtn">가입</button>
        <button type="reset" id="cancelBtn">취소</button>
    </form>
</body>
</html>