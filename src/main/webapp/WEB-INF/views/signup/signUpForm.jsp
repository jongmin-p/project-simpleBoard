<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/signup/signUpForm.css" rel="stylesheet" type="text/css">
    <!-- JS 연결 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/signup/signUpForm.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<body>
  <jsp:include page="/WEB-INF/views/include/header.jsp" />

  <form action="/" method="POST" class="info-wrapper">
    <h2>회원 가입</h2>

    <input type="text" name="id" placeholder="아이디를 입력해 주세요 (최소 5글자 이상)" autofocus /><br />
    <input type="password" name="pwd" placeholder="비밀번호를 입력해 주세요 (최소 5글자 이상)" /><br />
    <input type="text" name="name" placeholder="이름을 입력해 주세요" /><br />
    <input type="text" name="phone" placeholder="연락처를 입력해 주세요  (010-1234-5678)" /><br />
    <input type="email" name="email" placeholder="이메일을 입력해 주세요" /><br />

    <button type="submit" id="signUpBtn">가입</button>
    <button type="reset" id="cancelBtn">취소</button>
  </form>

<%-- 제이 쿼리는 여기에, 자바스크립트 코드는 따로 연결 --%>
<script>
    $(document).ready(function () {

        $('#signUpBtn').on("click", function () {
            alert("가입 버튼 클릭됐음!");
        })
    })
</script>
</body>
</html>
