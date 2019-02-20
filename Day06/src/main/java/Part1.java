import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static ArrayList<Instruction> instructions = new ArrayList<>();
    private static boolean[][] grid = new boolean[1000][1000];

    public static void main(String[] args) {
        initialize();

        for (Instruction instruction : instructions) {
            switch (instruction.operation) {
                case on:
                    turnOnLights(instruction.startPoint, instruction.endPoint);
                    break;
                case off:
                    turnOffLights(instruction.startPoint, instruction.endPoint);
                    break;
                case toggle:
                    toggleLights(instruction.startPoint, instruction.endPoint);
                    break;
            }
        }

        int numberOfLights = 0;
        for (int x = 0 ; x < 1000; x++){
            for (int y = 0 ; y < 1000; y++){
                if (grid[x][y]) {
                    numberOfLights++;
                }
            }
        }
        System.out.println("Done!");
        System.out.println("Number of Lights: " + numberOfLights);
    }

    private static void turnOnLights(String startPoint, String endPoint) {
        int startX = Integer.parseInt(startPoint.split(",")[0]);
        int startY = Integer.parseInt(startPoint.split(",")[1]);
        int endX = Integer.parseInt(endPoint.split(",")[0]);
        int endY = Integer.parseInt(endPoint.split(",")[1]);

        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                grid[x][y] = true;
            }
        }
    }

    private static void turnOffLights(String startPoint, String endPoint) {
        int startX = Integer.parseInt(startPoint.split(",")[0]);
        int startY = Integer.parseInt(startPoint.split(",")[1]);
        int endX = Integer.parseInt(endPoint.split(",")[0]);
        int endY = Integer.parseInt(endPoint.split(",")[1]);

        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                grid[x][y] = false;
            }
        }
    }

    private static void toggleLights(String startPoint, String endPoint) {
        int startX = Integer.parseInt(startPoint.split(",")[0]);
        int startY = Integer.parseInt(startPoint.split(",")[1]);
        int endX = Integer.parseInt(endPoint.split(",")[0]);
        int endY = Integer.parseInt(endPoint.split(",")[1]);

        for (int x = startX; x <= endX; x++){
            for (int y = startY; y <= endY; y++){
                grid[x][y] = !grid[x][y];
            }
        }
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day6\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                Pattern pattern = Pattern.compile("(toggle|turn off|turn on) ([0-9]+,[0-9]+) through ([0-9]+,[0-9]+)");

                Matcher m = pattern.matcher(line);

                while (m.find()) {
                    String operation = m.group(1);
                    String startPoint = m.group(2);
                    String endPoint = m.group(3);

                    Instruction newInstruction = new Instruction();
                    switch (operation) {
                        case "toggle":
                            newInstruction.operation = Operation.toggle;
                            break;
                        case "turn on":
                            newInstruction.operation = Operation.on;
                            break;
                        case "turn off":
                            newInstruction.operation = Operation.off;
                            break;
                    }

                    newInstruction.startPoint = startPoint;
                    newInstruction.endPoint = endPoint;

                    instructions.add(newInstruction);
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
