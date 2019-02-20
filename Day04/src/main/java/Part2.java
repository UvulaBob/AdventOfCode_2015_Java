import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part2 {

    public static void main(String[] args) {
        String secretKey = "iwrupvqb";
        int number = 1;

        while (true) {
            System.out.println(number);
            String hash = getMd5(secretKey + number);
            String firstSixDigits = hash.substring(0, 6);
            if (firstSixDigits.equals("000000")) {
                break;
            }
            number++;
        }

        System.out.println("Done!");
        System.out.println(number);
    }

    private static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
