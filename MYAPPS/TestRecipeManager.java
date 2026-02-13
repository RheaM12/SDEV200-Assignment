import java.util.ArrayList;

public class TestRecipeManager {
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();

        // Create sample recipes
        ArrayList<String> ingredients1 = new ArrayList<>();
        ingredients1.add("Flour");
        ingredients1.add("Sugar");
        ingredients1.add("Eggs");

        Recipe r1 = new Recipe(1, "Pancakes", "Breakfast", true, ingredients1,
                "Mix ingredients and cook on skillet.", false);

        ArrayList<String> ingredients2 = new ArrayList<>();
        ingredients2.add("Tomatoes");
        ingredients2.add("Mozzarella");
        ingredients2.add("Basil");

        Recipe r2 = new Recipe(2, "Caprese Salad", "Lunch", false, ingredients2,
                "Slice ingredients and serve.", true);

        // Add recipes
        manager.addRecipe(r1);
        manager.addRecipe(r2);

        // List all recipes
        System.out.println("All Recipes:");
        for (Recipe r : manager.getAllRecipes()) {  
            r.displayInfo();
        }

        // Search by name
        System.out.println("Found Recipe:");
        ArrayList<Recipe> foundRecipes = manager.searchRecipesByName("Pancakes"); 
        for (Recipe r : foundRecipes) {
            r.displayInfo();
        }

        // Delete a recipe
        manager.deleteRecipe(1);
        manager.deleteRecipe(5);

        // List all recipes again
        System.out.println("All Recipes:");
        for (Recipe r : manager.getAllRecipes()) { 
            r.displayInfo();
        }
    }
}
