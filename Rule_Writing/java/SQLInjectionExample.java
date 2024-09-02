// In a non-compliant scenario, the application constructs SQL queries by concatenating user input directly into the query string, making it vulnerable to SQL injection.


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLInjectionExample {

   public static void main(String[] args) {
       String username = "admin";  // This input could be from user input, e.g., from a web form
       String password = "' OR '1'='1";  // Malicious input that causes SQL injection

       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
           Statement stmt = conn.createStatement();

           // Vulnerable SQL query due to concatenation of user input
           
           //{fact rule=sql-injection-cdk@v1.0 defects=1}
           //rule-id:sql-injection-detection
           String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
           ResultSet rs = stmt.executeQuery(query);
		//{/fact}

           if (rs.next()) {
               System.out.println("User authenticated.");
           } else {
               System.out.println("Authentication failed.");
           }

           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
		
	 public static void AnotherMain(String[] args) {
       String username = "admin";  // Normal user input
       String password = "secureP@ssword";  // Normal user input

       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
		
           // Safe SQL query using prepared statement
		//{fact rule=sql-injection-cdk@v1.0 defects=0}
            //ok:sql-injection-detection
           String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		//{/fact}
           PreparedStatement pstmt = conn.prepareStatement(query);
           pstmt.setString(1, username);
           pstmt.setString(2, password);

           ResultSet rs = pstmt.executeQuery();

           if (rs.next()) {
               System.out.println("User authenticated.");
           } else {
               System.out.println("Authentication failed.");
           }

           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}