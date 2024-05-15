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
    <!-- SweetAlert -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
<jsp:include page="/WEB-INF/views/include/header.jsp" />

<form action="<c:url value='/signup'/>" method="POST" class="info-wrapper">
    <h2>회원 가입</h2>

    <div class="msg"></div>
    <input type="text"     name="userId"     placeholder="아이디 입력 (5 ~ 15글자 사이의 영문자 및 숫자)" autofocus /><br />

    <div class="msg"></div>
    <input type="password" name="userPwd"    placeholder="비밀번호를 입력해 주세요 (5 ~ 15글자)" /><br />

    <div class="msg"></div>
    <input type="password" name="userPwd222" placeholder="비밀번호를 다시 한 번 입력해 주세요" /><br />

    <div class="msg"></div>
    <input type="text"     name="userName"   placeholder="이름을 입력해 주세요" /><br />

    <div class="msg"></div>
    <input type="text"     name="userPhone"  placeholder="연락처를 입력해 주세요  (010-1234-5678)" /><br />

    <div class="msg"></div>
    <input type="text"    name="userEmail"  placeholder="이메일을 입력해 주세요" /><br />

    <input type="hidden" name="status" value="Y" /><br />
    <input type="hidden" name="isUser" value="Y" /><br />

    <button type="submit" id="signUpBtn">가입</button>
    <button type="reset"  id="cancelBtn">취소</button>
</form>


