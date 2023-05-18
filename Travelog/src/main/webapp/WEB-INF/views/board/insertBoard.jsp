<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- CSS 연결 -->
	<link
		href="${pageContext.request.contextPath}/css/board/insertBoard.css"
		rel="stylesheet" type="text/css">
	<!-- Jquery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/board/insertBoard.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	
	    <form action="" class="container">
        <h1>게시판</h1>
        <hr><br><br>

        <div class="title">
            <div class="title-header">제목</div>
            <input type="text" class="title-body">
        </div>

        <div class="content">
            <div class="content-header">내용</div>
            <textarea name="" id="" cols="30" rows="10" class="content-body"></textarea>
        </div>

        <div class="button">
            <button type="submit">등록</button>
            <button type="reset">취소</button>
        </div>
    </form>
</body>
</html>