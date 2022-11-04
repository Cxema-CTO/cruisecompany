<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<div class="userTop">
    <img class="userIcon" src="assets/user.png">
    <div id="userNameTop" class="userTopLine">${sessionScope.user.userName}</div>
    <div id="userBalanceTop" class="userTopLine">₿${sessionScope.user.accountBalance}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=CART">
        <img id="userTopCartButton" src="assets/cart.png"></a>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png"></a>
</div>

