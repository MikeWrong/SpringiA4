<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/resources/style.css" />">
</head>
<body>

<s:url value="registration" var="registration"/>

<sf:form method="post" commandName="spitter" enctype="multipart/form-data" action="${registration}">
    <label>Picture:
        <input name="picture" type="file" accept="image/jpeg,image/png,image/gif">
    </label>
    <br/>
    <label>Name:
        <input name="name" type="text">
    </label>
    <br/>
    <label>Birthday:
        <input name="birthday" type="text">
    </label>
    <br/>
    <label>Redirect
        <select name="redirect">
            <option value="1" selected>General</option>
            <option value="2">Flash</option>
        </select>
    </label>
    <br/>
    <button type="submit">Submit</button>
    <br/>
    <br/>
    <sf:errors path="*"/>
</sf:form>

</body>
</html>
