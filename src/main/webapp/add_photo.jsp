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

<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <div class="container" id="registration_form">
        <div id="loginFormLabel"><fmt:message key="add.photo"/></div>

        <div>
            <input id="photo" type="file" name="photo_passport" style="padding: 0" required />
            <label for="photo"><fmt:message key="photo.passport"/></label>
        </div>
        <br>

        <button type="submit"><fmt:message key="submit"/></button>
        <button onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
                type="button"><fmt:message key="skip"/></button>
    </div>
</form>

</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>


<%--<script defer type="text/javascript">--%>

<%--      let sendUserName = document.getElementById('username').value;--%>
<%--    // let url = "does_user_name_in_db?username=";--%>
<%--    let url = "CHECK_USER_IN_DB?username=";--%>
<%--    let xhr = new XMLHttpRequest();--%>
<%--    xhr.onload = function (e) {--%>
<%--        if (xhr.readyState === 4) {--%>
<%--            if (xhr.status === 200) {--%>

<%--            } else {--%>

<%--            }--%>
<%--        }--%>
<%--    };--%>
<%--    xhr.onerror = function (e) {--%>
<%--        console.error(xhr.statusText);--%>
<%--    };--%>


<%--</script>--%>

</html>