
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/student";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "12345";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Refresh in 3 seconds
        response.setHeader("Refresh", "3; URL=logInOut.jsp");
        
        
        // Get the submitted id_number from the login form
        String idNumber = request.getParameter("id_number");
        String firstName = null;
        String lastName = null;
        
        // JDBC variables
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            // Prepare query (assuming table name is 'student')
            String sql = "SELECT * FROM students WHERE id_number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idNumber);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
            } else {
                // If no record found, set error and forward back to login.jsp
                request.setAttribute("errorMessage", "Invalid ID Number. Please try again.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("logInOut.jsp");
                dispatcher.forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("logInOut.jsp");
            dispatcher.forward(request, response);
            return;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        
        // Get current date and time in the Philippines
        ZoneId philippineZone = ZoneId.of("Asia/Manila");
        ZonedDateTime now = ZonedDateTime.now(philippineZone);
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm a");
        String formatedDate = now.format(date);
        String formatedTime = now.format(time);
        
        // Set attributes to be accessed in jsp
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("date", formatedDate);
        request.setAttribute("time", formatedTime);
        
        // Forward request to jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("logInOut.jsp");
        dispatcher.forward(request, response);
    }
    
    // Optionally, allow GET requests to redirect to login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Refresh", "3; URL=logInOut.jsp");
        response.sendRedirect("logInOut.jsp");
    }
}