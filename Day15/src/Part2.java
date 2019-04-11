import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\input.txt"));

        Pattern pattern = Pattern.compile("^(\\w+): capacity (-?\\d), durability (-?\\d), flavor (-?\\d), texture (-?\\d), calories (-?\\d)");

        for (String line : lines) {
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
        int calories = 0;

        for (int i = 0; i < ingredients.size() ; i ++) {
            Ingredient ingredient = ingredients.get(i);
            int ingredientAmount = recipe.get(i);
            capacity += ingredient.capacity * ingredientAmount;
            durability += ingredient.durability * ingredientAmount;
            flavor += ingredient.flavor * ingredientAmount;
            texture += ingredient.texture * ingredientAmount;
            calories += ingredient.calories * ingredientAmount;
        }

        if (capacity < 0 || durability < 0 || flavor < 0 || texture < 0 || calories < 0) {
            return 0;
        }

        if (calories == 500) {
            return capacity * durability * flavor * texture;
        }

        return 0;
    }
}
