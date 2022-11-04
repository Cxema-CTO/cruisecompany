<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<h3 class="margin8px"><fmt:message key="cruises"/>: ${cruisesForSaleCount}</h3>

<table id="tableCruises">
    <tr id="tableHeader">
        <%--        <th onclick="orderBy('id')"><fmt:message key="id"/></th>--%>
        <th><fmt:message key="add.photo"/></th>
        <th onclick="orderBy('name')"><fmt:message key="ship.name"/></th>
        <th><fmt:message key="description"/></th>
        <th onclick="orderBy('start_date')"><fmt:message key="start.date"/></th>
        <th onclick="orderBy('end_date')"><fmt:message key="end.date"/></th>
        <th onclick="orderBy('duration')"><fmt:message key="duration"/></th>
        <th onclick="orderBy('route')"><fmt:message key="route"/></th>
        <th onclick="orderBy('price')"><fmt:message key="cruise.price"/></th>
    </tr>
    <c:forEach items="${cruisesForSale}" var="i">
        <tr class="rows" onclick="getIDfromTable(${i.id})">
                <%--            <td>${i.id}</td>--%>
            <td><img class="shipImage" src=${i.photoLink} alt="🚢"></td>
            <td>${i.shipName}</td>
            <td>
                <c:choose>
                    <c:when test="${sessionScope.language == 'ukr'}">${i.descriptionUkr}</c:when>
                    <c:otherwise>${i.descriptionEng}</c:otherwise>
                </c:choose>
            </td>
            <td>${i.startCruise}</td>
            <td>${i.endCruise}</td>
            <td>${i.duration}</td>
            <td>${i.route}</td>
            <td>
                <div class="inCenter">₿${i.price}</div>
                <c:if test="${user.accountBalance > i.price}">
                    <c:if test="${sessionScope.role == 'user'}">
                        <form action="${pageContext.request.contextPath}/controller?open=BUY_CRUISE" method="post"
                              accept-charset="UTF-8">
                            <input type="hidden" value="${i.id}" name="cruiseId">
                            <input type="hidden" value="${user.userName}" name="userName">
                            <button class="tableButton inCenter" type="submit"><fmt:message key="cruise.buy"/></button>
                        </form>
                    </c:if>
                </c:if>
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


<%--script in index.jsp--%>