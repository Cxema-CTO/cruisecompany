<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>

<jsp:include page="style.jsp"/>

<head>
    <jsp:include page="header.jsp"/>
</head>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<form action="${pageContext.request.contextPath}/controller?open=LOGIN_USER" method="post">
    <div class="container">
        <div id="loginFormLabel"><fmt:message key="title.login"/></div>
        <input type="text" maxlength="30" placeholder=
        <fmt:message key="username"/> name="username" required>
        <input type="password" maxlength="30" placeholder=
        <fmt:message key="password"/> name="password" required>
        <button type="submit"><fmt:message key="enter"/></button>
        <%--        <input type="checkbox"> <fmt:message key="rememberMe"/>--%>
        <button onclick="location.href='${pageContext.request.contextPath}/controller?open=REGISTRATION_PAGE'"
                type="button"><fmt:message key="newUser"/></button>
    </div>
</form>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>