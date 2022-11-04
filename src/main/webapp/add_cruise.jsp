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

<form action="${pageContext.request.contextPath}/controller?open=ADD_NEW_CRUISE" method="post" accept-charset="UTF-8">
    <div class="container containerWidth720" id="registration_form">
        <div id="loginFormLabel"><fmt:message key="add.cruise"/></div>
        <div>
            <label for="selectShip"><fmt:message key="select.ship"/></label><br>
            <select id="selectShip" name="shipId" required>
                <c:forEach items="${ships}" var="i">
                    <option value="${i.id}" class="cruiseOption">
                        <b>${i.shipName},</b> <fmt:message key="cabins.quantity"/>: ${i.cabins}, <fmt:message
                            key="staff.quantity"/>: ${i.staff}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="spaceBetween">
            <div>
                <label for="startDate"><fmt:message key="start.date"/></label><br>
                <input class="inputMiddle" id="startDate" type="date" name="startDate" required>
            </div>
            <div>
                <label for="endDate"><fmt:message key="end.date"/></label><br>
                <input class="inputMiddle" id="endDate" type="date" name="endDate" required>
            </div>
            <div>
                <label for="price"><fmt:message key="cruise.price"/></label><br>
                <input class="inputMiddle" id="price" type="number" name="price" min="100" max="50000" required>
            </div>
        </div>
        <label for="route"><fmt:message key="route"/></label>
        <textarea id="route" name="route" minlength="10" maxlength="999" required></textarea><br>

        <div class="inCenter">
            <button class="buttonSmall" type="submit"><fmt:message key="submit"/></button>
            <button class="buttonSmall"
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=CRUISES'" type="button">
                <fmt:message key="back"/></button>
        </div>
    </div>
</form>
</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>


<script defer type="text/javascript">
    function getIDfromTable(id) {
        document.getElementById("shipId").value = id
        console.log("ShipID+" + id)
    }
</script>

</html>