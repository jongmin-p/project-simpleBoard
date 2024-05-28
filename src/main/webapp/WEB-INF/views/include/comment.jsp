<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>

    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/include/comment.css" rel="stylesheet" type="text/css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
</head>

<body>
    <%-- 이걸 가지고 댓글 수정/삭제 버튼 보이게/안보이게 할 거임 --%>
    <input type="hidden" name="cUser" value="${currentUser}">

    <h3>댓글</h3>

    <%-- 댓글 출력 및 페이징 및 댓글 작성 출력     (현재 페이징 없음) --%>
    <div id="commentList"></div>


    <%-- 댓글 수정 클릭 시 나오는 모달 파트 --%>
    <div id="modalWin" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <!-- 수정 모달 창에서의 X 버튼 -->
            <span class="close">&times;</span>
            <p>

            <h2> | 댓글 수정</h2>
            <div id="modify-writebox">
                <div class="writer writer-writebox"></div>
                <div class="modify-writebox-content">
                    <textarea name="" id="" cols="30" rows="5" placeholder="댓글을 남겨보세요"></textarea>
                </div>
                <div id="modify-writebox-bottom">
                    <div class="register-box">
                        <a href="#" class="btn" id="modBtn">등록</a>
                    </div>
                </div>
            </div>
            </p>
        </div>
    </div>

    <script>
        let cUser = $("input[name=cUser]").val();
        let boardNo = $("input[name=boardNo]").val();

        // ms 를 날짜로 바꿔줌
        let dateToString = function(ms=0) {
            let date = new Date(ms);

            let yyyy = date.getFullYear();
            let mm   = addZero(date.getMonth() + 1);
            let dd   = addZero(date.getDate());

            let HH   = addZero(date.getHours());
            let MM   = addZero(date.getMinutes());
            let ss   = addZero(date.getSeconds());

            return yyyy + "." + mm + "." + dd + " " + HH + ":" + MM + ":" + ss;
        }

        let addZero = function(value = 1){
            return value > 9 ? value : "0" + value;
        }


        let toHTML = function (comments) {
            let tmp = "<ul>";

            comments.forEach(function (comment) {
                tmp += '<li class="comment-item" data-commentNo="' + comment.commentNo + '" data-boardNo="' + comment.boardNo + '">';
                tmp += '<span class="comment-img">';
                tmp += '<i class="fa fa-user-circle" aria-hidden="true"></i>';
                tmp += '</span>';

                tmp += '<div class="comment-area">';
                tmp += '<div class="comment-writer">' + comment.writer + '</div>';
                tmp += '<div class="comment">' + comment.comment + '</div>';

                tmp += '<div class="comment-bottom">';
                tmp += '<span class="modDate">' + dateToString(comment.modDate) + '</span>';

                // 현재 게시글 작성자와 댓글 작성자가 일치할 때만     댓글 수정/삭제 가능
                // 현재 게시글 작성자 (cUser),     댓글 작성자 (comment.writer)
                if(cUser == comment.writer) {
                    tmp += '<a href="#" class="modBtn" data-commentNo="' + comment.commentNo + '" data-boardNo="' + comment.boardNo + '" data-pCommentNo="' + comment.pCommentNo + '">수정</a>';
                    tmp += '<a href="#" class="delBtn" data-commentNo="' + comment.commentNo + '" data-boardNo="' + comment.boardNo + '" data-pCommentNo="' + comment.pCommentNo + '">삭제</a>';
                }

                tmp += '</div>';
                tmp += '</div>';
                tmp += '</li>';
            })

            tmp += '</ul>';


            // 댓글 작성하는 곳
            tmp += '<div id="comment-writebox">';
            tmp += '<div class="writer writer-writebox">${id}</div>';
            tmp += '<div class="comment-writebox-content">';
            tmp += '<textarea name="" id="" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>';
            tmp += '</div>';
            tmp += '<div id="comment-writebox-bottom">';
            tmp += '<div class="register-box">';
            tmp += '<a href="#" class="btn" id="sendBtn">등록</a>';
            tmp += '</div>';
            tmp += '</div>';
            tmp += '</div>';

            return tmp;
        }


        // 댓글 보여주는 메서드
        let showList = function (boardNo) {
            $.ajax({
                type:'GET',                                      // 요청 메서드
                url: '/simpleBoard/comments?boardNo=' + boardNo, // 요청 URI
                success : function(result) {                     // 서버로부터 응답이 도착하면 호출될 함수
                    $('#commentList').html(toHTML(result));      // 댓글 목록 가져온 걸 #commentList 에 집어 넣음
                },
                error   : function() {                           // 에러가 발생했을 때, 호출될 함수
                    alert("error");
                }
            });
        }


        $(document).ready(function() {
            // 처음에 특정 게시글 클릭하면, 댓글 출력
            showList(boardNo);


            // 수정 버튼 클릭 시
            $(document).on("click", "a.modBtn", function(e) {
                let target     = e.target;

                let commentNo  = target.getAttribute("data-commentNo");
                let boardNo    = target.getAttribute("data-boardNo");
                let pCommentNo = target.getAttribute("data-pCommentNo");
                let li         = $("li[data-commentNo=" + commentNo + "]");
                let writer     = $(".writer", li).first().text();
                let comment    = $(".comment", li).first().text();

                $("#modalWin .writer").text(writer);
                $("#modalWin textarea").val(comment);

                $("#modBtn").attr("data-commentNo", commentNo);
                $("#modBtn").attr("data-pCommentNo", pCommentNo);
                $("#modBtn").attr("data-boardNo", boardNo);

                // 팝업창을 열고 내용을 보여준다.
                $("#modalWin").css("display", "block");
            });


            // 수정 모달 창에서 등록 눌렀을 때
            $("#modBtn").click(function(){
                // 1. 변경된 내용을 서버로 전송
                let commentNo = $(this).attr("data-commentNo");
                let comment   = $("#modalWin textarea").val();

                // 만약 comment 가 공백이면, alert 발생
                if(comment.trim() == '') {
                    alert("수정할 댓글을 입력해 주세요!");
                    $("#modalWin textarea").focus();

                    return ;
                }

                $.ajax({
                    type:'PATCH',                                     // 요청 메서드
                    url: '/simpleBoard/comments/' + commentNo,        // 요청 URI
                    headers : { "content-type": "application/json"},  // 요청 헤더
                    data : JSON.stringify({                           // 서버로 전송할 데이터. stringify() 로 직렬화 필요.
                        commentNo: commentNo,
                        comment: comment
                    }),
                    success : function(result) {
                        alert(result);                               // 성공 시, 결과 띄어주고,
                        showList(boardNo);                           // 화면에 댓글 리스트 보여줌
                    },
                    error   : function() {                           // 에러가 발생했을 때, 호출될 함수
                        alert("error")
                    }
                });

                // 2. 모달 창을 닫는다.
                $(".close").trigger("click");
            });

            // 수정 모달 창에서의   X 버튼 클릭 시
            $(".close").click(function(){
                $("#modalWin").css("display","none");
            });


            // 댓글 작성 후, 등록 버튼 눌렀을 때
            $(document).on("click", "#sendBtn", function() {
                let comment = $(".comment-writebox-content textarea").val();

                // 만약 comment 가 공백이면, alert 발생
                if(comment.trim() == '') {
                    alert("댓글을 입력해 주세요!");
                    $(".comment-writebox-content textarea").focus();

                    return ;
                }

                $.ajax({
                    type:'POST',                                     // 요청 메서드
                    url: '/simpleBoard/comments?boardNo=' + boardNo, // 요청 URI
                    headers : { "content-type": "application/json"}, // 요청 헤더
                    data : JSON.stringify({                          // 서버로 전송할 데이터. stringify() 로 직렬화 필요.
                        boardNo: boardNo,
                        comment: comment
                    }),
                    success : function(result) {
                        alert(result);                               // 성공 시, 결과 띄어주고,
                        showList(boardNo);                           // 화면에 댓글 리스트 보여줌
                    },
                    error   : function() {                           // 에러가 발생했을 때, 호출될 함수
                        alert("error")
                    }
                });
            });

            // 댓글 삭제 메서드
            $("#commentList").on("click", ".delBtn", function() {
                // <li> 에 있는 commentNo 가져오기
                let commentNo = $(this).closest("li").attr("data-commentNo");
                let boardNo = $(this).closest("li").attr("data-boardNo");

                $.ajax({
                    type:'DELETE',                                                     // 요청 메서드
                    url: '/simpleBoard/comments/' + commentNo + '?boardNo=' + boardNo, // 요청 URI
                    success : function(result) {                                       // 서버로부터 응답이 도착하면 호출될 함수
                        alert(result);
                        showList(boardNo);                                             // 댓글을 삭제한 다음에, 새로 showList 호출해야 함
                    },
                    error   : function() {                                             // 에러가 발생했을 때, 호출될 함수
                        alert("error");
                    }
                });
            });


            // 각 댓글의 수정 버튼 클릭 시
            $("#commentList").on("click", ".modBtn", function() {
                let commentNo = $(this).closest("li").attr("data-commentNo");
                let comment = $("span.comment", $(this).closest("li")).text();

                // 1. comment 의 내용을 input 에 뿌려주기
                $("input[name=comment]").val(comment);

                // 2. commentNo 전달하기
                $("#modBtn").attr("data-commentNo", commentNo);
            });
        });
    </script>
</body>
</html>