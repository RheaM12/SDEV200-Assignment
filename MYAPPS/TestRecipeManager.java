import java.util.ArrayList;

public class TestRecipeManager {
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();

        // Create recipe 1
        ArrayList<String> ingredients1 = new ArrayList<>();
        ingredients1.add("Flour");
        ingredients1.add("Sugar");
        ingredients1.add("Eggs");
        Recipe pancake = new Recipe(1, "Pancakes", "Breakfast", true,
                ingredients1, "Mix ingredients and cook on skillet.", false);

        // Create recipe 2
        ArrayList<String> ingredients2 = new ArrayList<>();
        ingredients2.add("Tomatoes");
        ingredients2.add("Mozzarella");
        ingredients2.add("Basil");
        Recipe caprese = new Recipe(2, "Caprese Salad", "Lunch", false,
                ingredients2, "Slice ingredients and serve.", true);

        // Add recipes
        manager.addRecipe(pancake);
        manager.addRecipe(caprese);

        // List all recipes
        manager.listAllRecipes();

        // Search for a recipe
        Recipe found = manager.searchByName("Pancakes");
        if (found != null) {
            System.out.println("Found Recipe:");
            found.displayInfo();
        }

        // Delete a recipe
        manager.deleteRecipe(1);

        // Try to delete a non-existent recipe
        manager.deleteRecipe(5);

        // List all recipes after deletion
        manager.listAllRecipes();
    }
}
