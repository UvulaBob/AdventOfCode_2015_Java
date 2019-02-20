import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Part1 {
    private static ArrayList<String> instructions = new ArrayList<>();

    public static void main(String[] args) {
        instructions.add("Nothing");
        initialize();
        int a = 0;
        int b = 0;

        for (int i = 1; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            String operation = instruction.split(" ")[0];
            switch (operation) {
                case "hlf":
                    String register = instruction.split(" ")[1];
                    if (register.equals("a")) {
                        a /= 2;
                    } else {
                        b /= 2;
                    }
                    break;
                case "tpl":
                    register = instruction.split(" ")[1];
                    if (register.equals("a")) {
                        a *= 3;
                    } else {
                        b *= 3;
                    }
                    break;
                case "jmp":
                    String offset = instruction.split(" ")[1];
                    String direction = offset.split("")[0];
                    int amount = Integer.parseInt(offset.replace(direction, ""));
                    if (direction.equals("-")) {
                        amount *= -1;
                    }
                    i += (amount - 1);
                    break;
                case "jie":
                    register = instruction.split(", ")[0].split(" ")[1];
                    offset = instruction.split(", ")[1];
                    direction = offset.split("")[0];
                    amount = Integer.parseInt(offset.split("")[1]);
                    if (direction.equals("-")) {
                        amount *= -1;
                    }
                    if (register.equals("a")) {
                        if (a % 2 == 0) {
                            i += (amount - 1);
                        }
                    } else {
                        if (b % 2 == 0) {
                            i += (amount - 1);
                        }
                    }
                    break;
                case "jio":
                    register = instruction.split(", ")[0].split(" ")[1];
                    offset = instruction.split(", ")[1];
                    direction = offset.split("")[0];
                    amount = Integer.parseInt(offset.split("")[1]);
                    if (direction.equals("-")) {
                        amount *= -1;
                    }
                    if (register.equals("a")) {
                        if (a == 1) {
                            i += (amount - 1);
                        }
                    } else {
                        if (b == 1) {
                            i += (amount - 1);
                        }
                    }
                    break;
                default:
                    register = instruction.split(" ")[1];
                    if (register.equals("a")) {
                        a++;
                    } else {
                        b++;
                    }
            }

        }

        System.out.println("Register a: " + a);
        System.out.println("Register b: " + b);
        System.out.println("Done!");



    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\workspace\\AoC2015\\Day23\\src\\main\\resources\\input.txt";

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
                instructions.add(line);
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
