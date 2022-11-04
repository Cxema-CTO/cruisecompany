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
<div class="userTop">
    <img class="userIcon" src="assets/user.png">
    <div id="userNameTop" class="userTopLine">${sessionScope.user.userName}</div>
    <div id="userBalanceTop" class="userTopLine">₿${sessionScope.user.accountBalance}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png"></a>
</div>

<div class="inCenter">
    <div class="container containerWidth480">
        <div id="loginFormLabel">
            <%--        <fmt:message key="title.login"/></div>--%>
            <h3>Вітаємо з покупкою круїзу!!!</h3></div>
        <div class="inCenter">
            <button class="buttonSmall"
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
                    type="button"><fmt:message key="back"/></button>
        </div>
    </div>
</div>

</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>

</html>


<%--script--%>
<script defer type="text/javascript">

    let nameUser = ""
    let ID = 0
    let role = ""
    let banned
    let balance

    function getIDfromTable(id) {
        console.log(id)
    }

    function openBalanceModal(name) {
        nameUser = name
        document.getElementById("balanceModal").style.visibility = "visible"
        document.getElementById("nameBalance").innerText = name
        document.getElementById("money").focus();
    }


    function openBanModal(name, id, isAdmin, isBanned) {
        nameUser = name
        ID = id;
        banned = isBanned
        if (isAdmin) {
            role = "Admin"
            document.getElementById("adminModal").style.visibility = "visible"
        } else {
            role = "User"
            if (isBanned) {
                document.getElementById("unbanName").innerText = name + "?!"
                document.getElementById("unbanModal").style.visibility = "visible"
            } else {
                document.getElementById("banName").innerText = name + "?!"
                document.getElementById("banModal").style.visibility = "visible"
            }
        }
    }


    function closeBalanceModal() {
        let bannedModal = document.getElementById("balanceModal")
        bannedModal.style.visibility = "hidden"
    }

    let xhr = new XMLHttpRequest();
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                location.href = '${pageContext.request.contextPath}/controller?open=USERS'
            } else {
                console.error(xhr.statusText);
            }
        }
    }

    function setMoney() {
        let howMuch = document.getElementById("money").value
        xhr.open("POST", "SET_MONEY?username=" + nameUser + "&howmuch=" + howMuch, true);
        xhr.send();
        closeBalanceModal()
    }
</script>