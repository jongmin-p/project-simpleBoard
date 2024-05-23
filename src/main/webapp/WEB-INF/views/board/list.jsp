<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/board/list.css" rel="stylesheet" type="text/css">
    <style>
        /* 전체 너비 (부모가 body) */
        .container {
            margin: 0 auto;
            width: 70%;
        }

        /* 검색창 */
        #searchBar {
            /* <div> 클래스 공간 중 끝(오른쪽)으로 */
            text-align: end;
        }

        #searchBtn {
            color: #fff;
            font-size: 13px;
            background: grey;
            border: none;
            padding: 6px;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 테이블 너비 (부모가 container) */
        table {
            width: 100%;
        }

        tr {
            display: flex;
            padding: 5px 0px;
        }

        thead tr {
            background-color: lightgreen;
        }

        tbody tr:hover {
            background-color: lightgray;
            cursor: pointer;
        }

        /* cell 별로 간격 조절했음 */
        tr>th:nth-child(1), tr>td:nth-child(1) {
            flex-basis: 8%;
            /* border: 1px solid blue; */
            text-align: center;
        }

        tr>th:nth-child(2), tr>td:nth-child(2) {
            flex-basis: 54%;
            /* border: 1px solid blue; */
            padding-left: 20px;
        }

        tr>th:nth-child(3), tr>td:nth-child(3) {
            flex-basis: 15%;
            /* border: 1px solid blue; */
            text-align: center;
        }

        tr>th:nth-child(4), tr>td:nth-child(4) {
            flex-basis: 15%;
            /* border: 1px solid blue; */
            text-align: center;
        }

        tr>th:nth-child(5), tr>td:nth-child(5) {
            flex-basis: 8%;
            /* border: 1px solid blue; */
            text-align: center;
        }

        /* 글쓰기 버튼 위치 조절용 부모 태그 (button 이 inline 이라서 이렇게 썼음) */
        .writeBtn {
            display: flex;
            justify-content: flex-end;
        }

        .writeBtn button {
            color: #fff;
            font-size: 13px;
            background: #333;
            border: none;
            padding: 6px;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 페이지 숫자 정렬 */
        .page {
            background-color: yellow;
            display: flex;
            justify-content: center;
            list-style: none;
            margin-top: 30px;
            padding: 10px 0px;
        }

        /* 화살표 및 숫자들 설정 */
        .page a {
            font-size: 18px;
            font-weight: bolder;
            margin: 0px 10px;
            cursor: pointer;
        }

        .page a:hover {
            color: red;
        }
    </style>

    <!-- JS 연결 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/board/list.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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

        <%-- 게시글 테이블 --%>
        <table border="1">
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>이름</th>
                <th>등록일</th>
                <th>조회수</th>
            </tr>
            </thead>

            <tbody id="list">
            <c:forEach var="boardDto" items="${list}">
                <tr class="eachRow">
                    <td>${boardDto.boardNo}</td>
                    <td><a href="<c:url value='/board/read?boardNo=${boardDto.boardNo}&page=${page}&pageSize=${pageSize}'/>">${boardDto.title}</a></td>
                    <td>${boardDto.writer}</td>
                    <td><fmt:formatDate value="${boardDto.regDate}" pattern="yyyy-MM-dd" /></td>
                        <%--                    <td>${boardDto.regDate}</td>--%>
                    <td>${boardDto.viewCnt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>


        <div class="writeBtn">
            <button class="writeBtn" onclick="location.href='<c:url value="/board/write"/>'">글쓰기</button>
        </div>


        <%-- 페이지 네비게이션 --%>
        <div class="page">
            <%-- 페이지 핸들러(ph) 의 showPrev 가 true 이면 보여주기 --%>
            <c:if test="${ph.showPrev}">
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
    </div>

    <script>
        let msg = "${msg}";

        if(msg == "DEL_OK") {
            alert("성공적으로 삭제되었습니다!");
        } else if(msg == "DEL_ERR") {
            alert("삭제에 실패했습니다!");
        } else if(msg == "WRT_OK") {
            alert("성공적으로 글이 등록되었습니다!");
        }
    </script>
</body>
</html>