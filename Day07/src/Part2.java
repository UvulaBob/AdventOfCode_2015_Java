import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static HashMap<String, String> wires = new HashMap<>();

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        for (String line : lines) {
            String[] splitLine = line.split(" -> ");
            wires.put(splitLine[1], splitLine[0]);
        }
        
        // I should figure out how to generate the value to overwrite "b" with at runtime by running Part 1 first.
        wires.put("b", "16076");

        int valueOfWire = findValueOfWire("a");
        System.out.println(valueOfWire);
        System.out.println("Done!");
    }

    private static int findValueOfWire(String wire) {
        String wireValue;

        try {
            return Integer.parseInt(wire);
        } catch (NumberFormatException e) {
            wireValue = wires.get(wire);
        }

        try {
            return Integer.parseInt(wireValue);
        } catch (NumberFormatException e) {
            int result = doInstruction(wireValue);
            wires.put(wire, String.valueOf(result));
            return result;
        }
    }

    private static int doInstruction(String instruction) {
        String[] splitInstruction = instruction.split(" ");
        if (splitInstruction.length == 1) {
            return findValueOfWire(splitInstruction[0]);
        }

        if (splitInstruction.length == 2) {
            int operand;
            try {
                operand = Integer.parseInt(splitInstruction[1]);
            } catch (NumberFormatException e) {
                operand = findValueOfWire(splitInstruction[1]);
            }
            return ~operand & 65535;
        }

        String operation = splitInstruction[1];
        int firstValue = findValueOfWire(splitInstruction[0]);
        int secondValue = findValueOfWire(splitInstruction[2]);
        switch (operation) {
            case "AND":
                return firstValue & secondValue;
            case "OR":
                return firstValue | secondValue;
            case "LSHIFT":
                return firstValue << secondValue;
            default:
                return firstValue >> secondValue;
        }
    }
}
