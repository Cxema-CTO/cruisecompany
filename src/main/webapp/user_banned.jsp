<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>

<jsp:include page="style.jsp"/>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>


<body>
<div class="userTop">
    <img class="userIcon" src="assets/banned.png" alt="banned icon">
    <div id="userNameTop" class="userTopLine">${sessionScope.user.userName}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png" alt="exit icon"></a>
</div>

<div class="inCenter">
    <div class="container">
        <h2><fmt:message key="banned.message"/></h2>
        <br>
        <div class="inCenter"><img class="errorImage" width="164px" src="assets/banStamp.png" alt="banned gif">
        </div>
    </div>
</div>
</body>

</html>