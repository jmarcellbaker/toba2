<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/includes/header.html" />
        <header>
            <img src="images/tobalogo.PNG" alt="TOBA LOGO">
        </header>
        <nav>
            <ul>
                <li><a href="index.jsp">LOGOUT</a></li>
                <li><a href="account_activity.jsp">ACCOUNT ACTIVITY</a></li>
            </ul>
        </nav>
        <section>
            <h1>Transaction</h1>
            <table align="center">
                <caption>Balance</caption>
                <tr>
                    <th>Checking</th>
                    <th>Savings</th>
                </tr>
                <tr>
                    <td>$${checkingAccount.balance}</td>
                    <td>$${savingsAccount.balance}</td>
                </tr>
            </table>
                <br>
                <br>
                <form action="transfer" method="POST">
                <h3>Transfer Funds</h3><br>
                <label>From:</label><br>
                <select name="debitAccount">
                    <option value="debitChecking">Checking</option>
                    <option value="debitSavings">Savings</option>
                </select>
                <label>Amount:</label><br>
                <input type="text" name="funds"><br>
                <input type="submit" value="Transfer">
            </form>
                
        </section>
                       <c:import url="/includes/footer.jsp" />
