import java.util.LinkedList;

public class Part1 {
    private static int[] input = new int[] {33, 14, 18, 20, 45, 35, 16, 35, 1, 13, 18, 13, 50, 44, 48, 6, 24, 41, 30, 42};
    private static final int targetNumber = 150;
//    private static int[] input = new int[] {20, 15, 10, 5, 5};
//    private static final int targetNumber = 25;
    private static int combos = 0;

    public static void main(String[] args) {

        for (int primaryPointer = 0; primaryPointer < input.length; primaryPointer++) {
            LinkedList<Integer> queue =  new LinkedList<>();
            queue.push(primaryPointer);
            while (!queue.isEmpty()) {
                for (int secondaryPointer = queue.poll() + 1; secondaryPointer < input.length; secondaryPointer++) {
                    int currentSum = sumList(queue) + input[primaryPointer] + input[secondaryPointer];
                    if (currentSum == targetNumber) {
                        combos++;
                    } else if (currentSum < targetNumber) {
                        queue.push(secondaryPointer);
                    }
                }
            }
        }

        System.out.println("Combos: " + combos);
        System.out.println("Done!");
    }

    private static int sumList(LinkedList<Integer> list) {
        int sum = 0;
        for (int value : list) {
            sum += input[value];
        }
        return sum;
    }
}
