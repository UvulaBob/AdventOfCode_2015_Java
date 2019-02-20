import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static ArrayList<Reindeer> reindeers = new ArrayList<>();

    public static void main(String[] args) {
        int raceDuration = 2503;
        initialize();

        int leaderDistance = 0;
        for (int i = 0; i < raceDuration; i++) {
            for (Reindeer reindeer : reindeers) {
                if (reindeer.resting) {
                    reindeer.restTimeLeft--;
                    if (reindeer.restTimeLeft == 0) {
                        reindeer.resting = false;
                        reindeer.travelTimeLeft = reindeer.travelDuration;
                    }
                } else {
                    reindeer.distanceTraveled = reindeer.distanceTraveled + reindeer.speed;
                    if (reindeer.distanceTraveled > leaderDistance) {
                        leaderDistance = reindeer.distanceTraveled;
                    }
                    reindeer.travelTimeLeft--;
                    if (reindeer.travelTimeLeft == 0) {
                        reindeer.resting = true;
                        reindeer.restTimeLeft = reindeer.restDuration;
                    }
                }
            }

            for (Reindeer reindeer : reindeers) {
                if (reindeer.distanceTraveled == leaderDistance) {
                    reindeer.points++;
                }
            }

            Collections.sort(reindeers);
            Collections.reverse(reindeers);

        }

        Reindeer winner = reindeers.get(0);
        System.out.println(String.format("The winner is %s with %s points!", winner.name, winner.points));
        System.out.println("Done!");
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
                    newReindeer.travelTimeLeft = Integer.parseInt(m.group(3));
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
