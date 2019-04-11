import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) throws IOException{
        Sue mysterySue = new Sue();
        ArrayList<Sue> sues = new ArrayList<>();

        mysterySue.things.put("children", 3);
        mysterySue.things.put("cats", 7);
        mysterySue.things.put("samoyeds", 2);
        mysterySue.things.put("pomeranians", 3);
        mysterySue.things.put("akitas", 0);
        mysterySue.things.put("vizslas", 0);
        mysterySue.things.put("goldfish", 5);
        mysterySue.things.put("trees", 3);
        mysterySue.things.put("cars", 2);
        mysterySue.things.put("perfumes", 1);

        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        Pattern pattern = Pattern.compile("(\\w+): (\\d+)");
        int sueNumber = 1;

        for (String line : lines) {
            Matcher m = pattern.matcher(line);
            Sue newSue = new Sue();
            newSue.number = sueNumber;
            while (m.find()) {
                newSue.things.put(m.group(1), Integer.parseInt(m.group(2)));
            }
            sues.add(newSue);
            sueNumber++;
        }

        for (Sue sue : sues) {
            boolean match = true;
            for (Map.Entry<String, Integer> entry : mysterySue.things.entrySet()) {
                String thing = entry.getKey();
                int amount = entry.getValue();
                if (sue.things.keySet().contains(thing)) {
                    if (sue.things.get(thing) != amount) {
                        match = false;
                        break;
                    }
                }
            }
            if (match) {
                System.out.println("Sue Number: " + sue.number);
                break;
            }
        }

        System.out.println("Done!");
    }


}
