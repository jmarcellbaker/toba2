<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />
        <header>
            <img src="images/tobalogo.PNG" alt="TOBA LOGO">
        </header>
        <nav>
            <ul>
                <li><a href="index.jsp">HOME</a></li>
                <li><a href="new_customer.jsp">NEW CUSTOMER</a></li>
            </ul>
        </nav>
    <section>
            <form action="login" method="POST">
                <h3>Customer Login</h3>
                <label>Username:</label><br>
                <input type="text" name="userName" required><br>
                <label>Password:</label><br>
                <input type="password" name="password" required>
                <input type="hidden" name="action" value="login"><br><br>
                <input type="submit" value="Login"><br><br>
                <a href="password_reset.jsp">Reset Password</a>
            </form>
    </section>
       <c:import url="/includes/footer.jsp" />
