import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static Sue mysterySue = new Sue();
    private static ArrayList<Sue> sues = new ArrayList<Sue>();

    public static void main(String[] args) {
        initialize();

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


    private static void initialize() {
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

        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day16\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            Pattern pattern = Pattern.compile("(\\w+): (\\d+)");
            int sueNumber = 1;

            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                Sue newSue = new Sue();
                newSue.number = sueNumber;
                while (m.find()) {
                    newSue.things.put(m.group(1), Integer.parseInt(m.group(2)));
                }
                sues.add(newSue);
                sueNumber++;
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
