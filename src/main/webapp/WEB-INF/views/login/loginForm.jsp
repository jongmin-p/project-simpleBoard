<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <!-- CSS 연결 -->
    <link href="${pageContext.request.contextPath}/css/login/loginForm.css" rel="stylesheet" type="text/css">
    <!-- JS 연결 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login/loginForm.js"></script>
    <!-- Jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<form action="<c:url value='/login'/>" method="POST" class="login-wrapper" onsubmit="return formCheck(this);">
    <h2>로그인</h2>

    <div class="msg"></div>
    <input type="text" name="userId"      placeholder="ID를 입력하세요"      value="${cookie.id.value}" />
    <input type="password" name="userPwd" placeholder="비밀번호를 입력하세요" />

    <label for="rememberId">
        <input type="checkbox" id="rememberId" name="rememberId" ${empty cookie.id.value ? "" : "checked"}> 아이디 저장
    </label>

    <input type="submit" id="loginBtn" value="로그인" />

    <div>
        <div class="info-wrapper">
            <a href="/" />ID 찾기</a> |
            <a href="/" />비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <button type="button" id="signUpBtn" onclick="window.location.href='<c:url value='/signup'/>';">회원가입</button>
        </div>
    </div>
</form>

<script>
    function formCheck(frm) {
        let user_id = $("input[name='userId']").val();
        let user_pwd = $("input[name='userPwd']").val();

        $.ajax({
            url: "<c:url value='/login/loginCheck'/>",
            type: "POST",
            data: {
                id: user_id,
                pwd: user_pwd
            },
            success: function (result) {
                let parsedResult = JSON.parse(result);

                // 1. 아이디가 존재하지 않을 때, 혹은 아이디가 빈 칸일 때
                if(parsedResult.retCode == "Fail-ID" || user_id == "") {
                    $(".msg:nth-of-type(1)").text("존재하지 않는 아이디입니다.");
                    $(".msg:nth-of-type(1)").css({
                        'color': 'red',
                        'font-weight': 'bolder'
                    });

                    return false;

                    // 2. 아이디는 있으나, 비밀번호가 일치하지 않을 때
                } else if (parsedResult.retCode == "Fail-PWD" || user_pwd == "") {
                    $(".msg:nth-of-type(1)").text("비밀번호가 일치하지 않습니다.");
                    $(".msg:nth-of-type(1)").css({
                        'color': 'red',
                        'font-weight': 'bolder'
                    });

                    return false;
                }

                // 3. 로그인 성공 시 폼 제출
                frm.submit();
            },
            error: function () {
                console.log("ERROR 입니다!");
            }
        });

        // Ajax 호출 후 폼이 전송되는 것을 방지하기 위해 일단 false 로 폼 전송 막기 (비동기 전송 방지)
        return false;
    }
</script>
</body>
</html>