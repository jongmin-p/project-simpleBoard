<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>simpleBoard</title>
</head>

<body>
    <jsp:include page="/WEB-INF/views/include/header.jsp" />

    <c:choose>
        <c:when test="${not empty id}">
            <h1 style="text-align: center; color: green">${id} 님, 반갑습니다!</h1>
        </c:when>
        <c:otherwise>
            <h1 style="text-align: center">Welcome to the Main Page!</h1>
        </c:otherwise>
    </c:choose>
</body>