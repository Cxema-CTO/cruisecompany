<%@ page import="com.example.cruisecompany.controller.Pagination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<div class="userTop">
    <img class="userIcon" src="assets/admin.png" alt="user">
    <div class="userTopLine">${sessionScope.user.userName}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png" alt="exit"></a>
</div>

<div class="inCenter">
    <div class="spaceBetween">
        <button class="buttonAdminPanel"
                onclick="location.href='${pageContext.request.contextPath}/controller?open=ORDERS'"
                type="button"><fmt:message key="orders"/>
        </button>
        <button class="buttonAdminPanel"
                onclick="location.href='${pageContext.request.contextPath}/controller?open=CRUISES'"
                type="button"><fmt:message key="cruises"/>
        </button>
        <button class="buttonAdminPanel"
                onclick="location.href='${pageContext.request.contextPath}/controller?open=SHIPS'"
                type="button"><fmt:message key="ships"/>
        </button>
        <button class="buttonAdminPanel"
                onclick="location.href='${pageContext.request.contextPath}/controller?open=USERS'"
                type="button"><fmt:message key="users"/>
        </button>
    </div>
</div>

<script defer type="text/javascript">

</script>