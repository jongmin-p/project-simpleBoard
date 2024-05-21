<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/board/list.css" rel="stylesheet" type="text/css">
    <!-- JS 연결 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/board/list.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<body>
    <jsp:include page="/WEB-INF/views/include/header.jsp" />

    <%-- 게시글 테이블 --%>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>이름</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <c:forEach var="boardDto" items="${list}">
            <tr>
                <td>${boardDto.boardNo}</td>
                <td><a href="<c:url value='/board/read?boardNo=${boardDto.boardNo}&page=${page}&pageSize=${pageSize}'/>">${boardDto.title}</a></td>
                <td>${boardDto.writer}</td>
                <td>${boardDto.regDate}</td>
                <td>${boardDto.viewCnt}</td>
            </tr>
        </c:forEach>
    </table>
    <br>

    <%-- 페이지 네비게이션 --%>
    <div>
        <%-- 페이지 핸들러(ph) 의 showPrev 가 true 이면 보여주기 --%>
        <c:if test="${ph.showPrev}">z
            <a href="<c:url value='/board/list?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
        </c:if>

        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
        </c:forEach>

        <%-- 페이지 핸들러(ph) 의 showNext 가 true 이면 보여주기 --%>
        <c:if test="${ph.showNext}">
            <a href="<c:url value='/board/list?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
        </c:if>
    </div>
</body>
</html>