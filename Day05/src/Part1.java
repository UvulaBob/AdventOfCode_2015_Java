import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        ArrayList<String> strings = new ArrayList<>(lines);

        int niceStrings = 0;
        String[] vowels = "aeiou".split("");
        String[] badStrings = new String[] {"ab", "cd", "pq", "xy"};


        for (String string : strings) {
            boolean thisIsABadString = false;
            for (String badString: badStrings) {
                if (string.contains(badString)) {
                    thisIsABadString = true;
                    break;
                }
            }
            if (thisIsABadString) {
                continue;
            }

            int vowelCount = 0;
            for (String character : string.split("")) {
                for (String vowel : vowels) {
                    if (character.equals(vowel)) {
                        vowelCount++;
                        break;
                    }
                }
            }

            if (vowelCount < 3) {
                thisIsABadString = true;
            }
            if (thisIsABadString) {
                continue;
            }

            boolean hasDoubleLetter = false;
            for (int i = 0; i < string.length() - 1; i++) {
                if (string.charAt(i) == string.charAt(i + 1)) {
                    hasDoubleLetter = true;
                    break;
                }
            }
            if (hasDoubleLetter) {
                niceStrings++;
            }
        }

        System.out.println("Nice Strings: " + niceStrings);
        System.out.println("Done!");
    }
}
