<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Start App</title>
    <style>
        .loadingLogo {
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            background-image: url("${pageContext.request.contextPath}/assets/loadingShip.gif");
            background-origin: border-box;
            background-repeat: round;
            width: 320px;
            height: 240px;
            border-radius: 500px;
            /*box-shadow: 0 0 50px 0 rgba(172, 219, 255, 1);*/
        }
    </style>
</head>
<body>

<div class="loadingLogo">
</div>

</body>
</html>

<%--script--%>
<script defer type="text/javascript">
    location.href = '${pageContext.request.contextPath}/controller?open=INDEX'
</script>
