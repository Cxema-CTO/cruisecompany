<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mtg" uri="myTag" %>


<div class="limp"></div>
<div class="footer">
    <div id="webmaster"><mtg:myTag/></div>
</div>


<style>
    .limp {
        height: 32px;
    }

    .footer {
        width: 100%;
        max-width: 1280px;
        margin-top: 12px;
        display: inline-flex;
        align-items: center;
        justify-content: end;
        background: url("${pageContext.request.contextPath}/assets/backgroundSmall.png");
        color: white;
        text-align: center;
        font-size: small;
        position: fixed;
        bottom: 0;
    }

    #webmaster {
        padding: 8px;
    }


</style>

