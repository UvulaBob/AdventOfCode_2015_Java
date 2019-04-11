import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException{
        HashMap<String, Integer> stringLiteralLengths = new HashMap<>();
        HashMap<String, Integer> stringEncodedLengths = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        for (String line : lines) {
            stringLiteralLengths.put(line, line.length());
        }

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

        for (int value : stringLiteralLengths.values()) {
            totalCharactersInCode += value;
        }
        for (int value : stringEncodedLengths.values()) {
            totalEncodedCharacters += value;
        }

        System.out.println("Answer: " + (totalEncodedCharacters - totalCharactersInCode));
        System.out.println("Done!");

    }
}
