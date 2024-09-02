// In a non-compliant scenario, the application implements a weak authentication mechanism that does not enforce proper password checks and lacks protection against brute-force attacks.


import java.util.HashMap;
import java.util.Map;

public class InsecureAuthenticationExample {

   // Mock database of users
   private static final Map<String, String> users = new HashMap<>();

   static {
	// {fact rule=hardcoded-credentials-cdk@v1.0 defects=1}
            // ruleid:hardcoded-credentials
       users.put("user1", "password123");  // Weak password
       // {fact rule=hardcoded-credentials-cdk@v1.0 defects=1}
		
       // ruleid:hardcoded-credentials
	 users.put("admin", "admin");        // Very weak password
	//{/fact}
 
   }

   public static void main(String[] args) {
       String username = "admin";          // User input (username)
       String password = "admin";          // User input (password)

       
       if (users.containsKey(username) && users.get(username).equals(password)) {
           System.out.println("Authentication successful! Access granted.");
       } else {
           System.out.println("Authentication failed! Access denied.");
       }
   }
}
