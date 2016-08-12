<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="<s:url value="/resources/style.css" />">
</head>
<body>

<form method="post" enctype="multipart/form-data" action="/registration">
    <label>Picture:
        <input name="picture" type="file" accept="image/jpeg,image/png,image/gif">
    </label>
    <button type="submit">Submit</button>
</form>

</body>
</html>
