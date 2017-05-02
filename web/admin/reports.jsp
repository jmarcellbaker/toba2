<!DOCTYPE html>
<html>
    <head>
        <title>TOBA</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="../styles/main.css" type="text/css"/>
    </head>
    <body>
       <header>
            <img src="../images/tobalogo.PNG" alt="TOBA LOGO">
        </header>
        <nav>
            <ul>
                <li><a href="../index.jsp">LOGOUT</a></li>
            </ul>
        </nav>
        <section>
            <h1>Monthly User Report</h1>
            <br>
            <br>
            <table>
                
                <tr>
                    <th>User Name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip Code</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Salt</th>
                    <th>Registration Date</th>
                </tr>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td> ${user.userName} </td>
                    <td> ${user.firstName} </td>
                    <td> ${user.lastName} </td>
                    <td> ${user.phone} </td>
                    <td> ${user.address} </td>
                    <td> ${user.city} </td>
                    <td> ${user.state} </td>
                    <td> ${user.zipCode} </td>
                    <td> ${user.email} </td>
                    <td> ${user.password} </td>
                    <td> ${user.salt} </td> 
                    <td> ${user.registrationDate} </td> 
                </tr>
                </c:forEach>
            </table>
                <br>
                <br>
            <a href="Spreadsheet">Download</a>
        </section>
</body>
</html>
