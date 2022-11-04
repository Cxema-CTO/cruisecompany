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


<c:choose>
    <c:when test="${cruises==null}">
        <div class="inCenter">
            <div class="container">
                <div class="inCenter"><h3><fmt:message key="empty.list.cruises"/></h3></div>
                <button onclick="location.href='${pageContext.request.contextPath}/controller?open=ADD_CRUISE'"
                        type="button"><fmt:message key="yes"/></button>
                <button onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
                        type="button"><fmt:message key="no"/></button>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h3 class="margin8px"><fmt:message key="cruises"/>: ${cruisesCount}</h3>

        <table id="tableCruises">
            <tr id="tableHeader">
                <th onclick="orderBy('id')"><fmt:message key="id"/></th>
                <th onclick="orderBy('ship_id')"><fmt:message key="ship.id"/></th>
                <th onclick="orderBy('cabins_sold')"><fmt:message key="cabins.sold"/></th>
                <th onclick="orderBy('start_date')"><fmt:message key="start.date"/></th>
                <th onclick="orderBy('end_date')"><fmt:message key="end.date"/></th>
                <th onclick="orderBy('duration')"><fmt:message key="duration"/></th>
                <th onclick="orderBy('route')"><fmt:message key="route"/></th>
                <th onclick="orderBy('price')"><fmt:message key="cruise.price"/></th>
            </tr>
            <c:forEach items="${cruises}" var="i">
                <tr class="rows" onclick="getIDfromTable(${i.id})">
                    <td>${i.id}</td>
                    <td>${i.shipId}</td>
                    <td>${i.cabinsSold}</td>
                    <td>${i.startCruise}</td>
                    <td>${i.endCruise}</td>
                    <td>${i.duration}</td>
                    <td>${i.route}</td>
                    <td>₿${i.cruisePrice}</td>
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
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=ADD_CRUISE'"
                    type="button"><fmt:message key="add"/></button>
            <button class="buttonSmall"
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
                    type="button"><fmt:message key="back"/></button>
        </div>

    </c:otherwise>
</c:choose>


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
                location.href = '${pageContext.request.contextPath}/controller?open=CRUISES'
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



