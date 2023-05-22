<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>

<!-- CSS 연결 -->
<link href="${pageContext.request.contextPath}/css/board/boardList.css" rel="stylesheet" type="text/css">
<!-- Jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<!-- JS 연결 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/board/boardList.js"></script>
</head>


<body>
	<jsp:include page="/WEB-INF/views/include/header.jsp" />

    <div class="container">

        <!-- 검색창 -->
        <div id="searchBar">
            <select id="searchType" name="searchType" style="width: 100px">
                <option selected value="all">==선택==</option>
                <option value="T">제목</option>
                <option value="TC">제목+내용</option>
                <option value="W">작성자</option>
            </select>

            <input class="form-control" type="text" id="keyword" name="keyword" placeholder="검색어 입력" style="width: 180px"></input>
            <button type="button" id="searchBtn" class="btn btn-primary" style="margin-bottom: 3px">검색</button>
        </div>


        <!-- 조회 시 나타나는 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>No.</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>

            <!-- ↓↓↓여기에 조회된 결과 출력 -->
            <tbody id="list">
                <c:forEach items="${li}" var="board">
                    <tr class="eachRow">
                        <td>${board.bno}</td>
                        <td>${board.title}</td>
                        <td>${board.writer}</td>
                        <td>${board.reg_date}</td>
                        <td>${board.view_cnt}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="writeBtn">
            <button class="writeBtn">글쓰기</button>
        </div>


        <%-- 페이지 네비게이션 바 --%>
        <div class="page">
            <c:if test="${ph.showPrev}">
                <a href="<c:url value='/board/list?page=${ph.beginPage - 1}&pageSize=${ph.pageSize}' />"><<</a>
            </c:if>

            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                <a href="<c:url value='/board/list?page=${i}&pageSize=${ph.pageSize}'/>">${i}</a>
            </c:forEach>

            <c:if test="${ph.showNext}">
                <a href="<c:url value='/board/list?page=${ph.endPage + 1}&pageSize=${ph.pageSize}' />">>></a>
            </c:if>
        </div>
    </div>
</body>
</html>