import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part2 {
    private static ArrayList<Integer> input = new ArrayList<>();
    private static LinkedList<Integer> queue = new LinkedList<>();
    private static ArrayList<ArrayList<Integer>> combos = new ArrayList<>();

    public static void main(String[] args) {
        initialize();
        int smallestComboSize = Integer.MAX_VALUE;

        int targetWeight = addInputvalues(input) / 4;
        Collections.reverse(input);
        for (int primaryPointer = 0; primaryPointer < input.size(); primaryPointer++) {
            queue.push(primaryPointer);
            int secondaryPointer = primaryPointer + 1;
            while (!queue.isEmpty()) {
                while (secondaryPointer < input.size()) {
                    int potentialAdditionalNumber = input.get(secondaryPointer);
                    int totalSoFar = addQueueValues(queue);
                    if (totalSoFar + potentialAdditionalNumber < targetWeight) {
                        queue.push(secondaryPointer);
                    } else if (totalSoFar + potentialAdditionalNumber == targetWeight) {
                        ArrayList<Integer> combo = new ArrayList<>();
                        for (int index : queue) {
                            combo.add(input.get(index));
                        }
                        combo.add(potentialAdditionalNumber);
                        if (combo.size() <= smallestComboSize) {
                            Collections.sort(combo);
                            combos.add(combo);
                            smallestComboSize = combo.size();
                        }
                    }
                    secondaryPointer++;
                }
                if (queue.peek() != null) {
                    secondaryPointer = queue.peek() + 1;
                    queue.poll();
                }
            }
        }

        long smallestQuantum = Long.MAX_VALUE;
        for (ArrayList<Integer> combo : combos) {
            if (combo.size() == smallestComboSize) {
                long quantum = multiplyValues(combo);
                if (quantum < smallestQuantum) {
                    smallestQuantum = quantum;
                }
            }
        }

        System.out.println("Smallest Quantum: " + smallestQuantum);
        System.out.println("Done!");

    }

    private static int addQueueValues(LinkedList<Integer> values) {
        int total = 0;
        for (int value : values) {
            total += input.get(value);
        }
        return total;
    }

    private static int addInputvalues(ArrayList<Integer> values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    private static long multiplyValues(ArrayList<Integer> values) {
        long total = 1;
        for (int value : values) {
            total *= value;
        }
        return total;
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day24\\src\\main\\resources\\input.txt";

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
                input.add(Integer.parseInt(line));
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
