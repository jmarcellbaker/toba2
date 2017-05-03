<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />
        <header>
            <img src="images/tobalogo.PNG" alt="TOBA LOGO">
        </header>
        <nav>
            <ul>
                <li><a href="index.jsp">LOGOUT</a></li>
                <li><a href="transaction.jsp">TRANSACTION</a></li>
            </ul>
        </nav>
        <section>
            <h1>Account Activity</h1>
           
            <c:if test="${sessionScope.user != null}">
                        <p class="welcome">Welcome ${user.firstName} ${user.lastName}!</p>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                        <p class="welcome">Not Logged In</p>
            </c:if>
                        
                
        </section>
<c:import url="/includes/footer.jsp" />
