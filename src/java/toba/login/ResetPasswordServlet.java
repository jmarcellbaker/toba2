package toba.login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import toba.newcustomer.PasswordUtil;

import toba.user.User;
import toba.user.UserDB;

public class ResetPasswordServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
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
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "reset";
        }
        
        if (action.equals("reset")) {
            
            String password = request.getParameter("password");
            String userName = request.getParameter("userName");
            String message = "";
            String url = "/password_reset.jsp";
            
            User user = new User();
            
            user.setPassword(password);
            user.setUserName(userName);

            HttpSession session = request.getSession();
            
            if (UserDB.userNameExists(user.getUserName())) {
                 
                user = UserDB.getUserbyID(userName);
                user.setPassword(password);
                
                String hashedPassword;
                String salt = "";
                
                try {
                    salt = PasswordUtil.getSalt();
                    hashedPassword = PasswordUtil.hashPassword(salt + user.getPassword());
                } catch (NoSuchAlgorithmException ex) {
                    hashedPassword = ex.getMessage();
                }
                
                session.setAttribute("user", user);
                request.setAttribute("hashedPassword", hashedPassword);
                request.setAttribute("salt", salt);
                
                response.sendRedirect("account_activity.jsp");
                message = "";
                
                user.setPassword(hashedPassword);
                user.setSalt(salt);
                UserDB.update(user);
        }
            else {
                message = "User name does not match";
                url = "/password_reset.jsp";
                
                request.setAttribute("message", message);
                
                getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
            }
            
        request.setAttribute("user", user);    
        request.setAttribute("message", message);
        
        processRequest(request, response);
    }
   }
   

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

