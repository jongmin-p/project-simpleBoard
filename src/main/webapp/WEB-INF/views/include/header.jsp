<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
              <li><a href="<c:url value='/login'/>">Login</a></li>
              <li><a href="<c:url value='/signup'/>">Sign Up</a></li>
          </ul>
      </div>
  </body>
</html>