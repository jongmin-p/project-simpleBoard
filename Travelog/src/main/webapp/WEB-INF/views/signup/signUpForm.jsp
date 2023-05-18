<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- CSS 연결 -->
	<link href="${pageContext.request.contextPath}/css/signup/signUpForm.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/signup/signUpForm.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />

    <form action="<c:url value="/signup/save"/>" method="POST" onsubmit="return formCheck(this)" class="info-wrapper">
        <h2>회원 가입</h2>
	    <div id="msg" class="msg">
	  	    <c:if test="${not empty param.msg}">
	        	<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
	    	</c:if>
	    </div> 

        <input type="text" name="id" placeholder="아이디를 입력해 주세요 (최소 5글자 이상)" autofocus /><br />
        <input type="password" name="pwd" placeholder="비밀번호를 입력해 주세요 (최소 5글자 이상)" /><br />
        <input type="text" name="name" placeholder="이름을 입력해 주세요" /><br />
        <input type="text" name="phone" placeholder="연락처를 입력해 주세요  (010-1234-5678)" /><br />
        <input type="email" name="email" placeholder="이메일을 입력해 주세요" /><br />
        <!-- 유저 회원가입이니까 DB에서 mem_user 컬럼의 값을 자동으로 user 로 숨겨서 전달하기 -->
        <input type="hidden" name="user" value="user" />

        <button type="submit" id="signUpBtn">가입</button>
        <button type="reset" id="cancelBtn">취소</button>
    </form>
</body>
</html>