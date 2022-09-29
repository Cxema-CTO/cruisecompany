<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>

<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles.css">

<head>
    <jsp:include page="header.jsp"/>
</head>

<%--   бікоз - вибір мови у хедері   --%>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>


<body>
<c:if test="${sessionScope.role == 'guest' || sessionScope.role == null}">
<%--    <jsp:include page="login.jsp"/>--%>
</c:if>
<c:if test="${sessionScope.role == 'user'}">
<%--    <jsp:include page="user.jsp"/>--%>
</c:if>
<c:if test="${sessionScope.role == 'inspector'}">
<%--    <jsp:include page="inspector.jsp"/>--%>
</c:if>
</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>