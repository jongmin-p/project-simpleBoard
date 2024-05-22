<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>

    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/board/board.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>
<body>
    <jsp:include page="/WEB-INF/views/include/header.jsp" />


    <%-- 게시물 읽기 / 게시물 수정 모두 board.jsp 사용할 예정. --%>
    <%-- 수정 시 readonly 제외, 읽을 시에는 readonly 포함 --%>

    <h2>게시물 ${mode == "new" ? "글쓰기" : "읽기"}</h2>

    <form action="" id="form">
        <input type="hidden" name="boardNo" value="${boardDto.boardNo}">

        <div class="writer">
            <h3>작성자</h3>
            <input type="text" name="writer" value="${boardDto.writer}" readonly>
        </div>

        <div class="title">
            <h3>제목</h3>
            <input type="text" name="title" value="${boardDto.title}" ${mode == "new" ? "" : "readonly"}>
        </div>

        <div class="content">
            <h3 class="content-header">내용</h3>
            <textarea name="content" cols="30" rows="10" ${mode == "new" ? "" : "readonly"}>${boardDto.content}</textarea>
        </div>


        <div class="button">
            <%-- 글쓰기 모드 일 때만 '글쓰기' 버튼 보이도록 --%>
            <c:if test="${mode == 'new'}">
                <button type="button" id="writeBtn">글쓰기</button>
            </c:if>
                <%-- 지금 접속해있는 유저가 해당 게시글의 작성자라면 수정/삭제 버튼 보여줌 --%>
                <%-- mode == null  이거 없으면, 글쓰기 버튼 눌렀을 때도, 수정/버튼이 생김 --%>
                <c:if test="${currentUser == boardDto.writer && mode == null}">
                    <button type="button" id="modifyBtn">수정</button>
                    <button type="button" id="removeBtn">삭제</button>
                </c:if>


            <%-- 목록 버튼은 공통 버튼 --%>
            <button type="button" id="listBtn">목록</button>
        </div>
    </form>

    <script>
        $(document).ready(function() {

            // 기존에 보던 게시판 페이지로 이동.
            $("#listBtn").on("click", function() {
                location.href="<c:url value='/board/list?page=${page}&pageSize=${pageSize}'/>";
            });


            $("#removeBtn").on("click", function() {
                if(!confirm('정말로 삭제하시겠습니까?')) {
                    return ;			// 아니라고 하면 빠져 나감.
                }

                // POST 이기 때문에, form 에 대한 참조 가져오기
                let frm = $('#form');

                frm.attr("action", "<c:url value='/board/remove?page=${page}&pageSize=${pageSize}'/>");
                frm.attr("method", "POST");
                frm.submit();			// 내용 전송

            });


            $("#writeBtn").on("click", function() {
                // POST 이기 때문에, form 에 대한 참조 가져오기
                let frm = $('#form');

                frm.attr("action", "<c:url value='/board/write'/>");
                frm.attr("method", "POST");
                frm.submit();			// form 에 작성한 내용들을 전송
            });


            $("#modifyBtn").on("click", function() {
                // 수정 버튼 누르면, 삭제 버튼은 안보이게!
                $('#removeBtn').css("display", "none");

                // 1. 읽기 상태이면 수정 상태로 변경
                let frm = $('#form');
                let isReadOnly = $('input[name=title]').attr('readonly');

                if(isReadOnly == 'readonly') {
                    $('input[name=title]').attr('readonly', false);     // 제목
                    $('textarea').attr('readonly', false);              // 본문 내용
                    $('#modifyBtn').html('등록');                        // 수정이라는 문구를 등록이라는 문구로
                    $('h2').html('게시물 수정');

                    return ;       // 수정 상태일 때는 위의 것들만 바꾸고  빠져나가야 함. 이거 안하면 전송됨.
                }

                // 2. 수정 상태이면 수정된 내용을 서버로 전송
                frm.attr("action", "<c:url value='/board/modify'/>");
                frm.attr("method", "POST");
                frm.submit();			// form 에 작성한 내용들을 전송
            });
        });
    </script>
</body>
</html>
