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
    <img class="userIcon" src="assets/admin.png" alt="user">
    <div class="userTopLine">${sessionScope.user.userName}</div>
    <a class="userTop" href="${pageContext.request.contextPath}/controller?open=EXIT">
        <img id="userTopCloseButton" src="assets/exit.png" alt="exit"></a>
</div>

<h3 class="margin8px"><fmt:message key="users"/>: ${usersCount}</h3>

<table id="tableUsers">
    <tr id="tableHeader">
        <th onclick="orderBy('id')"><fmt:message key="id"/></th>
        <th onclick="orderBy('user_name')"><fmt:message key="user"/></th>
        <th onclick="orderBy('password')"><fmt:message key="password"/></th>
        <th onclick="orderBy('is_admin')"><fmt:message key="role"/></th>
        <th onclick="orderBy('is_banned')"><fmt:message key="status"/></th>
        <th onclick="orderBy('balance')"><fmt:message key="balance"/></th>
        <th><fmt:message key="add.photo"/></th>
    </tr>
    <c:forEach items="${users}" var="i">
        <tr class="rows" onclick="getIDfromTable(${i.id})">
            <td>${i.id}</td>
            <td>${i.userName}</td>
            <td>${i.password}</td>
                <%--            https://stackoverflow.com/questions/6854866/how-to-get-boolean-property-with-expression-language--%>
                <%--            <td>${i.isAdmin()}</td>--%>
                <%--            <td>${i.isBanned()}</td>--%>
            <td>
                <c:choose>
                    <c:when test="${i.admin == true}"><fmt:message key="admin"/></c:when>
                    <c:when test="${i.admin == false}"><fmt:message key="user"/></c:when>
                </c:choose>
            </td>
            <td onclick="openBanModal('${i.userName}',${i.id},${i.admin},${i.banned})">
                <div class="tableButton inCenter">
                    <c:choose>
                        <c:when test="${i.banned == true}"><fmt:message key="banned.table"/></c:when>
                        <c:when test="${i.banned == false}"><fmt:message key="unbanned.table"/></c:when>
                    </c:choose>
                </div>
            </td>
            <td onclick="openBalanceModal('${i.userName}')">
                <div class="tableButton inCenter">
                    ₿${i.accountBalance}
                </div>
            </td>
            <td>
                <div class="inCenter"><img src="show?id=${i.id}" class="inCenter imgPassportPhoto"
                                           onerror="this.onerror = null; this.src = '${pageContext.request.contextPath}/assets/noPhoto.png'">
                </div>
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

<div class="inCenter">
    <button class="buttonSmall" onclick="location.href='${pageContext.request.contextPath}/controller?open=INDEX'"
            type="button"><fmt:message key="back"/></button>
</div>


<%--modal--%>
<div class="hideModal" id="banModal">
    <div class="errorModal">
        <div>
            <h1 class="inCenter textAlignCenter"><fmt:message key="user.ban"/></h1>
            <h1 class="inCenter textAlignCenter" id="banName"></h1>
            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="banUser()" type="button"><fmt:message
                        key="yes"/></button>
                <button class="buttonError buttonSmall" onclick="closeBanModal()" type="button"><fmt:message
                        key="no"/></button>
            </div>
        </div>
    </div>
</div>

<div class="hideModal" id="unbanModal">
    <div class="errorModal">
        <div>
            <h1 class="inCenter textAlignCenter"><fmt:message key="user.unban"/></h1>
            <h1 class="inCenter textAlignCenter" id="unbanName"></h1>
            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="unbanUser()" type="button"><fmt:message
                        key="yes"/></button>
                <button class="buttonError buttonSmall" onclick="closeUnbanModal()" type="button"><fmt:message
                        key="no"/></button>
            </div>
        </div>
    </div>
</div>

<div class="hideModal" id="adminModal">
    <div class="errorModal">
        <div>
            <h1 class="inCenter textAlignCenter"><fmt:message key="unable.to.ban"/>!</h1>
            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="closeAdminModal()" type="button"><fmt:message
                        key="ok"/></button>
            </div>
        </div>
    </div>
</div>

<div class="hideModal" id="balanceModal">
    <div class="errorModal">
        <div>
            <h1 class="inCenter textAlignCenter"><fmt:message key="set.account.balance"/></h1>
            <h1 class="inCenter textAlignCenter" id="nameBalance"></h1>
            <div class="inCenter">
                <input class="inCenter textAlignCenter" id="money" type="number" min="100" max="100000" placeholder=
                <fmt:message key="between100and100000"/>>
            </div>
            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="setMoney()" type="button"><fmt:message
                        key="set"/></button>
                <button class="buttonError buttonSmall" onclick="closeBalanceModal()" type="button"><fmt:message
                        key="close"/></button>
            </div>
        </div>
    </div>
</div>
<%--end body--%>
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

    function closeBanModal() {
        let bannedModal = document.getElementById("banModal")
        bannedModal.style.visibility = "hidden"
    }

    function closeUnbanModal() {
        let bannedModal = document.getElementById("unbanModal")
        bannedModal.style.visibility = "hidden"
    }

    function closeAdminModal() {
        let bannedModal = document.getElementById("adminModal")
        bannedModal.style.visibility = "hidden"
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

    function banUser() {
        xhr.open("POST", "BAN_USER_IN_DB?username=" + nameUser, true);
        xhr.send();
    }

    function unbanUser() {
        xhr.open("POST", "UNBAN_USER_IN_DB?username=" + nameUser, true);
        xhr.send();
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
</script>