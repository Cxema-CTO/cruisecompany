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
<c:if test="${sessionScope.role == 'guest' || sessionScope.role == null}">
    <jsp:include page="guest.jsp"/>
    <jsp:include page="cruises_for_sale.jsp"/>
</c:if>

<c:if test="${sessionScope.role == 'user'}">
    <jsp:include page="user.jsp"/>
    <jsp:include page="cruises_for_sale.jsp"/>
</c:if>

<c:if test="${sessionScope.role == 'admin'}">
    <jsp:include page="admin.jsp"/>
    <jsp:include page="cruises_for_sale.jsp"/>
</c:if>

<c:if test="${sessionScope.role == 'banned'}">
    <jsp:include page="user_banned.jsp"/>
</c:if>




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
                location.href = '${pageContext.request.contextPath}/controller?open=INDEX'
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

    function buyCruise(id){
        console.log("buy cruise Id:"+id)
    }
</script>