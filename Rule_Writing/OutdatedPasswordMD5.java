// In the non-compliant scenario, the password is e hashed using a weak or outdated algorithm like MD5.

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class OutdatedPasswordMD5 {

   // Storing the password with weak hashing using MD5
   public static String hashPasswordMD5(String plainPassword) {
       try {
    
           // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=1}
           // ruleid:java-weak-hashing-algorithm
           MessageDigest md = MessageDigest.getInstance("MD5");
           //{/fact}
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
    public static String hashPasswordSHA256(String password) {
       try {
           
           // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=0}
           //ok:java-weak-hashing-algorithm
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
           //{/fact}

           // Hash the password bytes
           byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
           // Convert the byte array to a hexadecimal string
           StringBuilder hexString = new StringBuilder();
           for (byte b : encodedHash) {
               String hex = Integer.toHexString(0xff & b);
               if (hex.length() == 1) {
                   hexString.append('0');
               }
               hexString.append(hex);
           }
           return hexString.toString();
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       }
   }

   public static String hashPasswordMD2(String plainPassword) {
   try {
       // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=1}
       // ruleid:java-weak-hashing-algorithm
       MessageDigest md = MessageDigest.getInstance("MD2");
       //{/fact}
       md.update(plainPassword.getBytes());
       byte[] digest = md.digest();
       StringBuilder sb = new StringBuilder();
       for (byte b : digest) {
           sb.append(String.format("%02x", b));
       }
       return sb.toString();
   } catch (NoSuchAlgorithmException e) {
       throw new RuntimeException(e);
   }
}

public static String hashPasswordMD4(String plainPassword) {
   try {
       // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=1}
       // ruleid:java-weak-hashing-algorithm
       MessageDigest md = MessageDigest.getInstance("MD4");
       //{/fact}
       md.update(plainPassword.getBytes());
       byte[] digest = md.digest();
       StringBuilder sb = new StringBuilder();
       for (byte b : digest) {
           sb.append(String.format("%02x", b));
       }
       return sb.toString();
   } catch (NoSuchAlgorithmException e) {
       throw new RuntimeException(e);
   }
}

public static String hashPasswordSHA0(String plainPassword) {
   try {
       // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=1}
       // ruleid:java-weak-hashing-algorithm
       MessageDigest md = MessageDigest.getInstance("SHA-0");
       //{/fact}
       md.update(plainPassword.getBytes());
       byte[] digest = md.digest();
       StringBuilder sb = new StringBuilder();
       for (byte b : digest) {
           sb.append(String.format("%02x", b));
       }
       return sb.toString();
   } catch (NoSuchAlgorithmException e) {
       throw new RuntimeException(e);
   }
}
public static String hashPasswordSHA0(String plainPassword) {
   try {
       // {fact rule=java-weak-hashing-algorithm-cdk@v1.0 defects=1}
       // ruleid:java-weak-hashing-algorithm
       MessageDigest md = MessageDigest.getInstance("SHA-0");
       //{/fact}
       md.update(plainPassword.getBytes());
       byte[] digest = md.digest();
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
       String plainPassword = "mySecureP@ssw0rd";
       String hashedPassword1 = hashPasswordMD5(plainPassword);
        String hashedPassword2 = hashPassword2(plainPassword);
       //Store the hashedPassword in the database (simulated here)
       System.out.println("Hashed Password MD5: " + hashedPassword1);
       System.out.println("Hashed Password2 SHA-256: " + hashedPassword2);
   }
}