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


    // filter
    function openCruiseFilterModal() {
        let bannedModal = document.getElementById("cruiseFilterModal")
        bannedModal.style.visibility = "visible"
    }

    function closeCruiseFilterModal() {
        let bannedModal = document.getElementById("cruiseFilterModal")
        bannedModal.style.visibility = "hidden"
    }

    function setCruiseFilterModal(onOff) {
        let from = document.getElementById("dateFrom").value
        let to = document.getElementById("dateTo").value
        let duration = document.getElementById("duration").value

        if (onOff === "on") {
            if (from === "") from = ("1977-10-29")
            if (to === "") to = ("2077-10-29")
            if (duration == null) duration = 0
            xhr.open("POST", "PAGINATION?filter=on&from=" + from + "&to=" + to + "&duration=" + duration, true);
            xhr.send();
        }
        if (onOff === "off") {
            from = ("1977-10-29")
            to = ("2077-10-29")
            duration = 0
            xhr.open("POST", "PAGINATION?filter=on&from=" + from + "&to=" + to + "&duration=" + duration, true);
            xhr.send();
        }
    }

    function date() {
        let from = document.getElementById("dateFrom").value
        let to = document.getElementById("dateTo").value
        if (from !== "" && to !== "") {
            if (from.valueOf() > to.valueOf()) {
                document.getElementById("dateFrom").style.backgroundColor = "red"
                document.getElementById("dateTo").style.backgroundColor = "red"
            } else {
                document.getElementById("dateFrom").style.backgroundColor = "white"
                document.getElementById("dateTo").style.backgroundColor = "white"
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

    function buyCruise(id) {
        console.log("buy cruise Id:" + id)
    }
</script>