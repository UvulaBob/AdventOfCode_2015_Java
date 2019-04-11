import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) throws IOException{
        ArrayList<Reindeer> reindeers = new ArrayList<>();
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
                newReindeer.travelTimeLeft = Integer.parseInt(m.group(3));
                newReindeer.restDuration = Integer.parseInt(m.group(4));
                reindeers.add(newReindeer);
            }
        }

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

}
