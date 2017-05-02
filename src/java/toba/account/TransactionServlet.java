/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static toba.account.Account.Type.CHECKING;
import static toba.account.Account.Type.SAVINGS;
import toba.user.User;
import toba.user.UserDB;



public class TransactionServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TransactionServelet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransactionServelet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("transfer");
        if (action == null) {
            action = "transfer";
        }
        
        if(action.equals("transfer")) {
            
            //String url = "";
            String debitAccount; 
            double funds;
            
            debitAccount = request.getParameter("debitAccount"); // Either debitChecking or debitSavings
            funds = Double.parseDouble(request.getParameter("funds")); // Pulls in the amount from the user
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            String userName = user.getUserName();
            
            Account checkingAccount = new Account();
            Account savingsAccount = new Account();
            
            Transaction transaction1 = new Transaction();
            Transaction transaction2 = new Transaction();

            user = UserDB.getUserbyID(userName);
            checkingAccount = AccountDB.selectChecking(userName, CHECKING);
            savingsAccount = AccountDB.selectSavings(userName, SAVINGS);
            

            // Change transfer method to only select one account from dropdown list and amount
            if(debitAccount.equals("debitChecking")) {
                checkingAccount.debit(funds);
                savingsAccount.credit(funds);
                
            }
            else {
                checkingAccount.credit(funds);
                savingsAccount.debit(funds);
  
            }
             
            double cBalance = checkingAccount.getBalance();
            double sBalance = savingsAccount.getBalance();
            Date date = new Date();
            transaction1 = new Transaction(checkingAccount, date, funds, cBalance, CHECKING);
            transaction2 = new Transaction(savingsAccount, date, funds, sBalance, SAVINGS);
                    
            AccountDB.update(checkingAccount);
            AccountDB.update(savingsAccount);
            TransactionDB.insert(transaction2);
            TransactionDB.insert(transaction1);
            
            
            request.setAttribute("user", user);
            request.setAttribute("checkingAccount", checkingAccount);
            request.setAttribute("savingsAccount", savingsAccount);
            request.setAttribute("transaction1", transaction1);
            request.setAttribute("transaction2", transaction2);
        }
        
           String url = "/transaction.jsp";
            
            getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    /*private Transaction Transaction(Account checkingAccount, Date date, double funds, double cBalance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

}
