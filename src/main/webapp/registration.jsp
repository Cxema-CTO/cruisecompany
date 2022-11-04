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

<form action="${pageContext.request.contextPath}/controller?open=REGISTRATION_NEW_USER" method="post">
    <div class="container" id="registration_form">
        <div id="loginFormLabel"><fmt:message key="title.registration"/></div>
        <input id="username" type="text" minlength="3" maxlength="30" placeholder=
        <fmt:message key="username"/> name="username" required>
        <input id="password" type="password" minlength="4" maxlength="30" placeholder=
        <fmt:message key="password"/> name="password" required>
        <input id="confirm_password" type="password" maxlength="30" placeholder=
        <fmt:message key="confirm_password"/> name="confirm_password" required>
        <input type="checkbox" name="is_admin"> <fmt:message key="admin"/>
        <%--                <img  class="viewPassword" src="assets/view.png"></img>--%>
        <button type="submit"><fmt:message key="submit"/></button>
        <button onclick="history.back()" type="button"><fmt:message key="back"/></button>

    </div>
</form>

</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>


<script defer type="text/javascript">

    let password = document.getElementById('password');
    let confirmPass = document.getElementById('confirm_password');
    confirmPass.addEventListener("focus", confirmPasswordFocus);
    confirmPass.addEventListener("blur", confirmPasswordBlur);

    function confirmPasswordFocus() {
        confirmPass.addEventListener("keyup", confirmPassword);
    }

    function confirmPasswordBlur() {
        confirmPass.removeEventListener("keyup", confirmPassword);
        confirmPass.style.backgroundColor = "white";
    }

    function confirmPassword() {
        if (password.value !== confirmPass.value && confirmPass.value !== "") {
            confirmPass.style.backgroundColor = "red";
        } else {
            confirmPass.style.backgroundColor = "white";
        }
    }

    let usernameInput = document.getElementById('username');
    usernameInput.addEventListener("blur", leaveName);
    usernameInput.addEventListener("focus", enterName);

    let sendUserName = document.getElementById('username').value;
    // let url = "does_user_name_in_db?username=";
    let url = "CHECK_USER_IN_DB?username=";
    let xhr = new XMLHttpRequest();
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                if (xhr.responseText === "true") usernameInput.style.backgroundColor = "red";
            } else {
                console.error(xhr.statusText);
            }
        }
    };
    xhr.onerror = function (e) {
        console.error(xhr.statusText);
    };

    function enterName() {
        usernameInput.style.backgroundColor = "white";
    }

    function leaveName() {
        sendUserName = document.getElementById('username').value;
        console.log(sendUserName);
        xhr.open("POST", url + sendUserName, true);
        xhr.send();
    }


</script>

</html>