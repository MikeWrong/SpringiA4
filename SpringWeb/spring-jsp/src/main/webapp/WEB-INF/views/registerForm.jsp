<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<sf:form method="POST" commandName="spitter" action="register">
    First Name: <sf:input path="firstName"/>
    <sf:errors path="firstName"/>
    <br/>
    Last Name: <sf:input path="lastName"/>
    <sf:errors path="lastName"/>
    <br/>
    Email: <sf:input path="email"/>
    <sf:errors path="email"/>
    <br/>
    Username: <sf:input path="username"/>
    <sf:errors path="username"/>
    <br/>
    Password: <sf:password path="password"/>
    <sf:errors path="password"/>
    <br/>
    <input type="submit" value="Register"/>
</sf:form>

</body>
</html>
