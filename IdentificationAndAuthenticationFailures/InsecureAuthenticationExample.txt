// In a non-compliant scenario, the application implements a weak authentication mechanism that does not enforce proper password checks and lacks protection against brute-force attacks.


import java.util.HashMap;
import java.util.Map;

public class InsecureAuthenticationExample {

   // Mock database of users
   private static final Map<String, String> users = new HashMap<>();

   static {
       users.put("user1", "password123");  // Weak password
       users.put("admin", "admin");        // Very weak password
   }

   public static void main(String[] args) {
       String username = "admin";          // User input (username)
       String password = "admin";          // User input (password)

       // Insecure authentication: No protection against brute-force attacks or weak passwords
       if (users.containsKey(username) && users.get(username).equals(password)) {
           System.out.println("Authentication successful! Access granted.");
       } else {
           System.out.println("Authentication failed! Access denied.");
       }
   }
}
