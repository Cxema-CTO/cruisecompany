<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>

    <link rel="icon" href="${pageContext.request.contextPath}/assets/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/favicon.ico" type="image/x-icon"/>


    <style>
        body, section, html {
            max-width: 1280px;
            margin: 0 auto;
            padding: 0;
            font-family: montserrat, roboto, sans-serif;
            background: #737373;
        }

        body {
            height: auto;
            display: flex;
            flex-direction: column;
            /*justify-content: space-between;*/
            background-color: #f3f3f3;
        }


        form {
            display: flex;
            justify-content: center;
        }

        .inCenter {
            display: flex;
            justify-content: center;
        }

        .spaceBetween {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .spaceAround {
            display: flex;
            justify-content: space-around;
            align-items: center;
        }

        .textAlignCenter {
            text-align: center;
        }

        .margin8px {
            margin: 8px;
        }

        .marginLeft8px {
            margin-left: 8px;
        }

        .marginRight8px {
            margin-right: 8px;
        }

        .container {
            width: 320px;
            padding: 25px;
            margin: 32px;
            color: white;
            text-shadow: 0 0 4px darkgray, 0 0 16px darkgray;
            /*background-color: lightgray;*/
            background-color: #cacaca;
            border-radius: 8px;
        }

        /*.adminPanel {*/
        /*    width: 320px;*/
        /*    padding: 4px;*/
        /*    margin: 4px;*/
        /*    color: white;*/
        /*    text-shadow: 0 0 4px darkgray, 0 0 16px darkgray;*/
        /*    !*background-color: lightgray;*!*/
        /*    background-color: #cacaca;*/
        /*    border-radius: 8px;*/
        /*}*/

        .adminPanel {
            background-color: #e6e6e6;
        }


        .width100percent {
            width: 100%;
        }

        .containerWidth1024 {
            width: 1024px;
        }

        .containerWidth720 {
            width: 720px;
        }

        .containerWidth480 {
            width: 480px;
        }

        button {
            background-color: #263b5d;
            border: 2px solid rgba(38, 59, 93, 0);
            padding: 12px 20px;
            width: 100%;
            margin: 8px 0;
            color: white;
            cursor: pointer;
            font-weight: bolder;
            /*transition: all 0.3s ease;*/
            border-radius: 8px;
        }

        button:hover {
            background-color: #ff8c00;
            /*border: 2px solid #ff8c00;*/
            color: white;
            /*transition: all 0.3s ease;*/
        }

        .buttonSmall {
            width: 124px;
            margin-left: 8px;
            margin-right: 8px;
        }

        .buttonMiddle {
            width: 156px;
        }

        .buttonAdminPanel {
            width: 156px;
            margin-left: 8px;
            margin-right: 8px;
            padding: 4px;
        }

        .buttonError:hover {
            background-color: white;
            /*border: 2px solid white;*/
            color: #263b5d;
            /*transition: all 0.3s ease;*/
        }

        /*select, input[type=text], input[type=password], input[type=number], textarea {*/
        select, input, textarea {
            width: 100%;
            margin: 8px 0;
            padding: 12px 20px;
            display: inline-block;
            border: 2px solid rgba(255, 255, 255, 0);
            box-sizing: border-box;
        }

        input[type=checkbox] {
            width: auto;
        }

        .editButton {
            width: 12px;
        }

        .editButton:hover {
            content: url("${pageContext.request.contextPath}/assets/edit_hover.png");
        }

        textarea {
            height: 72px;
            border-radius: 8px;
        }

        select, input {
            border-radius: 8px;
        }

        input[type=checkbox] {
            margin-top: 8px;
        }

        input[type=checkbox]:checked {
            accent-color: #263b5d;
        }

        input[type=radio]:checked {
            accent-color: #263b5d;
        }

        input.inputSmall {
            width: 112px;
        }

        input.inputMiddle {
            width: 156px;
        }

        option.cruiseOption {
            font-size: large;
        }

        #money {
            margin-bottom: 16px;
            text-align: center;
            font-size: x-large;
            font-weight: bold;
        }

        #loginFormLabel {
            margin-bottom: 8px;
            text-align: center;
            font-size: x-large;
            font-weight: bold;
        }

        #adminFormLabel {
            padding: 8px;
            /*margin-bottom: 8px;*/
            text-align: center;
            font-weight: bold;
        }

        .errorModal {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            display: flex;
            align-content: center;
            justify-content: center;
            align-items: center;
            background-color: #ff8c00;
            color: white;
            padding: 16px 32px;
            min-width: 320px;
            min-height: 160px;
            border-radius: 8px;
        }

        .errorImage {
            background-color: white;
            padding: 8px;
            border-radius: 8px;
            margin: 12px;
        }

        .hideModal {
            visibility: hidden;
            position: fixed;
            left: 0;
            top: 0;
            height: 100%;
            width: 100%;
            overflow: hidden;
            background-color: rgba(40, 40, 40, 0.2);
            z-index: 100000;
            transition: all 0s ease;
            backdrop-filter: blur(5px);
        }


        .userTop {
            background-color: white;
            display: flex;
            justify-content: flex-start;
            align-content: center;
            align-items: center;
        }

        .userIcon {
            height: 24px;
            margin-left: 32px;
        }

        .userTopLine {
            padding: 8px;
        }

        #userTopLoginButton {
            height: 36px;
            margin-left: 4px;
        }

        #userTopCloseButton {
            height: 32px;
            margin-left: 14px;
            padding: 2px;
        }

        #userTopCloseButton:hover {
            content: url("${pageContext.request.contextPath}/assets/exitHover.png");
        }

        #userTopCartButton {
            height: 28px;
            margin-left: 6px;
        }

        #userTopCartButton:hover {
            content: url("${pageContext.request.contextPath}/assets/cartHover.png");
        }

        #userTopLoginButton:hover {
            content: url("${pageContext.request.contextPath}/assets/loginHover.png");
        }

        .filterIcon {
            height: 24px;
            padding: 8px;
            margin-right: 8px;
        }

        .filterIcon:hover {
            cursor: pointer;
            box-shadow: inset 0 0 18px 0 rgba(223, 223, 223, 1);
            border-radius: 8px;
        }

        .viewPassword {
            background-image: url("${pageContext.request.contextPath}/assets/view.png");
            height: 16px;
            padding: 4px;
            cursor: pointer;
        }


        .workspace {
            background: gainsboro;
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: row;
            align-items: stretch;
            gap: 8px;
            padding-bottom: 36px;
        }

        .left_sidebar {
            background: lightgray;
            width: 180px;
            min-width: 180px;
            padding: 8px;
        }

        #paginationButtonView {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #paginationButtonBefore, #paginationButtonNext {
            margin: 12px;
            padding: 0;
            display: inline-block;
            width: 72px;
            height: 24px;
        }

        /*table*/
        table {
            /*table-layout: fixed;*/
            width: 100%;
            border: 2px solid #e3e3e3;
        }

        th, td {
            min-width: 42px;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #04AA6D;
            color: white;
        }

        th:hover {
            cursor: pointer;
        }

        tr {
            background-color: #f1f1f1;
        }

        tr:nth-child(even) {
            background-color: #e3e3e3;
        }

        tr:hover {
            background-color: coral;
            color: white;
        }

        .tableButton {
            padding: 4px;
            margin: 4px;
            background-color: rgba(0, 0, 0, 0.2);
            border-radius: 6px;
            color: white;
        }

        .tableButton:hover {
            cursor: pointer;
            background-color: #04AA6D;
        }

        .shipImage {
            height: 72px;
            width: 152px;
            object-fit: cover;
            object-position: center;
        }

        .shipImageCart {
            max-width: 640px;
            object-fit: cover;
            object-position: center;
            border-radius: 8px;
        }


        .imgPassportPhoto {
            max-height: 48px;
            max-width: 48px;
            padding: 4px
        }

    </style>
</head>
</html>
