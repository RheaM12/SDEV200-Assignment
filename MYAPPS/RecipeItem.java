public abstract class RecipeItem {
    private int id;
    private String name;
    private String category;
    private boolean isFavorite;

    // Constructor
    public RecipeItem(int id, String name, String category, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isFavorite = isFavorite;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    // Abstract method to display info
    public abstract void displayInfo();
}
