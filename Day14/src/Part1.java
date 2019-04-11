import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException{
        ArrayList<Reindeer> reindeers = new ArrayList<>();
        HashMap<String, Integer> distances = new HashMap<>();
        int raceDuration = 2503;
        
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsoluteFile() + "\\src\\input.txt"));
        Pattern pattern = Pattern.compile("^(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+)");

        for (String line : lines) {
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

        System.out.println("The fastest reindeer went " + distanceValues.get(0) + "km");
        System.out.println("Done!");
    }
}
