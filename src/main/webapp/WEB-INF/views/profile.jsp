<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" type="text/css" href="../../../../../../Library_managment_system-jsp-test/src/main/webapp/web-resources/libstyle.css">
</head>
<body>
<h1>Your Profile</h1>
<c:out value="${user.username}" /><br/>
<c:out value="${user.firstName}" /> <c:out value="${user.lastName}" /><br/>
<c:out value="${user.email}" />
</body>
</html>