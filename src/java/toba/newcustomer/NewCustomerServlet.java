package toba.newcustomer;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.*;
import toba.account.Account;
import toba.account.AccountDB;

import toba.user.User;
import toba.user.UserDB;

public class NewCustomerServlet extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = "/index.jsp";
        String firstName = "";
        String lastName = "";
        String phone = "";
        String address = "";
        String city = "";
        String state = "";
        String zipCode = "";
        String email = "";
        String userName = "";
        String password = "";
        String salt = "";
        String registrationDate = "";
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "newCustomer";
        }
        
        if (action.equals("newCustomer")) {
            firstName = request.getParameter("firstName");
            lastName = request.getParameter("lastName");
            phone = request.getParameter("phone");
            address = request.getParameter("address");
            city = request.getParameter("city");
            state = request.getParameter("state");
            zipCode = request.getParameter("zipCode");
            email = request.getParameter("email");

            User user = new User();
            
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhone(phone);
            user.setAddress(address);
            user.setCity(city);
            user.setState(state);
            user.setZipCode(zipCode);
            user.setEmail(email);
            user.setUserName(firstName + zipCode);
            user.setPassword("welcome1");
            user.setSalt("");
            user.setRegistrationDate("");
            
            String hashedPassword;
            
            try {
                salt = PasswordUtil.getSalt();
                hashedPassword = PasswordUtil.hashPassword(salt + user.getPassword());
            } catch (NoSuchAlgorithmException ex) {
                hashedPassword = ex.getMessage();
            }
            
            request.setAttribute("hashedPassword", hashedPassword);
            request.setAttribute("salt", salt);
            
            
            Account.Type cAccount = Account.Type.CHECKING;
            Account.Type sAccount = Account.Type.SAVINGS;
            
            Account checkingAccount = new Account(user, 0.00, cAccount);
            Account savingsAccount = new Account(user, 25.00, sAccount);
            
            //account.creditChecking(0.0);
            //account.creditSavings(25.0);
            

            //HttpSession session = request.getSession();
            
            //session.setAttribute("user", user);
            //session.setAttribute("account", account);

            String message;
        
            
            if (firstName == null || lastName == null || phone == null || address == null || 
                city == null || state == null || zipCode == null || email == null ||
                firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty() || 
                city.isEmpty() || state.isEmpty() || zipCode.isEmpty() || email.isEmpty()) {

                message = "Please fill out all the form fields";
                request.setAttribute("message", message);
                url = "/new_customer.jsp";
            }

            else {
                message = "";
                request.setAttribute("message", message);
                url = "/success.jsp";
                
                user.setPassword(hashedPassword);
                user.setSalt(salt);
                
                String date = new SimpleDateFormat("yyy.MM.dd").format(new Date());
                user.setRegistrationDate(date);
                
                UserDB.insert(user);
                
                AccountDB.insert(checkingAccount);
                AccountDB.insert(savingsAccount);
            }
            
            request.setAttribute("user", user);
            request.setAttribute("checkingAccount", checkingAccount);
            request.setAttribute("savingsAccount", savingsAccount);
            //request.setAttribute("message", message);
        }

                       
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

}
