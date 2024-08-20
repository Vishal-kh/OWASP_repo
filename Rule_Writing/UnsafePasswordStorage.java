// In the non-compliant scenario, the password is either stored in plaintext or hashed using a weak or outdated algorithm like MD5.


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UnsafePasswordStorage {

   // Storing the password with weak hashing using MD5
   public static String hashPassword(String plainPassword) {
       try {
           // Create MD5 message digest instance
           // {fact rule=week-hashing-md5-cdk@v1.0 defects=1}
            // ruleid:week-hashing-md5
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainPassword.getBytes());
           byte[] digest = md.digest();

           // Convert the byte array into hex format
           StringBuilder sb = new StringBuilder();
           for (byte b : digest) {
               sb.append(String.format("%02x", b));
           }
           return sb.toString();
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       }
   }

   // Example usage
   public static void main(String[] args) {
        // {fact rule=week-hashing-md5-cdk@v1.0 defects=1}
        // ruleid:week-hashing-md5
       String plainPassword = "mySecureP@ssw0rd";
        // {fact rule=week-hashing-md5-cdk@v1.0 defects=1}
        // ruleid:week-hashing-md5
       String hashedPassword = hashPassword(plainPassword);

       // Store the hashedPassword in the database (simulated here)
       System.out.println("Hashed Password: " + hashedPassword);
   }
}