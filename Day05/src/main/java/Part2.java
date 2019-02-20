import java.io.*;
import java.util.ArrayList;

public class Part2 {
    private static ArrayList<String> strings = new ArrayList<>();

    public static void main(String[] args) {
        int niceStrings = 0;
        initialize();

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

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day5\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                strings.add(line);
            }


            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
    }
}
