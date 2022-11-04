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

<form action="${pageContext.request.contextPath}/controller?open=ADD_NEW_SHIP" method="post" accept-charset="UTF-8">
    <div class="container containerWidth720" id="registration_form">
        <div id="loginFormLabel"><fmt:message key="add.ship"/></div>
        <label for="shipNameInput"><fmt:message key="ship.name"/></label>
        <input id="shipNameInput" type="text" onblur="leaveName()" onfocus="enterName()" minlength="3" maxlength="50"
               name="shipname" required>
        <div class="spaceBetween">
            <div class="spaceBetween">
                <div>
                    <label for="cabins"><fmt:message key="cabins.quantity"/></label><br>
                    <input class="inputSmall" id="cabins" type="number" min="10" max="1000" name="cabins" required>
                </div>
                <div class="marginLeft8px">
                    <label for="staff"><fmt:message key="staff.quantity"/></label><br>
                    <input class="inputSmall" id="staff" type="number" min="10" max="1000" name="staff" required>
                </div>
            </div>
            <div class="width100percent marginLeft8px">
                <label for="linkPhoto"><fmt:message key="add.photo"/></label><br>
                <%--            <button onclick="openAddPhotoModal()" class="buttonMiddle" id="inputPhoto" type="button"><fmt:message key="add"/></button>--%>
                <input id="linkPhoto" type="text" name="photoLink" minlength="3" required>
            </div>
        </div>
        <label for="cabins"><fmt:message key="description.eng"/></label>
        <textarea id="descriptionEng" name="descriptionEng" minlength="10" maxlength="999" required></textarea><br>
        <label for="cabins"><fmt:message key="description.ukr"/></label>
        <textarea id="descriptionUkr" name="descriptionUkr" minlength="10" maxlength="999" required></textarea><br>
        <div class="inCenter">
            <button class="buttonSmall" type="submit"><fmt:message key="submit"/></button>
            <button class="buttonSmall"
                    onclick="location.href='${pageContext.request.contextPath}/controller?open=SHIPS'" type="button">
                <fmt:message key="back"/></button>
        </div>
    </div>
</form>


<div class="hideModal" id="addPhotoModal">
    <div class="errorModal">
        <div class="containerWidth480">
            <h1 class="inCenter textAlignCenter"><fmt:message key="add.image.link"/></h1>
            <input id="linkInput" type="text" name="link" required>
            <div class="inCenter">
                <button class="buttonError buttonSmall" onclick="closeAddPhotoModalAndAddLink()" type="button">
                    <fmt:message
                            key="ok"/></button>
                <button class="buttonError buttonSmall" onclick="closeAddPhotoModal()" type="button"><fmt:message
                        key="close"/></button>
            </div>
        </div>
    </div>
</div>

</body>


<footer>
    <jsp:include page="footer.jsp"/>
</footer>


<script defer type="text/javascript">
    let shipNameInput = document.getElementById('shipNameInput');
    let sendShipName = document.getElementById('shipNameInput').value;
    let url = "CHECK_SHIP_IN_DB?shipname=";

    let xhr = new XMLHttpRequest();
    xhr.onload = function (e) {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.responseText);
                if (xhr.responseText === "true") shipNameInput.style.backgroundColor = "red";
            } else {
                console.error(xhr.statusText);
            }
        }
    };
    xhr.onerror = function (e) {
        console.error(xhr.statusText);
    };

    function enterName() {
        shipNameInput.style.backgroundColor = "white";
    }

    function leaveName() {
        sendShipName = document.getElementById('shipNameInput').value;
        console.log(sendShipName);
        xhr.open("POST", url + sendShipName, true);
        xhr.send();
    }


    function openAddPhotoModal() {
        let addPhotoModal = document.getElementById("addPhotoModal")
        addPhotoModal.style.visibility = "visible"
    }

    function closeAddPhotoModalAndAddLink() {
        let addPhotoModal = document.getElementById("addPhotoModal")
        let addLinkInput = document.getElementById("linkPhoto")
        let fromLinkInput = document.getElementById("linkInput")
        addLinkInput.value = fromLinkInput.value
        addPhotoModal.style.visibility = "hidden"
    }

    function closeAddPhotoModal() {
        let addPhotoModal = document.getElementById("addPhotoModal")
        addPhotoModal.style.visibility = "hidden"
    }

</script>

</html>