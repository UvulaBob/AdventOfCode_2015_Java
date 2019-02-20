import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static HashMap<String, Integer> happinessChanges = new HashMap<>();
    private static ArrayList<String> guestNames = new ArrayList<>();
    private static HashSet<ArrayList<String>> seatingArrangements = new HashSet<>();
    private static int totalHappinessChange = 0;

    public static void main(String[] args) {

        initialize();

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

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day13\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            Pattern pattern = Pattern.compile("^(\\w+).*(gain|lose)\\s(\\d+).*to (\\w+)");
            while ((line = bufferedReader.readLine()) != null) {
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
