import java.util.ArrayList;

class RecipeManager {
    private ArrayList<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
    }

    // Add a recipe
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        System.out.println(recipe.getName() + " added!");
    }

    // Edit a recipe by ID
    public boolean editRecipe(int id, Recipe newRecipe) {
        Recipe existing = getRecipeById(id);
        if (existing != null) {
            existing.setName(newRecipe.getName());
            existing.setCategory(newRecipe.getCategory());
            existing.setFavorite(newRecipe.isFavorite());
            existing.setIngredients(newRecipe.getIngredients());
            existing.setInstructions(newRecipe.getInstructions());
            existing.setHasBeenCooked(newRecipe.isHasBeenCooked());
            System.out.println("Recipe ID " + id + " updated!");
            return true;
        } else {
            System.out.println("Recipe ID " + id + " not found.");
            return false;
        }
    }

    // Delete a recipe by ID
    public boolean deleteRecipe(int id) {
        boolean removed = recipes.removeIf(r -> r.getId() == id);
        if (removed) System.out.println("Recipe ID " + id + " deleted!");
        else System.out.println("Recipe ID " + id + " not found.");
        return removed;
    }

    // Get a recipe by ID
    public Recipe getRecipeById(int id) {
        for (Recipe r : recipes) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    // Search recipes by name
    public ArrayList<Recipe> searchRecipesByName(String name) {
        ArrayList<Recipe> results = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(r);
            }
        }
        return results;
    }

    // Get all favorite recipes
    public ArrayList<Recipe> getFavorites() {
        ArrayList<Recipe> favorites = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.isFavorite()) favorites.add(r);
        }
        return favorites;
    }

    // Get recipes by category
    public ArrayList<Recipe> getRecipesByCategory(String category) {
        ArrayList<Recipe> catRecipes = new ArrayList<>();
        for (Recipe r : recipes) {
            if (r.getCategory().equalsIgnoreCase(category)) {
                catRecipes.add(r);
            }
        }
        return catRecipes;
    }

    // Get all recipes
    public ArrayList<Recipe> getAllRecipes() {
        return recipes;
    }
}

