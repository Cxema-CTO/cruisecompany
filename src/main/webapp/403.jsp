<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
        <h3 class="inCenter"><fmt:message key="error.access_denied"/></h3>
        <div class="inCenter">
<%--            url("${pageContext.request.contextPath}/assets/backgroundSmall.png");--%>
            <img class="errorImage" src="${pageContext.request.contextPath}/assets/accessDenied.jpg" height="128px" width="128px">
        </div>
        <button class="buttonError" onclick="history.back()" type="button"><fmt:message key="back"/></button>
    </div>
</div>
</body>

<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>
