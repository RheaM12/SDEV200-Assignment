import java.util.ArrayList;

public class RecipeManager {
    private ArrayList<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
    }

    // Add a recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        System.out.println(recipe.getName() + " added successfully!");
    }

    // Delete a recipe by ID
    public void deleteRecipe(int id) {
        boolean removed = recipes.removeIf(r -> r.getId() == id);
        if (removed) {
            System.out.println("Recipe ID " + id + " removed successfully!");
        } else {
            System.out.println("Recipe ID " + id + " not found.");
        }
    }

    // Search recipe by name
    public Recipe searchByName(String name) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        System.out.println("Recipe \"" + name + "\" not found.");
        return null;
    }

    // List all recipes
    public void listAllRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes in the list.");
        } else {
            System.out.println("All Recipes:");
            for (Recipe r : recipes) {
                r.displayInfo();
            }
        }
    }
}
