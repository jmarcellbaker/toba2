<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />
        <header>
            <img src="images/tobalogo.PNG" alt="TOBA LOGO">
        </header>
        <nav>
            <ul>
                <li><a href="index.jsp">HOME</a></li>
                <li><a href="login.jsp">LOGIN</a></li>
                <li><a href="new_customer.jsp">NEW CUSTOMER</a></li>
            </ul>
        </nav>
        <section>
            <p class="error">${message}</p>
                <form action="newCustomer" method="POST">
                <h3>New Customer Sign Up</h3><br>
                <label>First Name:</label><br>
                <input type="text" name="firstName" value="${firstName}"><br>
                <label>Last Name:</label><br>
                <input type="text" name="lastName" value="${lastName}"><br>
                <label>Phone:</label><br>
                <input type="text" name="phone" value="${phone}"><br>
                <label>Address:</label><br>
                <input type="text" name="address" value="${address}"><br>
                <label>City:</label><br>
                <input type="text" name="city" value="${city}"><br>
                <label>State:</label><br>
                <input type="text" name="state" value="${state}"><br>
                <label>Zip Code:</label><br>
                <input type="text" name="zipCode" value="${zipCode}"><br>
                <label>Email:</label><br>
                <input type="email" name="email" value="${email}"><br>
                <input type="submit" value="Register">
            </form>
        </section>
                <c:import url="/includes/footer.jsp" />