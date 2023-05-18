<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- CSS 연결 -->
	<link href="${pageContext.request.contextPath}/css/login/loginForm.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/loginForm.js"></script>
</head>
  

<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	
    <form action="<c:url value='/login/login'/>" method="POST" class="login-wrapper">
        <h2>로그인</h2>
        <div id="msg">
        	<c:if test="${not empty param.msg }">
        		${param.msg }
        	</c:if>
        </div>
        <!-- 여기다가 에러 메시지 출력 -->

        <input type="text" name="id" placeholder="ID를 입력하세요" value="${cookie.id.value}"/>
        <input type="password" name="pwd" placeholder="비밀번호를 입력하세요" />
        <!-- board/list 라는 값을 hidden 으로 받아옴. -->
		<input type="hidden" name="toURL" value="${param.toURL}"/>
		<!-- ㄴ> 그러면, 이후에는 이 board/list 라는 것을 LoginController 에서 받겠지? (PostMapping /login/login) -->

        <label for="rememberId">
            <input type="checkbox" id="rememberId" name="rememberId" ${empty cookie.id.value ? "" : "checked" }> 아이디 저장
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
    </form>
</body>
</html>