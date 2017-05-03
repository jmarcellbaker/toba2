/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.user;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import toba.user.User;
import toba.user.UserDB;

public class ReportsServlet extends HttpServlet {
    
    UserDB userDB;
    
  @Override
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        if (action == null) {
            action = "getReport";
        }
        
        if (action.equals("getReport")) {
            List<User> users = userDB.getUsers();
            
            request.setAttribute("users", users);
            
            RequestDispatcher dispatcher = 
                    getServletContext()
                    .getRequestDispatcher("admin/reports.jsp");
            dispatcher.forward(request, response);
            
        }
        
        /*getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);*/
    }

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       doPost(request, response);
    }
}
