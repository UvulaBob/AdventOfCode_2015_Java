import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static ArrayList<String> instructions = new ArrayList<>();


    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> wires = new HashMap<>();
        initialize();

        // Set up first wires
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            Pattern pattern = Pattern.compile("^([0-9]+) -> ([a-z]+)");
            Matcher m = pattern.matcher(instruction);
            while (m.find()) {
                wires.put(m.group(2), Integer.parseInt(m.group(1)));
                instructions.remove(i);
            }
        }

        while (instructions.size() > 0) {
            queue.addAll(wires.keySet());

            // Known wires being modified by values
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                int currentWireValue = wires.get(currentWireName);
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^" + currentWireName + " (.*) ([0-9]*) -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        String operation = m.group(1);
                        int adjustmentValue = Integer.parseInt(m.group(2));
                        String targetWireName = m.group(3);
                        int operationResult;
                        switch (operation) {
                            case "LSHIFT":
                                operationResult = currentWireValue << adjustmentValue;
                                break;
                            case "RSHIFT":
                                operationResult = currentWireValue >>> adjustmentValue;
                                break;
                            case "AND":
                                operationResult = currentWireValue & adjustmentValue;
                                break;
                            default:
                                operationResult = currentWireValue | adjustmentValue;
                                break;
                        }
                        wires.put(targetWireName, operationResult);
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }

            queue.addAll(wires.keySet());

            // Known wires being modified by other known wires
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                int currentWireValue = wires.get(currentWireName);
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^" + currentWireName + " (.*) ([a-z]*) -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        String operation = m.group(1);
                        String otherWire = m.group(2);
                        String targetWireName = m.group(3);
                        if (!wires.keySet().contains(otherWire)) {
                            continue;
                        }
                        int adjustmentValue = wires.get(otherWire);
                        int operationResult;
                        switch (operation) {
                            case "LSHIFT":
                                operationResult = currentWireValue << adjustmentValue;
                                break;
                            case "RSHIFT":
                                operationResult = currentWireValue >>> adjustmentValue;
                                break;
                            case "AND":
                                operationResult = currentWireValue & adjustmentValue;
                                break;
                            default:
                                operationResult = currentWireValue | adjustmentValue;
                                break;
                        }
                        wires.put(targetWireName, operationResult);
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }

            queue.addAll(wires.keySet());

            // Values being modified by known wires
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^([0-9]*) ([A-Z]*) " + currentWireName + " -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        int valueToAdjust = Integer.parseInt(m.group(1));
                        String operation = m.group(2);
                        String targetWireName = m.group(3);
                        int adjustmentValue = wires.get(currentWireName);
                        int operationResult;
                        switch (operation) {
                            case "LSHIFT":
                                operationResult = valueToAdjust << adjustmentValue;
                                break;
                            case "RSHIFT":
                                operationResult = valueToAdjust >>> adjustmentValue;
                                break;
                            case "AND":
                                operationResult = valueToAdjust & adjustmentValue;
                                break;
                            default:
                                operationResult = valueToAdjust | adjustmentValue;
                                break;
                        }
                        wires.put(targetWireName, operationResult);
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }

            queue.addAll(wires.keySet());

            // Other wires being modified by known wires
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^([a-z]*) ([A-Z]*) " + currentWireName + " -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        String wireToAdjust = m.group(1);
                        if (!wires.keySet().contains(wireToAdjust)) {
                            continue;
                        }
                        int valueToAdjust = wires.get(wireToAdjust);
                        String operation = m.group(2);
                        String targetWireName = m.group(3);
                        int adjustmentValue = wires.get(currentWireName);
                        int operationResult;
                        switch (operation) {
                            case "LSHIFT":
                                operationResult = valueToAdjust << adjustmentValue;
                                break;
                            case "RSHIFT":
                                operationResult = valueToAdjust >>> adjustmentValue;
                                break;
                            case "AND":
                                operationResult = valueToAdjust & adjustmentValue;
                                break;
                            default:
                                operationResult = valueToAdjust | adjustmentValue;
                                break;
                        }
                        wires.put(targetWireName, operationResult);
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }

            queue.addAll(wires.keySet());

            // Known wires being inverted
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^NOT " + currentWireName + " -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        String targetWireName = m.group(1);
                        wires.put(targetWireName, ~wires.get(currentWireName));
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }

            queue.addAll(wires.keySet());

            // Known wires giving signal to new wires
            while (!queue.isEmpty()) {
                String currentWireName = queue.poll();
                for (int i = 0; i < instructions.size(); i++) {
                    String instruction = instructions.get(i);
                    Pattern pattern = Pattern.compile("^" + currentWireName + " -> ([a-z]+)");
                    Matcher m = pattern.matcher(instruction);
                    while (m.find()) {
                        String targetWireName = m.group(1);
                        wires.put(targetWireName, wires.get(currentWireName));
                        if (!queue.contains(targetWireName)) {
                            queue.add(targetWireName);
                        }

                        instructions.remove(i);
                        i++;
                    }
                }
            }
        }

        System.out.println("Done!");
        System.out.println("Value of Wire \"a\": " + wires.get("a"));
    }




    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day7\\src\\main\\resources\\input.txt";

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
                instructions.add(line);
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
