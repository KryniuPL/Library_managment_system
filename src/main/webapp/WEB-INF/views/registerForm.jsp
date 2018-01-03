<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <title>Library Managment System</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/home/kryniu/Desktop/library_application/src/main/webapp/web-resources/libstyle.css" />" >
</head>
<body>
<h1>Register</h1>

<%--@elvariable id="user" type="text"--%>
<sf:form method="POST" commandName="user" enctype="multipart/form-data">
    <sf:errors path="*" element="div" cssClass="errors" />

    <sf:label path="firstName"
              cssErrorClass="error">First Name</sf:label>:
    <sf:input path="firstName" cssErrorClass="error" /><br/>

    <sf:label path="lastName"
              cssErrorClass="error">Last Name</sf:label>:
    <sf:input path="lastName" cssErrorClass="error" /><br/>

    <sf:label path="email"
              cssErrorClass="error">Email</sf:label>:
    <sf:input path="email" cssErrorClass="error" /><br/>

    <sf:label path="username"
              cssErrorClass="error">Username</sf:label>:
    <sf:input path="username" cssErrorClass="error" /><br/>

    <sf:label path="password"
              cssErrorClass="error">Password</sf:label>:
    <sf:password path="password" cssErrorClass="error" /><br/>
    <h3>Photo Upload:</h3>
    Select a photo to upload: <br />
    <form action="UploadImage" method="post" enctype="multipart/form-data">
        <input type="file" name="UploadImage" accept="image/jpeg,image/png,image/gif" size="50" />
        <br />
        <input type="submit" value="Upload Photo" />
    </form>

    <%--
    <sf:label path="profilePicture" cssErrorClass="error">Profile Picture</sf:label>:
    <sf:input path="profilePicture" type="file" accept="image/jpeg.image/png/image/gif"/><br/>
    --%>
    <input type="submit" value="Register" />
</sf:form>
</body>
</html>