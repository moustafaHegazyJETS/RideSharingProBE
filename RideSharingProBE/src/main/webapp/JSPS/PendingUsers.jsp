<%-- 
    Document   : PendingUsers
    Created on : Apr 28, 2018, 6:33:13 PM
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

            <br>
            <form action="success" method="GET">
                <input type="submit" value="Back"/>
            </form>
        

        <c:forEach var="i" begin="0" end="${users.size()-1}">

            <div>
                USerName IS : ${users[i].userName}
                <br>
                <br>
                
                
                <form action="Accept.htm" method="GET">
                    <input type="hidden" name="id" value="${users[i].idUser}"/>
                    <input type="submit" value="Accept request"/>
                </form>
                
                <form action="Deny.htm" method="GET">
                    <input type="hidden" name="id" value="${users[i].idUser}"/>
                    <input type="submit" value="Deny request"/>
                </form>


            </div>



        </c:forEach>

            </c:if>
        <!--        <form action="login.htm" method="GET">
                    <input type="submit" value="SignIn"/>
                </form>-->

    </body>
</html>
