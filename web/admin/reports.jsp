<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
               <!-- <th>Password</th> 
                    <th>Salt</th> -->
                    <th>Registration Date</th>
                </tr>
                <sql:query var="rs" dataSource="jdbc/toba">
                    select * 
                    from user 
                    where year(registrationdate) = year(current_date()) 
                    and month(registrationdate) = month(current_date()) 
                </sql:query>
                    
                <c:forEach var="row" items="${rs.rows}">
                <tr>
                    <td> ${row.userName} </td>
                    <td> ${row.firstName} </td>
                    <td> ${row.lastName} </td>
                    <td> ${row.phone} </td>
                    <td> ${row.address} </td>
                    <td> ${row.city} </td>
                    <td> ${row.state} </td>
                    <td> ${row.zipCode} </td>
                    <td> ${row.email} </td>
                <!--<td> ${row.password} </td>
                    <td> ${row.salt} </td> -->
                    <td> ${row.registrationDate} </td> 
                </tr>
                </c:forEach>
            </table>
                <br>
                <br>
            <a href="../Spreadsheet">Download</a>
        </section>
</body>
</html>