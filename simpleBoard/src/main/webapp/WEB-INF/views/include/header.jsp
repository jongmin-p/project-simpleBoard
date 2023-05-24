<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="loginOutLink" value="${sessionScope.id == null ? '/login/login' : '/login/logout'}" />
<c:set var="loginOut" value="${sessionScope.id == null ? 'Login' : 'Logout'}" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <style>
        .header {
            margin: 0 auto;
            /* background-color: grey; */
            width: 70%;
            display: flex;
            justify-content: flex-end;
            /* height: 10%; */
        }

        #main-menu {
            display: flex;
            justify-content: space-around;
            width: 40%;
            /* align-items: center; */
        }

        #sub-menu {
            position: absolute;
            opacity: 0;
            visibility: hidden;
        }

        #main-menu > li:hover #sub-menu {
            opacity: 1;
            visibility: visible;
            background-color: lightyellow;
            margin-top: 1px;
        }

        #main-menu,
        #sub-menu {
            padding: 10px;
            list-style-type: none;
        }

        a {
            text-decoration: none;
            color: black;
            font-weight: bolder;
            display: inline-block;
            padding: 10px;
        }

        #sub-menu > li > a {
            display: inline-block;
            padding-bottom: 10px;
        }

        ul li:hover {
            background-color: lightcoral;
        }
    </style>
</head>

<body>
    <div class="header">
        <ul id="main-menu">
            <li><a href="<c:url value='/'/>">Home</a></li>

            <li><a href="<c:url value='/board/list'/>">Board</a></li>

<%--            <li>--%>
<%--                <a href="#">My Page</a>--%>
<%--                <ul id="sub-menu">--%>
<%--                    <li><a href="#">My Profile</a></li>--%>
<%--                    <li><a href="#">Etc</a></li>--%>
<%--                </ul>--%>
<%--            </li>--%>

            <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        </ul>
    </div>
</body>

</html>