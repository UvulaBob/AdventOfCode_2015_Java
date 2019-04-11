import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {
    private static ArrayList<Integer> values = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> combos = new ArrayList<>();
        
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        for (String line : lines) {
            values.add(Integer.parseInt(line));
        }

        int smallestComboSize = Integer.MAX_VALUE;

        int targetWeight = addInputValues(values) / 4;
        Collections.reverse(values);
        for (int primaryPointer = 0; primaryPointer < values.size(); primaryPointer++) {
            queue.push(primaryPointer);
            int secondaryPointer = primaryPointer + 1;
            while (!queue.isEmpty()) {
                while (secondaryPointer < values.size()) {
                    int potentialAdditionalNumber = values.get(secondaryPointer);
                    int totalSoFar = addQueueValues(queue);
                    if (totalSoFar + potentialAdditionalNumber < targetWeight) {
                        queue.push(secondaryPointer);
                    } else if (totalSoFar + potentialAdditionalNumber == targetWeight) {
                        ArrayList<Integer> combo = new ArrayList<>();
                        for (int index : queue) {
                            combo.add(values.get(index));
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
            total += Part2.values.get(value);
        }
        return total;
    }

    private static int addInputValues(ArrayList<Integer> values) {
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

}
