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
            <h1>Welcome to Titan Bank! Your account was successfully created.</h1>
            <br>
            <div class="center">
            <label class="successLabel">First Name:</label>
            <span>${user.firstName}</span><br>
            <label class="successLabel">Last Name:</label>
            <span>${user.lastName}</span><br>
            <label class="successLabel">Phone:</label>
            <span>${user.phone}</span><br>
            <label class="successLabel">Address:</label>
            <span>${user.address}</span><br>
            <label class="successLabel">City:</label>
            <span>${user.city}</span><br>
            <label class="successLabel">State:</label>
            <span>${user.state}</span><br>
            <label class="successLabel">Zip Code:</label>
            <span>${user.zipCode}</span><br>
            <label class="successLabel">Email:</label>
            <span>${user.email}</span><br>
            <label class="successLabel">User Name:</label>
            <span class="loginDetails">${user.userName}</span><br>
            <label class="successLabel">Temporary Password:</label>
            <span class="loginDetails">welcome1</span><br>
            </div>
        </section>
               <c:import url="/includes/footer.jsp" />
