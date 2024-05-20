<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- session 을 이용하여 헤더 탭에다가 Login 혹은 Logout 으로 바꿔줌 --%>
<c:set var="loginOutLink" value="${sessionScope.id == null ? '/login' : '/login/logout'}" />
<c:set var="loginOut" value="${sessionScope.id == null ? 'Login' : 'Logout'}" />
<html>
  <head>
    <title>Document</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/include/header.css" rel="stylesheet" type="text/css">
  </head>

  <body>
      <div class="header">
          <ul id="main-menu">
              <li><a href="<c:url value='/'/>">Home</a></li>
              <li><a href="<c:url value='/board/list'/>">Board</a></li>
              <%-- 위에서 세팅한 session 변수에 따라 아래가 달라짐 --%>
              <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
              <li><a href="<c:url value='/signup'/>">Sign Up</a></li>
          </ul>
      </div>
  </body>
</html>