<%-- 
    Document   : hello
    Created on : Mar 29, 2018, 6:14:10 PM
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
        <c:if test="${!empty User}">


            <form action="SignOut.htm" method="GET">
                <input type="submit" value="Sign Out"/>
            </form>

            <h1>Hello Admin  ${User.userName}</h1>
            <br>
            <br>
            

            <form action="pending.htm" method="GET">
                <input type="submit" value="Pending Users"/>
                <p style="color: red">${noUsers} </p>
            </form>

            <br>
            <br>
            
            
            
            <form action="DeniedUsers.htm" method="GET">
                <input type="submit" value="Denied Users"/>
                <p style="color: red">${noUsers} </p>
            </form>

        </c:if>
            
    </body>
</html>
