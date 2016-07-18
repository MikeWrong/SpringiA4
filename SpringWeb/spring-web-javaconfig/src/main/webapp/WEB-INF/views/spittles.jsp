<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spittles</title>
</head>
<body>

<c:forEach items="${spittleList}" var="spittle">
    <li id="spittle_<c:out value="spittle.id"/>">
        <div class="spittleMessage">
            <c:out value="${spittle.message}"/>
        </div>
        <div>
            <fmt:formatDate value="${spittle.time}" pattern="yyyy-MM-dd HH:mm:ss.sss" var="time"/>
            <span class="spittleTime"><c:out value="${time}"/></span>
            <span class="spittleLocation">(
                <c:out value="${spittle.latitude}"/>,
                <c:out value="${spittle.longitude}"/>
                )</span>
        </div>
    </li>
</c:forEach>

</body>
</html>
