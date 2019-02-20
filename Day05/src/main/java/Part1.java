import java.io.*;
import java.util.ArrayList;

public class Part1 {
    private static ArrayList<String> strings = new ArrayList<>();

    public static void main(String[] args) {
        int niceStrings = 0;
        String[] vowels = "aeiou".split("");
        String[] badstrings = new String[] {"ab", "cd", "pq", "xy"};

        initialize();
        for (String string : strings) {
            boolean thisIsABadString = false;
            for (String badString: badstrings) {
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

        System.out.println("Done!");
        System.out.println("Nice Strings: " + niceStrings);
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