<%-- 제이 쿼리는 여기에, 자바스크립트 코드는 따로 연결 --%>
<script>
    // 1. 아이디 검증 메서드
    function idCheckMsg(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(1)").text("아이디를 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(1)").text("중복된 아이디입니다.");
                break;
            case 3:
                $(".msg:nth-of-type(1)").text("올바른 양식으로 입력해 주세요.");
                break;
            case 4:
                $(".msg:nth-of-type(1)").text("이용 가능한 아이디입니다.");
                break;
        }

        // 이용 가능한 아이디인 경우
        if(optionNum == 4) {
            $(".msg:nth-of-type(1)").css({
                'color': 'yellowgreen',
                'font-weight': 'bolder'
            });
        } else {    // 이용 가능하지 않은 경우
            $(".msg:nth-of-type(1)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }

    // 2. 비밀번호 검증 메서드
    function pwdCheckMsg(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(2)").text("비밀번호를 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(2)").text("비밀번호는 5 ~ 15글자만 허용됩니다.");
                break;
        }

        // 에러 메시지 css
        if(optionNum == 1 || optionNum == 2) {
            // 비밀번호 에러 메시지 보이도록 (혹시나 .hide() 로 삭제된 경우를 대비)
            $(".msg:nth-of-type(2)").show();

            $(".msg:nth-of-type(2)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }

    // 3. 재입력 비밀번호 검증 메서드
    function pwdDoubleCheck(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(3)").text("비밀번호를 다시 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(3)").text("비밀번호가 일치하지 않습니다.");
                break;
        }

        // 에러 메시지 css
        if(optionNum == 1 || optionNum == 2) {
            // 비밀번호 에러 메시지 보이도록 (혹시나 .hide() 로 삭제된 경우를 대비)
            $(".msg:nth-of-type(3)").show();

            $(".msg:nth-of-type(3)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }

    // 4. 이름 검증 메서드
    function nameCheckMsg(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(4)").text("이름을 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(4)").text("이름은 영문 혹은 한글만 가능합니다.");
                break;
        }

        // 에러 메시지 css
        if(optionNum == 1 || optionNum == 2) {
            // 비밀번호 에러 메시지 보이도록 (혹시나 .hide() 로 삭제된 경우를 대비)
            $(".msg:nth-of-type(4)").show();

            $(".msg:nth-of-type(4)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }

    // 5. 이름 검증 메서드
    function phoneCheckMsg(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(5)").text("연락처를 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(5)").text("잘못된 연락처 양식입니다. (010-1234-5678)");
                break;
        }

        // 에러 메시지 css
        if(optionNum == 1 || optionNum == 2) {
            // 비밀번호 에러 메시지 보이도록 (혹시나 .hide() 로 삭제된 경우를 대비)
            $(".msg:nth-of-type(5)").show();

            $(".msg:nth-of-type(5)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }

    // 6. 이메일 검증 메서드
    function emailCheckMsg(optionNum) {
        switch (optionNum) {
            case 1:
                $(".msg:nth-of-type(6)").text("이메일을 입력해 주세요.");
                break;
            case 2:
                $(".msg:nth-of-type(6)").text("잘못된 이메일 양식입니다.");
                break;
        }

        // 에러 메시지 css
        if(optionNum == 1 || optionNum == 2) {
            // 비밀번호 에러 메시지 보이도록 (혹시나 .hide() 로 삭제된 경우를 대비)
            $(".msg:nth-of-type(6)").show();

            $(".msg:nth-of-type(6)").css({
                'color': 'red',
                'font-weight': 'bolder'
            });
        }
    }


    // 7. submit 승인 메서드
    function submitOK() {
        $("button[type='submit']").css({
            'background-color': '#6A24FE'
        });

        // 혹시나 submit 기능 막힌게 있다면, 해제
        $("button[type='submit']").removeAttr('disabled');
    }

    // 8. submit 막는 메서드
    function submitNO() {
        // 제출 버튼 색 변하게 만들고,
        $("button[type='submit']").css({
            'background-color': 'grey'
        });

        // submit 도 막음
        $("button[type='submit']").attr("disabled", "disabled");
    }


    // jQuery
    $(document).ready(function () {
        // 1. 아이디 검증
        $("input[name='userId']").blur(function() {
            let user_id = $("input[name='userId']").val();
            console.log("user_id => " + user_id);

            $.ajax({
                url: "<c:url value='/signup/idCheck'/>",
                type: "POST",
                data: { id: user_id },
                success: function (result) {
                    // SignupController.java 로 부터 응답받은 걸 다시 파싱
                    let parsedResult = JSON.parse(result);

                    // 1-1. 아이디 입력 칸 공백인 경우
                    if(parsedResult.retCode == "Blank") {
                        console.log("parsedResult.retCode => " + parsedResult.retCode);

                        idCheckMsg(1);
                        submitNO();

                        // 1-2. 아이디가 중복되는 경우
                    } else if (parsedResult.retCode == "Fail") {
                        idCheckMsg(2);
                        submitNO();

                        // 1-3. 아이디 입력 양식에 맞지 않는 경우
                    } else if(parsedResult.retCode == "RegexCheck") {
                        idCheckMsg(3);
                        submitNO();

                        // 1-4. 아이디 검증 통과
                    } else if(parsedResult.retCode == "Success") {
                        idCheckMsg(4);
                        submitOK();
                    }
                },
                error: function () {
                    console.log("통신 실패입니다.");
                },
            });
        });


        // 2. 비밀번호 검증
        $("input[name='userPwd']").blur(function() {
            let pwdLength = $("input[name='userPwd']").val().length;

            // 2-1. 비밀번호 입력하지 않았을 때
            if(pwdLength == 0) {
                submitNO();
                pwdCheckMsg(1);

                // 2-2. 입력한 비밀번호 자릿수가 통과되지 않았을 때
            } else if(pwdLength < 5 || 15 < pwdLength) {
                submitNO();
                pwdCheckMsg(2);

                // 2-3. 비밀번호 검증 통과
            } else {
                submitOK();
                // 만약 에러 메시지가 존재한다면, 삭제
                $(".msg:nth-of-type(2)").hide();
            }
        });

        // 3. 재입력한 비밀번호가 일치하는지
        $("input[name='userPwd222']").blur(function() {
            let pwd = $("input[name='userPwd']").val();
            let pwd222 = $("input[name='userPwd222']").val();

            // 3-1. 비밀번호를 재입력하지 않았을 때
            if(pwd222 === "") {
                submitNO();
                pwdDoubleCheck(1);

                // 3-2. 재입력한 비밀번호가 일치하지 않을 때
            } else if (pwd222 !== pwd) {
                submitNO();
                pwdDoubleCheck(2);

                // 3-3. 재입력 비밀번호 일치할 때
            } else {
                submitOK();
                $(".msg:nth-of-type(3)").hide();
            }
        });


        // 4. 이름 검증
        $("input[name='userName']").blur(function() {
            let name = $("input[name='userName']").val();

            // 정규식. (영문 혹은 한글만 입력 가능. 혼용도 안 됨)
            let pattern = /^[a-zA-Z]*$|^[가-힣]*$/;

            // 4-1. 이름을 입력하지 않았을 때
            if(name == "") {
                submitNO();
                nameCheckMsg(1);

                // 4-2. 이름이 적합하지 않을 때
            } else if(!pattern.test(name)) {
                submitNO();
                nameCheckMsg(2);

                // 4-3. 이름 검증 통과
            } else {
                submitOK();
                $(".msg:nth-of-type(4)").hide();
            }
        });

        // 5. 연락처 검증
        $("input[name='userPhone']").blur(function() {
            let phone = $("input[name='userPhone']").val();

            // 연락처 정규식. (010-1234-5678 처럼   숫자 3자리-숫자 4자리-숫자 4자리  만 허용)
            let pattern = /^\d{3}-\d{4}-\d{4}$/;

            // 5-1. 연락처를 입력하지 않았을 때
            if(phone == "") {
                submitNO();
                phoneCheckMsg(1);

                // 5-2. 연락처가 적합하지 않을 때
            } else if(!pattern.test(phone)) {
                submitNO();
                phoneCheckMsg(2);

                // 5-3. 연락처 검증 통과
            } else {
                submitOK();
                $(".msg:nth-of-type(5)").hide();
            }
        });

        // 6. 이메일 검증
        $("input[name='userEmail']").blur(function() {
            let email = $("input[name='userEmail']").val();

            // 이메일 정규식.
            let pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            // 6-1. 이메일을 입력하지 않았을 때
            if(email == "") {
                submitNO();
                emailCheckMsg(1);

                // 6-2. 이메일이 적합하지 않을 때
            } else if(!pattern.test(email)) {
                submitNO();
                emailCheckMsg(2);

                // 6-3. 이메일 검증 통과
            } else {
                submitOK();
                $(".msg:nth-of-type(6)").hide();
            }
        });
    })
</script>
</body>
</html>
