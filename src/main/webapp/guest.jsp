<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<div class="userTop">
    <img class="userIcon" src="assets/guest.png">
    <div id="userTopLine" class="userTopLine"> <fmt:message key="welcome"/>, <fmt:message key="guest"/></div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=LOGIN">
        <img id="userTopLoginButton" src="assets/login.png"></a>
</div>

