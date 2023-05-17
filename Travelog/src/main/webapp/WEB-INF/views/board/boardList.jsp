<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>

<!-- CSS 연결 -->
<link
	href="${pageContext.request.contextPath}/resources/css/board/boardList.css"
	rel="stylesheet" type="text/css">
<!-- Jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<!-- JS 연결 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/board/boardList.js"></script>
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
		    		<%-- <c:forEach items="${eqList }" var="eqm">
						<tr class="eachRow">
							<td>${eqm.idx }</td>
							<td>${eqm.eqmFg }</td>
							<td>${eqm.eqmNm }</td>
							<td>${eqm.eqmCd }</td>
							<td>${eqm.prcsNm }</td>
							<td>${eqm.prcsCd }</td>					
							<td>${eqm.eqmYn }</td>
							<td>${eqm.minTemp }</td>
							<td>${eqm.maxTemp }</td>
							<td>${eqm.chckPerd }</td>
							<td>${eqm.lineCd }</td>
							<td><fmt:formatDate value="${eqm.eqmIstDt }" pattern="yyyy-MM-dd" /></td>
							<td>${eqm.eqmPsch }</td>
						</tr>
					</c:forEach> --%>
                <tr class="eachRow">
                    <td>1</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>2</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>3</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>4</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>5</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr>
	                <td>6</td>
	                <td>제목입니다</td>
	                <td>김팔춘</td>
	                <td>2021-03-21</td>
	                <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>7</td>
                    <td>제목입니다제목입니다제목입니다입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>8</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>9</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
                <tr class="eachRow">
                    <td>10</td>
                    <td>제목입니다</td>
                    <td>김팔춘</td>
                    <td>2021-03-21</td>
                    <td>5</td>
                </tr>
            </tbody>
        </table>

        <div class="writeBtn">
            <button class="writeBtn">글쓰기</button>
        </div>

        <ul class="page">
            <li><</li>
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
            <li>6</li>
            <li>7</li>
            <li>8</li>
            <li>9</li>
            <li>10</li>
            <li>></li>
        </ul>
    </div>
</body>
</html>