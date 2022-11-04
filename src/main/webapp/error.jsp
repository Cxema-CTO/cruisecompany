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
<div class="errorModal">
    <div>
        <h1 class="inCenter"><fmt:message key="error"/></h1>
        <h3 class="inCenter"><fmt:message key="${sessionScope.error_message}"/></h3>
        <button class="buttonError" onclick="history.back()" type="button"><fmt:message key="back"/></button>
    </div>
</div>
</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>