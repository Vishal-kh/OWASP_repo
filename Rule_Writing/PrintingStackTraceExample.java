// In a non-compliant scenario, the application is configured to display detailed error messages, including stack traces, which can be valuable to attackers.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrintingStackTraceExample {

      public static void main(String[] args) {
       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "wrongpassword");

           // Further database operations...
       } 
      //{fact rule=logged-detail-error-msg-cdk@v1.0 defects=1}
        // ruleid:logged-detail-error-msg
       catch (SQLException e) {
           // Insecure: Printing the detailed stack trace, including sensitive information
           e.printStackTrace();
          //{/fact}  
       }
      }

   private static final Logger logger = Logger.getLogger(SecureMisconfigurationExample.class.getName());

   public static void AnotherMain(String[] args) {
       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "wrongpassword");

           // Further database operations...
       } 
       
       catch (SQLException e) {
            // {fact rule=logged-detail-error-msg-cdk@v1.0 defects=0}
            // ok:logged-detail-error-msg
           // Secure: Log the detailed error message for internal review
           logger.log(Level.SEVERE, "Database connection failed", e);
            //{/fact}
           // Display a generic error message to the user
           System.out.println("An error occurred while processing your request. Please try again later.");
       
   }
}
