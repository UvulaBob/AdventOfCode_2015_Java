import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));
        ArrayList<String> instructions = new ArrayList<>(lines);

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

}
