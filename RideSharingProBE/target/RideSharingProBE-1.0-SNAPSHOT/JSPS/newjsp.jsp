<%-- 
    Document   : newjsp
    Created on : Apr 28, 2018, 5:22:01 PM
    Author     : TECHNOLOGY CITY
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <h1>Login Model</h1>
        <br>
        <br>
        <h1>${error}</h1>
        <br>
        <br>
        <form:form method="post" commandName="user"  >
                   <!--action="j_spring_security_check">-->
            UserName<br>
            <form:input path="userName"/>
            <form:errors path="userName"/>
            <br>
            <br>
            Password
            <br>
            <form:password path="password"  />
            <form:errors path="password"/>
            <br>
            <br>
            <input type="submit" value="Login"  />
            <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        </form:form>
    </body>
</html>
