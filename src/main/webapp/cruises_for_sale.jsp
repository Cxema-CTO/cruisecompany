<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>


<div class="spaceBetween">
    <h3 class="margin8px"><fmt:message key="cruises"/>: ${cruisesForSaleCount}</h3>

    <div class="spaceBetween">
        <h3 class="margin8px"><fmt:message key="filter"/>: </h3>
        <c:if test="${sessionScope.filter == 'on'}">
            <c:if test="${sessionScope.filterFrom != '1977-10-29'}">
                <fmt:message key="from"/> ${sessionScope.filterFrom}
            </c:if>
            <c:if test="${sessionScope.filterTo != '2077-10-29'}">
                <fmt:message key="to"/> ${sessionScope.filterTo}
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.filterDuration == '1'}"><fmt:message key="duration"/> <fmt:message
                        key="filter.to7days"/></c:when>
                <c:when test="${sessionScope.filterDuration == '2'}"><fmt:message key="duration"/> <fmt:message
                        key="filter.7-15days"/></c:when>
                <c:when test="${sessionScope.filterDuration == '3'}"><fmt:message key="duration"/> <fmt:message
                        key="filter.more15days"/></c:when>
                <c:otherwise> </c:otherwise>
            </c:choose>
        </c:if>
        <img onclick="openCruiseFilterModal()" class="filterIcon" src="assets/filter.png" alt="filterIcon">
    </div>

</div>


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
        <th><fmt:message key="cruise.cabins.left"/></th>
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
            <td>
                <div class="inCenter">${i.duration}</div>
            </td>
            <td>${i.route}</td>
            <td>
                <div class="inCenter">${i.cabinsLeft}</div>
            </td>
            <td>
                <div class="inCenter">₿${i.price}</div>
                <c:if test="${user.accountBalance > i.price && i.cabinsLeft > 0}">
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

<div class="hideModal" id="cruiseFilterModal">
    <div class="errorModal">
        <div>
            <h1 class="inCenter textAlignCenter"><fmt:message key="set.filter"/></h1>

            <input type="hidden" name="filter" value="on">
            <div>
                <label for="dateFrom"><fmt:message key="from"/></label><br>
                <input class="inCenter textAlignCenter" id="dateFrom" type="date" name="from" oninput="date()"
                       placeholder=
                       <fmt:message key="from"/>
                       <c:if test="${sessionScope.filterFrom != '1977-10-29'}"> value=${sessionScope.filterFrom}</c:if>
                >
            </div>
            <div>
                <label for="dateTo"><fmt:message key="to"/></label><br>
                <input class="inCenter textAlignCenter" id="dateTo" type="date" name="to" oninput="date()"
                       placeholder=
                       <fmt:message key="to"/>
                       <c:if test="${sessionScope.filterTo != '2077-10-29'}"> value=${sessionScope.filterTo}</c:if>
                >
            </div>
            <div>
                <label for="duration"><fmt:message key="duration"/></label><br>
                <select id="duration" name="duration" required>
                    <option value="0" class="cruiseOption"><fmt:message key="filter.any.days"/></option>
                    <option value="1" class="cruiseOption"
                            <c:if test="${sessionScope.filterDuration == 1}"> selected</c:if>>
                        <fmt:message key="filter.to7days"/></option>
                    <option value="2" class="cruiseOption"
                            <c:if test="${sessionScope.filterDuration == 2}"> selected</c:if>>
                        <fmt:message key="filter.7-15days"/></option>
                    <option value="3" class="cruiseOption"
                            <c:if test="${sessionScope.filterDuration == 3}"> selected</c:if>>
                        <fmt:message key="filter.more15days"/></option>
                </select>
            </div>

            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="setCruiseFilterModal('on')" type="button"><fmt:message
                        key="set"/></button>
                <button class="buttonError buttonSmall" onclick="setCruiseFilterModal('off')" type="button"><fmt:message
                        key="reset"/></button>
                <button class="buttonError buttonSmall" onclick="closeCruiseFilterModal()" type="button"><fmt:message
                        key="close"/></button>
            </div>
        </div>
    </div>
</div>

<%--script in index.jsp--%>