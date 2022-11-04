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
    <c:when test="${ships==null}">
        <div class="inCenter">
            <div class="container">
                <div class="inCenter"><h3><fmt:message key="empty.list.ships"/></h3></div>
                <button onclick="location.href='${pageContext.request.contextPath}/controller?open=ADD_SHIP'"
                        type="button"><fmt:message key="yes"/></button>
                <button onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
                        type="button"><fmt:message key="no"/></button>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h3 class="margin8px"><fmt:message key="ships"/>: ${shipsCount}</h3>

        <table id="tableShips">
            <tr id="tableHeader">
                <th onclick="orderBy('id')"><fmt:message key="id"/></th>
                <th onclick="orderBy('name')"><fmt:message key="ship.name"/></th>
                <th onclick="orderBy('cabins')"><fmt:message key="cabins.quantity"/></th>
                <th onclick="orderBy('staff')"><fmt:message key="staff.quantity"/></th>
                <th onclick="orderBy('description_eng')"><fmt:message key="description.eng"/></th>
                <th onclick="orderBy('description_ukr')"><fmt:message key="description.ukr"/></th>
                <th onclick="orderBy('photo')"><fmt:message key="add.photo"/></th>
            </tr>
            <c:forEach items="${ships}" var="i">
                <tr class="rows" onclick="getIDfromTable(${i.id})">
                    <td>${i.id}</td>
                    <td>${i.shipName}</td>
                    <td>${i.cabins}</td>
                    <td>${i.staff}</td>
                    <td>${i.descriptionEng}</td>
                    <td>${i.descriptionUkr}</td>
                    <td><img class="shipImage" src=${i.photoLink} alt="🚢"></td>
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
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=ADD_SHIP'"
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
                location.href = '${pageContext.request.contextPath}/controller?open=SHIPS'
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

