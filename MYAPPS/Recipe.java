import java.util.ArrayList;

public class Recipe extends RecipeItem {
    private ArrayList<String> ingredients;
    private String instructions;
    private boolean hasBeenCooked;

    // Constructor
    public Recipe(int id, String name, String category, boolean isFavorite,
                  ArrayList<String> ingredients, String instructions, boolean hasBeenCooked) {
        super(id, name, category, isFavorite);
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.hasBeenCooked = hasBeenCooked;
    }

    // Getters and Setters
    public ArrayList<String> getIngredients() { return ingredients; }
    public void setIngredients(ArrayList<String> ingredients) { this.ingredients = ingredients; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public boolean isHasBeenCooked() { return hasBeenCooked; }
    public void setHasBeenCooked(boolean hasBeenCooked) { this.hasBeenCooked = hasBeenCooked; }

    // Mark recipe as cooked
    public void markAsCooked() {
        this.hasBeenCooked = true;
    }

    // Display all recipe info
    @Override
    public void displayInfo() {
        System.out.println("Recipe ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Category: " + getCategory());
        System.out.println("Favorite: " + (isFavorite() ? "Yes" : "No"));
        System.out.println("Ingredients:");
        for (String ingredient : ingredients) {
            System.out.println("- " + ingredient);
        }
        System.out.println("Instructions: " + instructions);
        System.out.println("Has been cooked: " + (hasBeenCooked ? "Yes" : "No"));
        System.out.println("-------------------------------------");
    }
}
