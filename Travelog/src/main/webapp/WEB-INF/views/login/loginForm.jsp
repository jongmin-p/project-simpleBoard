<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- CSS 연결 -->
	<link href="${pageContext.request.contextPath}/resources/css/login/loginForm.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/login/loginForm.js"></script>
</head>


<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	
    <div class="login-wrapper">
        <h2>로그인</h2>
        <div id="errorMsg"></div>
        <!-- 여기다가 에러 메시지 출력 -->

        <input type="text" id="mid" name="mid" placeholder="ID를 입력하세요" />
        <input type="password" id="mpw" name="mpw" placeholder="비밀번호를 입력하세요" />

        <label for="saveMid">
            <input type="checkbox" id="saveMid"> 아이디 저장
        </label>

        <input type="submit" id="loginBtn" value="로그인" />

        <div>
            <div class="info-wrapper">
                <a href="findIdForm">ID 찾기</a> |
                <a href="findPwdForm">비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- Button trigger modal -->
                <button type="button" id="signUpBtn" onClick="location.href='signUpForm'">회원가입</button>
            </div>
        </div>
    </div>
</body>
</html>