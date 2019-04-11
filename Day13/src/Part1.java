import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static HashSet<ArrayList<String>> seatingArrangements = new HashSet<>();


    public static void main(String[] args) throws IOException{
        HashMap<String, Integer> happinessChanges = new HashMap<>();
        ArrayList<String> guestNames = new ArrayList<>();
        int totalHappinessChange = 0;

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsoluteFile() + "\\src\\input.txt"));
        Pattern pattern = Pattern.compile("^(\\w+).*(gain|lose)\\s(\\d+).*to (\\w+)");
        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                String guest = m.group(1);
                boolean loseHappiness = m.group(2).equals("lose");
                int happiness = Integer.parseInt(m.group(3));
                String neighbor = m.group(4);

                String seating = String.format("%s, %s", guest, neighbor);
                if (loseHappiness) {
                    happiness *= -1;
                }

                happinessChanges.put(seating, happiness);
                if (!guestNames.contains(guest)) {
                    guestNames.add(guest);
                }
            }
        }

        heapPermutation(guestNames, guestNames.size());

        for (ArrayList<String> seatingArragement : seatingArrangements) {
            int arrangementHappiness = 0;
            for (int i = 0; i < seatingArragement.size(); i++) {
                String guestName = seatingArragement.get(i);
                int leftNeighborIndex = (i == 0) ? seatingArragement.size() - 1 : i - 1;
                int rightNeighborIndex = (i == seatingArragement.size() - 1) ? 0 : i + 1;
                String leftNeighborName = seatingArragement.get(leftNeighborIndex);
                String rightNeighborName = seatingArragement.get(rightNeighborIndex);

                String leftPair = guestName + ", " + leftNeighborName;
                String rightPair = guestName + ", " + rightNeighborName;

                arrangementHappiness += happinessChanges.get(leftPair);
                arrangementHappiness += happinessChanges.get(rightPair);
            }
            if (arrangementHappiness > totalHappinessChange) {
                totalHappinessChange = arrangementHappiness;
            }
        }

        System.out.println("Done!");
        System.out.println("Optimal Happiness Change: " + totalHappinessChange);

    }


    //Generating permutation using Heap Algorithm
    private static void heapPermutation(ArrayList<String> names, int size)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            for (int i = names.size(); i > 0; i--) {
                Collections.rotate(names, 1);
                if (seatingArrangements.contains(names)) {
                    return;
                }
            }
            seatingArrangements.add(new ArrayList<>(names));
        }

        for (int i=0; i<size; i++)
        {
            heapPermutation(names, size-1);

            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                String temp = names.get(0);
                names.set(0, names.get(size-1));
                names.set(size-1, temp);
            }

            // If size is even, swap ith and last
            // element
            else
            {
                String temp = names.get(i);
                names.set(i, names.get(size-1));
                names.set(size-1, temp);
            }
        }
    }
}
