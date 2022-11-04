<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <img class="userIcon" src="assets/user.png">
    <div id="userNameTop" class="userTopLine">${sessionScope.user.userName}</div>
    <div id="userBalanceTop" class="userTopLine">₿${sessionScope.user.accountBalance}</div>
    <%--    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=CART">--%>
    <%--        <img id="userTopCartButton" src="assets/cart.png"></a>--%>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png"></a>
</div>

<h3 class="margin8px"><fmt:message key="my.cruises"/>: ${cruisesUserBuyCount}</h3>


<table id="tableCruises">
    <tr id="tableHeader">
        <th onclick="orderBy('id')"><fmt:message key="id"/></th>
        <th onclick="orderBy('name')"><fmt:message key="ship.name"/></th>
        <th onclick="orderBy('route')"><fmt:message key="route"/></th>
        <th onclick="orderBy('start_date')"><fmt:message key="start.date"/></th>
        <th onclick="orderBy('end_date')"><fmt:message key="end.date"/></th>
        <th onclick="orderBy('price')"><fmt:message key="cruise.price"/></th>
        <th onclick="orderBy('confirmed')"><fmt:message key="confirmed"/></th>
    </tr>
    <c:forEach items="${cruisesUserBuy}" var="i">
        <tr class="rows" onclick="showOrderInfo(${i.orderId})">
            <td>${i.orderId}</td>
            <td>${i.shipName}</td>
            <td>${i.route}</td>
            <td>${i.startCruise}</td>
            <td>${i.endCruise}</td>
            <td>${i.price}</td>
            <td>
                <c:choose>
                    <c:when test="${i.confirmed == true}"><fmt:message key="yes"/></c:when>
                    <c:when test="${i.confirmed == false}"><fmt:message key="no"/></c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>


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

    <button class="buttonSmall"
            onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
            type="button"><fmt:message key="back"/></button>
</div>

<%--limp--%>
<form style="visibility: hidden" action="${pageContext.request.contextPath}/controller?open=CRUISE_DETAILED"
      method="post">
    <input type="hidden" id="hiddenForm" name="orderId">
    <button type="submit" id="hiddenFormSubmit">send</button>
</form>
<%--limp--%>
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
                location.href = '${pageContext.request.contextPath}/controller?open=CART'
            } else {
                console.error(xhr.statusText);
            }
        }
    }


    function showOrderInfo(id) {
        document.getElementById("hiddenForm").value = id
        document.getElementById("hiddenFormSubmit").click()
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

</script>
