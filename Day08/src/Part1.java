import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException{
        HashMap<String, Integer> stringCodeLengths = new HashMap<>();
        HashMap<String, Integer> stringMemoryLengths = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        for (String line : lines) {
            stringCodeLengths.put(line, line.length());
        }

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

        for (int value : stringCodeLengths.values()) {
            totalCharactersInCode += value;
        }
        for (int value : stringMemoryLengths.values()) {
            totalCharactersInMemory += value;
        }

        System.out.println("Answer: " + (totalCharactersInCode - totalCharactersInMemory));
        System.out.println("Done!");

    }

}
