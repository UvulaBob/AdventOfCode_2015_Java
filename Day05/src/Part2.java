import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        ArrayList<String> strings = new ArrayList<>(lines);

        int niceStrings = 0;

        for (String string : strings) {

            if (rule1(string) && rule2(string)) {
                niceStrings++;
            }
        }

        System.out.println("Done!");
        System.out.println("Nice Strings: " + niceStrings);
    }


    // It contains a pair of any two letters that appears at least twice in the string without
    // overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
    private static boolean rule1(String input) {
        String[] splitInput = input.split("");
        for (int i = 0; i < splitInput.length - 3; i++) {
            String firstChar = splitInput[i];
            String secondChar = splitInput[i + 1];
            String subString = firstChar + secondChar;
            if (input.indexOf(subString, i + 2) > -1) {
                return true;
            }
        }
        return false;
    }

    // It contains at least one letter which repeats with exactly one letter between them,
    // like xyx, abcdefeghi (efe), or even aaa.
    private static boolean rule2(String input) {
        String [] splitInput = input.split("");
        for (int i = 0; i < splitInput.length - 2; i++) {
            if (splitInput[i].equals(splitInput[i + 2])) {
                return true;
            }
        }

        return false;
    }
}
