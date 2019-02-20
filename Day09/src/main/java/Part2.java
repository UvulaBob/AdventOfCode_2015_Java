import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static HashSet<Hop> hops = new HashSet<>();
    private static ArrayList<ArrayList<String>> routes  = new ArrayList<>();



    public static void main(String[] args) {
        initialize();

        ArrayList<String> nodes = new ArrayList<>();
        for (Hop hop : hops) {
            if (!nodes.contains(hop.node1)){
                nodes.add(hop.node1);
            }

            if (!nodes.contains(hop.node2)) {
                nodes.add(hop.node2);
            }
        }

        heapPermutation(nodes, nodes.size(), nodes.size());

        int currentMaxDistance = 0;

        for (ArrayList<String> route : routes) {
            int totalDistance = 0;
            for (int i = 0; i < route.size() - 1; i++) {
                String source = route.get(i);
                String destination = route.get(i + 1);
                for (Hop hop : hops) {
                    if ((hop.node1.equals(source) && hop.node2.equals(destination)) ||
                            hop.node2.equals(source) && hop.node1.equals(destination)) {
                        totalDistance += hop.distance;
                        break;
                    }
                }
            }
            if (totalDistance > currentMaxDistance) {
                currentMaxDistance = totalDistance;
            }
        }

        System.out.println("Done!");
        System.out.println("Max Distance: " + currentMaxDistance);

    }

    //Generating permutation using Heap Algorithm
    private static void heapPermutation(ArrayList<String> hops, int size, int n)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            routes.add(new ArrayList<>(hops));
        }

        for (int i=0; i<size; i++)
        {
            heapPermutation(hops, size-1, n);

            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                String temp = hops.get(0);
                hops.set(0, hops.get(size-1));
                hops.set(size-1, temp);
            }

            // If size is even, swap ith and last
            // element
            else
            {
                String temp = hops.get(i);
                hops.set(i, hops.get(size-1));
                hops.set(size-1, temp);
            }
        }
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day9\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            Pattern pattern = Pattern.compile("(\\w+) to (\\w+) = (\\d+)");

            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    Hop newHop = new Hop();
                    newHop.node1 = m.group(1);
                    newHop.node2 = m.group(2);
                    newHop.distance = Integer.parseInt(m.group(3));
                    hops.add(newHop);
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
