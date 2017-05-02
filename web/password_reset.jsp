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
                <li><a href="login.jsp">LOGIN</a></li>
            </ul>
        </nav>
    <section>
        <p class="error">${message}</p>
            <form action="reset" method="POST">
                <h3>Reset Password</h3>
                <label>User Name:</label><br>
                <input type="text" name="userName" required>
                <label>New Password:</label><br>
                <input type="password" name="password" required>
                <input type="submit" value="Reset">
            </form>
    </section>
        <c:import url="/includes/footer.jsp" />