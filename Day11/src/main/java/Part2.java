import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Part2 {
    private static final ArrayList<String> alphabet = new ArrayList<>(Arrays.asList("abcdefghjkmnpqrstuvwxyz".split("")));

    public static void main(String[] args) {
        String password = increment("cqjxxyzz");
        while (!validPassword(password)) {
            password = increment(password);
        }
        System.out.println(password);
    }

    private static boolean validPassword(String password) {
        if (password.matches(".*[iol].*")) {
            return false;
        }

        HashSet<String> straights = new HashSet<>(Arrays.asList("abc", "bcd", "cde", "def", "efg", "fgh", "pqr", "qrs", "rst", "stu", "tuv", "uvw", "vwx", "wxy", "xyz"));
        boolean containsStraight = false;
        for (String straight : straights) {
            if (password.contains(straight)) {
                containsStraight = true;
                break;
            }
        }

        if (!containsStraight) {
            return false;
        }

        int pairCount = 0;
        for (String letter : alphabet) {
            if (password.contains(letter + letter)) {
                pairCount++;
                if (pairCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String increment(String password) {
        int lastCharacterIndex = password.length() - 1;
        String characterToRoll = password.split("")[lastCharacterIndex];
        String slicedPassword = password.substring(0, lastCharacterIndex);
        if (characterToRoll.equals("z")) {
            return (increment(slicedPassword) + "a");
        } else {
            String nextLetter = alphabet.get(alphabet.indexOf(characterToRoll) + 1);
            return slicedPassword + nextLetter;
        }
    }
}
