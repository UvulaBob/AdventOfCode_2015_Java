import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Test {
    private static ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
    private static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {

        int targetWeight= 20;

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
                        combo.add(potentialAdditionalNumber);
                        for (int index : queue) {
                            combo.add(input.get(index));
                        }
                        Collections.sort(combo);
                        System.out.println("Woo!");
                        System.out.println(combo);
                        return;
                    }
                    secondaryPointer++;
                }
                secondaryPointer = queue.peek() + 1;
                queue.poll();
            }
        }

    }


    private static int addQueueValues(LinkedList<Integer> values) {
        int total = 0;
        for (int value : values) {
            total += input.get(value);
        }
        return total;
    }


}
