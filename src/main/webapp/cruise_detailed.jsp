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

<div class="inCenter">
    <div class="container containerWidth1024">
        <div class="spaceAround">
            <div>
                <h3 class="inCenter"><fmt:message key="order"/> ${cruiseDetailed.orderId}</h3>
            </div>
            <div>
                <h3 class="inCenter">${cruiseDetailed.shipName}</h3>
            </div>
        </div>

        <div class="spaceAround">
            <div>
                <fmt:message key="route"/>: ${cruiseDetailed.route}
            </div>
            <div>
                <fmt:message key="from"/> ${cruiseDetailed.startCruise} <fmt:message key="to"/> ${cruiseDetailed.endCruise}
            </div>
            <div>
                <fmt:message key="cruise.price"/>: ₿${cruiseDetailed.price}
            </div>
        </div>
        <br>
        <div class="inCenter">
            <fmt:message key="description"/>:
            <c:choose>
                <c:when test="${sessionScope.language == 'ukr'}">${cruiseDetailed.descriptionUkr}</c:when>
                <c:otherwise>${cruiseDetailed.descriptionEng}</c:otherwise>
            </c:choose>
        </div>
        <br>
        <div class="inCenter">
            <img src="${cruiseDetailed.photoLink}" class="shipImageCart">
        </div>
    </div>
</div>

<div class="inCenter">

    <button class="buttonSmall"
            onclick="location.href='${pageContext.request.contextPath}/controller?open=CART'"
            type="button"><fmt:message key="back"/></button>
</div>

</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>


<script defer type="text/javascript">
    function getIDfromTable(id) {
        // document.getElementById("shipId").value = id
        // console.log("Order Id: " + id)
    }
</script>

</html>