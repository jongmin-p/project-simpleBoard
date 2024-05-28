<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        h1, h2 {
            text-align: center;
        }

        #loginId {
            color: lightseagreen;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/include/header.jsp" />

    <h1><span id="loginId">${id}</span> 님, 회원가입이 완료되었습니다.</h1>
    <h2>우측 상단의 Login 버튼을 클릭하여 로그인을 진행해 주세요.</h2>
</body>
</html>
