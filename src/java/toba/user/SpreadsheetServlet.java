/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toba.user;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author jmarc
 */
@WebServlet("/Spreadsheet")
public class SpreadsheetServlet extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException, ServletException {

        // create the workbook, its worksheet, and its title row
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Table");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Monthly Registration Report");
        
        // create the header row
        row = sheet.createRow(2);
        row.createCell(0).setCellValue("UserName");
        row.createCell(1).setCellValue("Address");
        row.createCell(2).setCellValue("City");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("FirstName");
        row.createCell(5).setCellValue("LastName");
        row.createCell(6).setCellValue("Password");
        row.createCell(7).setCellValue("Phone");
        row.createCell(8).setCellValue("RegistrationDate");
        row.createCell(9).setCellValue("Salt");
        row.createCell(10).setCellValue("State");
        row.createCell(11).setCellValue("ZipCode");
        
        try {
            // read database rows
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM User";    
            ResultSet results = statement.executeQuery(query);
            
            // create the spreadsheet rows
            int i = 3;
            while (results.next()) {
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(results.getString("USERNAME"));
                row.createCell(1).setCellValue(results.getString("ADDRESS"));
                row.createCell(2).setCellValue(results.getString("CITY"));
                row.createCell(3).setCellValue(results.getString("EMAIL"));
                row.createCell(4).setCellValue(results.getString("FIRSTNAME"));
                row.createCell(5).setCellValue(results.getString("LASTNAME"));
                row.createCell(6).setCellValue(results.getString("PASSWORD"));
                row.createCell(7).setCellValue(results.getString("PHONE"));
                row.createCell(8).setCellValue(results.getString("REGISTRATIONDATE"));
                row.createCell(9).setCellValue(results.getString("SALT"));
                row.createCell(10).setCellValue(results.getString("STATE"));
                row.createCell(11).setCellValue(results.getString("ZIPCODE"));
                i++;
            }
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            this.log(e.toString());
        }

        // set the response headers
        response.setHeader("content-disposition", 
                "attachment; filename=users.xls");
        response.setHeader("cache-control", "no-cache");

        // get the output stream and send the workbook to the browser
        OutputStream out = response.getOutputStream();
        workbook.write(out);
        out.close();
    }
}
