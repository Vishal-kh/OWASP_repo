// In a non-compliant scenario, the application might allow account deletion based solely on the user ID passed through the URL, without verifying that the request is being made by the rightful account owner.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class InsecureDesignExample {

   public static void main(String[] args) {
       String userIdToDelete = "123";  // User ID taken from the URL or input

       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");
           Statement stmt = conn.createStatement();

           // Insecure design: Directly allowing account deletion based on user ID from the input
           // {fact rule=hardcoded-credentials-cdk@v1.0 defects=1}
            // ruleid:insecure-design-account-deletion
           String query = "DELETE FROM users WHERE user_id = " + userIdToDelete;
           stmt.executeUpdate(query);
		//{/fact}

           System.out.println("User account deleted.");
           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
	public static void AnotherMain(String[] args) {
       String currentUserId = "123";  // User ID from the session (authenticated user)
       String userIdToDelete = "123";  // User ID taken from the URL or input

       try {
           // Establish a connection to the database
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

           // Secure design: Ensure the current user is the owner of the account they want to delete
           if (currentUserId.equals(userIdToDelete)) {
             // {fact rule=hardcoded-credentials-cdk@v1.0 defects=0}
            // ok:insecure-design-account-deletion
               String query = "DELETE FROM users WHERE user_id = ?";
               PreparedStatement pstmt = conn.prepareStatement(query);
               pstmt.setString(1, currentUserId);

               pstmt.executeUpdate();
                //{/fact}
               System.out.println("User account deleted.");
           } else {
               System.out.println("Unauthorized request: You can only delete your own account.");
           }

           conn.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
}
