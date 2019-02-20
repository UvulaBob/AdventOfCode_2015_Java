import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Part1 {
    private static HashMap<String, Integer> stringCodeLengths = new HashMap<>();
    private static HashMap<String, Integer> stringMemoryLengths = new HashMap<>();

    public static void main(String[] args) {
        initialize();
        for (String string : stringCodeLengths.keySet()) {
            String[] splitString = string.split("");
            int charactersInMemory = string.length() - 2;
            for (int i = 0; i < string.length() - 2; i++) {
                String firstChar = splitString[i];
                String secondChar = splitString[i + 1];
                if (firstChar.equals("\\") && secondChar.equals("\\")) {
                    charactersInMemory--;
                    i++;
                }
                if (firstChar.equals("\\") && secondChar.equals("\"")) {
                    charactersInMemory--;
                    i++;
                }
                if (string.length() - i > 3) {
                    if (firstChar.equals("\\") && secondChar.equals("x")) {
                        charactersInMemory -= 3;
                        i += 3;
                    }
                }
            }
            stringMemoryLengths.put(string, charactersInMemory);
        }

        int totalCharactersInCode = 0;
        int totalCharactersInMemory = 0;

        System.out.println("Done!");
        for (int value : stringCodeLengths.values()) {
            totalCharactersInCode += value;
        }
        for (int value : stringMemoryLengths.values()) {
            totalCharactersInMemory += value;
        }

        int answer = totalCharactersInCode - totalCharactersInMemory;
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
                stringCodeLengths.put(line, line.length());
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
