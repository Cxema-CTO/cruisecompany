<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>

<jsp:include page="style.jsp"/>

<head>
    <jsp:include page="header.jsp"/>
</head>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>


<body>
<div class="userTop">
    <img class="userIcon" src="assets/admin.png" alt="user">
    <div class="userTopLine">${sessionScope.user.userName}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png" alt="exit"></a>
</div>


<h3 class="margin8px"><fmt:message key="orders"/>: ${ordersCount}</h3>

<div class="inCenter">
<table id="tableShips" class="containerWidth480">
    <tr id="tableHeader">
        <th onclick="orderBy('id')"><fmt:message key="id"/></th>
        <th onclick="orderBy('client_id')"><fmt:message key="client.id"/></th>
        <th onclick="orderBy('cruise_id')"><fmt:message key="cruise.id"/></th>
        <th onclick="orderBy('paid')"><fmt:message key="paid"/></th>
        <th onclick="orderBy('confirmed')"><fmt:message key="confirmed"/></th
    </tr>
    <c:forEach items="${orders}" var="i">
        <tr class="rows" onclick="getIDfromTable(${i.id})">
            <td>${i.id}</td>
            <td>${i.clientId}</td>
            <td>${i.cruiseId}</td>
            <td>
                <c:choose>
                    <c:when test="${i.paid == true}"><fmt:message key="yes"/></c:when>
                    <c:when test="${i.paid == false}"><fmt:message key="no"/></c:when>
                </c:choose>
            </td>
            <td>
                <div class="inCenter">
                    <c:choose>
                        <c:when test="${i.confirmed == true}"><fmt:message key="yes"/></c:when>
                        <c:when test="${i.confirmed == false}"><fmt:message key="no"/></c:when>
                    </c:choose>
                </div>
                <c:if test="${sessionScope.role == 'admin'}">
                    <form action="${pageContext.request.contextPath}/controller?open=CONFIRM_CRUISE" method="post"
                          accept-charset="UTF-8">
                        <input type="hidden" value="${i.id}" name="orderId">
                        <button class="tableButton inCenter" type="submit">
                            <c:choose>
                            <c:when test="${i.confirmed == true}">
                                <fmt:message key="reject"/>
                            </c:when>
                            <c:when test="${i.confirmed == false}">
                                <fmt:message key="confirm"/>
                            </c:when>
                            </c:choose>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</div>

<c:if test="${pagination=='yes'}">
    <div id="paginationButtonView">
        <button onclick="beforeButtonClick()" id="paginationButtonBefore">←</button>
        <div>
            <c:out value="${currentPage}"/> <fmt:message key="of.page"/> <c:out value="${totalPages}"/>
        </div>
        <button onclick="nextButtonClick()" id="paginationButtonNext">→</button>
    </div>
</c:if>

<div class="inCenter">
    <%--            <button class="buttonSmall"--%>
    <%--                    onclick="location.href='${pageContext.request.contextPath}/controller?open=ADD_SHIP'"--%>
    <%--                    type="button"><fmt:message key="add"/></button>--%>
    <button class="buttonSmall"
            onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
            type="button"><fmt:message key="back"/></button>
</div>
</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>

<%--script--%>
<script defer type="text/javascript">
    let xhr = new XMLHttpRequest();
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                location.href = '${pageContext.request.contextPath}/controller?open=ORDERS'
            } else {
                console.error(xhr.statusText);
            }
        }
    }

    // pagination
    function beforeButtonClick() {
        xhr.open("POST", "PAGINATION?click=before", true);
        xhr.send();
    }

    function nextButtonClick() {
        xhr.open("POST", "PAGINATION?click=next", true);
        xhr.send();
    }

    function orderBy(column) {
        xhr.open("POST", "PAGINATION?orderBy=" + column, true);
        xhr.send();
    }

    function getIDfromTable(id) {
        console.log(id)
    }
</script>

