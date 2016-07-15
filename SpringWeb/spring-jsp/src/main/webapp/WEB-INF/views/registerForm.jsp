<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:message code="page.register"/></title>
    <style>
        .error {
            color: red;
        }

        label.error > input {
            background-color: #e99;
        }
    </style>
</head>
<body>

<s:url value="/spittles" var="spittlesJSUrl" javaScriptEscape="true">
    <s:param name="max" value="60" />
    <s:param name="count" value="20" />
</s:url>
<script>
    var spittlesUrl = "${spittlesJSUrl}"
</script>
<script>
    var spittlesUrl = "\/spitter\/spittles?max=60&count=20"
</script>

<sf:form method="POST" commandName="spitter" action="register">
    <sf:errors path="*" element="div" cssClass="error"/>
    First Name:
    <sf:label path="firstName" cssErrorClass="error">
        <sf:input path="firstName"/>
    </sf:label>
    <sf:errors path="firstName" cssClass="error"/>
    <br/>
    Last Name:
    <sf:label path="lastName" cssErrorClass="error">
        <sf:input path="lastName"/>
    </sf:label>
    <sf:errors path="lastName" cssClass="error"/>
    <br/>
    Email: <sf:input path="email"/>
    <sf:errors path="email" cssClass="error"/>
    <br/>
    Username: <sf:input path="username"/>
    <sf:errors path="username" cssClass="error"/>
    <br/>
    Password: <sf:password path="password"/>
    <sf:errors path="password" cssClass="error"/>
    <br/>
    <input type="submit" value="Register"/>
</sf:form>

</body>
</html>
