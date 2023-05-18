<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<!-- CSS 연결 -->
	<link
		href="${pageContext.request.contextPath}/css/board/boardDetail.css"
		rel="stylesheet" type="text/css">
	<!-- Jquery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<!-- JS 연결 -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/board/boardDetail.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	
	    <div class="container">
        <h1>게시판</h1>
        <hr><br>

        <h2>여기다가 제목!</h2>

        <div class="content-info">
            <div class="writer-date">
                김팔춘 | 2023-01-23
            </div>
            <div class="hit-reply">
                조회 5 | 댓글 3
            </div>
        </div>

        <!-- 컨텐츠 바디 -->
        <div class="content-body"></div>

        <!-- 컨텐츠 버튼 -->
        <div class="content-button">
            <button class="boardListBtn">목록</button>
            <div class="content-modify-delete">
                <button class="content-modifyBtn">수정</button>
                <button class="content-deleteBtn">삭제</button>
            </div>
        </div>
        <br><hr><br>

        <!-- 여기서부터는 댓글칸 -->
        <h2>댓글 3</h2>
        
        <div class="reply">
            <div class="writer-content">
                <span class="writer">박춘봉</span>
                <span class="reply-content">안녕하세요 춘봉이에요</span>
            </div>

            <div class="date-button">
                <span class="date">2023-01-23</span>
                <span class="reply-button">
                    <button class="reply-modifyBtn">수정</button>
                    <button class="reply-deleteBtn">삭제</button>
                </span>
            </div>
        </div>
    </div>
</body>
</html>