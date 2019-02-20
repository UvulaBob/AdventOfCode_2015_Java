import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static ArrayList<Reindeer> reindeers = new ArrayList<>();
    private static HashMap<String, Integer> distances = new HashMap<>();

    public static void main(String[] args) {
        int raceDuration = 2503;
        initialize();

        for (Reindeer reindeer : reindeers) {
            int travelChunk = reindeer.speed * reindeer.travelDuration;
            int travelChunkDuration = reindeer.travelDuration + reindeer.restDuration;
            int minNumOfTravelChunks = raceDuration / travelChunkDuration;
            int leftoverSeconds = raceDuration % travelChunkDuration;
            int extraDistance = 0;
            if (leftoverSeconds > 0) {
                if (leftoverSeconds > reindeer.travelDuration) {
                    extraDistance = travelChunk;
                } else {
                    extraDistance = leftoverSeconds * reindeer.speed;
                }
            }

            int totalDistance = (minNumOfTravelChunks * travelChunk) + extraDistance;
            distances.put(reindeer.name, totalDistance);

        }


        ArrayList<Integer> distanceValues = new ArrayList<>(distances.values());
        Collections.sort(distanceValues);
        Collections.reverse(distanceValues);
        System.out.println("Done!");
        System.out.println("The fastest reindeer went " + distanceValues.get(0) + "km");


    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day14\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            Pattern pattern = Pattern.compile("^(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+)");

            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    Reindeer newReindeer = new Reindeer();
                    newReindeer.name = m.group(1);
                    newReindeer.speed = Integer.parseInt(m.group(2));
                    newReindeer.travelDuration = Integer.parseInt(m.group(3));
                    newReindeer.restDuration = Integer.parseInt(m.group(4));
                    reindeers.add(newReindeer);
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
