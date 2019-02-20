import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();


    public static void main(String[] args) {
        initialize();



        HashSet<ArrayList<Integer>> recipes = new HashSet<>();
        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                for (int c = 0; c <= 100; c++) {
                    for (int d = 0; d <= 100; d++) {
                        if (a + b + c + d == 100) {
                            recipes.add(new ArrayList<>(Arrays.asList(a, b, c, d)));
                        }
                    }
                }
            }
        }

        int currentMaxScore = 0;

        for (ArrayList<Integer> recipe : recipes) {
            int score = calculateScore(recipe);
            System.out.println(String.format("%s Score: %s", recipe, score));
            if (score > currentMaxScore) {
                currentMaxScore = score;
            }
        }

        System.out.println("Max Score: " + currentMaxScore);
        System.out.println("Done!");

    }

    private static int calculateScore(ArrayList<Integer> recipe) {
        int capacity = 0;
        int durability = 0;
        int flavor = 0;
        int texture = 0;

        for (int i = 0; i < ingredients.size() ; i ++) {
            Ingredient ingredient = ingredients.get(i);
            int ingredientAmount = recipe.get(i);
            capacity += ingredient.capacity * ingredientAmount;
            durability += ingredient.durability * ingredientAmount;
            flavor += ingredient.flavor * ingredientAmount;
            texture += ingredient.texture * ingredientAmount;
        }

        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0) {
            return 0;
        }

        return capacity * durability * flavor * texture;
    }

    private static void initialize() {
        // The name of the file to open.
        String fileName = "C:\\Users\\UvulaBob\\IdeaProjects\\AoC2015\\Java\\Day15\\src\\main\\resources\\input.txt";

        // This will reference one line at a time
        String line;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            Pattern pattern = Pattern.compile("^(\\w+): capacity (-?\\d), durability (-?\\d), flavor (-?\\d), texture (-?\\d), calories (-?\\d)");

            while ((line = bufferedReader.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                if (m.find()) {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.name = m.group(1);
                    newIngredient.capacity = Integer.parseInt(m.group(2));
                    newIngredient.durability = Integer.parseInt(m.group(3));
                    newIngredient.flavor = Integer.parseInt(m.group(4));
                    newIngredient.texture = Integer.parseInt(m.group(5));
                    newIngredient.calories = Integer.parseInt(m.group(6));
                    ingredients.add(newIngredient);
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
