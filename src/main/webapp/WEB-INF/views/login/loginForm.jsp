<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/login/loginForm.css" rel="stylesheet" type="text/css">
    <!-- JS 연결 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login/loginForm.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<body>
    <jsp:include page="/WEB-INF/views/include/header.jsp" />

    <form action="/" method="POST" class="login-wrapper">
        <h2>로그인</h2>

        <input type="text" name="id" placeholder="ID를 입력하세요" />
        <input type="password" name="pwd" placeholder="비밀번호를 입력하세요" />

        <label for="rememberId">
            <input type="checkbox" id="rememberId" name="rememberId" > 아이디 저장
        </label>

        <input type="submit" id="loginBtn" value="로그인" />

        <div>
            <div class="info-wrapper">
                <a href="/" />ID 찾기</a> |
                <a href="/" />비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <!-- Button trigger modal -->
                <%-- <button type="button" id="signUpBtn" onClick="location.href='signup/add'">회원가입</button>     <-- 얘는 안 됨 --%>
                <button type="button" id="signUpBtn" onclick="window.location.href='<c:url value='/signup'/>';">회원가입</button>
            </div>
        </div>
    </form>
</body>
</html>
