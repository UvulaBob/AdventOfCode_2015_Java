import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Part2 {
    private static HashMap<String, Integer> stringLiteralLengths = new HashMap<>();
    private static HashMap<String, Integer> stringEncodedLengths = new HashMap<>();

    public static void main(String[] args) {
        initialize();
        for (String string : stringLiteralLengths.keySet()) {
            String[] splitString = string.split("");
            int encodedCharacterCount = string.length() + 2;
            for (String character : splitString) {
                if (character.equals("\"") || character.equals("\\")) {
                    encodedCharacterCount++;
                }
                stringEncodedLengths.put(string, encodedCharacterCount);
            }
        }

        int totalCharactersInCode = 0;
        int totalEncodedCharacters = 0;

        System.out.println("Done!");
        for (int value : stringLiteralLengths.values()) {
            totalCharactersInCode += value;
        }
        for (int value : stringEncodedLengths.values()) {
            totalEncodedCharacters += value;
        }

        int answer = totalEncodedCharacters - totalCharactersInCode;
        System.out.println("Answer: " + answer);
        System.out.println("Done!");

    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day8\\src\\main\\resources\\input.txt";

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
                stringLiteralLengths.put(line, line.length());
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
