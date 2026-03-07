import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;

public class KitchenApp extends Application {

    private RecipeManager manager = new RecipeManager();  
    private VBox recipeListVBox = new VBox(10);          

    @Override
    public void start(Stage stage) {

        // My introduction screen //header
        Label title = new Label("My Kitchen");
        Label subtitle = new Label("Curate your personal cookbook");
        VBox titleBox = new VBox(title, subtitle);
        titleBox.setSpacing(5);

        // ADD RECIPE BUTTON 
        Button addRecipeButton = new Button("Add Recipe");

        addRecipeButton.setOnAction(e -> openAddRecipeWindow());

        // SEARCH Button 
        TextField searchField = new TextField();
        searchField.setPromptText("Search recipes or ingredients...");
        Button searchButton = new Button("Search");

        searchButton.setOnAction(e -> {
            String searchTerm = searchField.getText();
            recipeListVBox.getChildren().clear();
            ArrayList<Recipe> results = manager.searchRecipesByName(searchTerm);
            if (results.isEmpty()) {
                recipeListVBox.getChildren().add(new Label("No recipes found."));
            } else {
                for (Recipe r : results) {
                    recipeListVBox.getChildren().add(createRecipeCard(r));
                }
            }
        });

        HBox searchBox = new HBox(10, searchField, searchButton);

        // CATEGORY FILTER BUTTONS 
        Button allButton = new Button("All Recipes");
        Button breakfastButton = new Button("Breakfast");
        Button lunchButton = new Button("Lunch");
        Button dinnerButton = new Button("Dinner");

        allButton.setOnAction(e -> refreshRecipeList(manager.getAllRecipes()));
        breakfastButton.setOnAction(e -> refreshRecipeList(manager.getRecipesByCategory("Breakfast")));
        lunchButton.setOnAction(e -> refreshRecipeList(manager.getRecipesByCategory("Lunch")));
        dinnerButton.setOnAction(e -> refreshRecipeList(manager.getRecipesByCategory("Dinner")));

        HBox categoryBox = new HBox(10, allButton, breakfastButton, lunchButton, dinnerButton);

        //  MAIN LAYOUT 
        VBox mainLayout = new VBox(15, titleBox, addRecipeButton, searchBox, categoryBox,
                new Label("Recipes:"), recipeListVBox);
        mainLayout.setPadding(new Insets(15));

        // INITIALIZE WITH ALL RECIPES 
        refreshRecipeList(manager.getAllRecipes());

        // SHOW SCENE 
        Scene scene = new Scene(mainLayout, 600, 500);
        stage.setTitle("My Kitchen");
        stage.setScene(scene);
        stage.show();
    }

    // CREATE A RECIPE CARD 
    private HBox createRecipeCard(Recipe r) {
        VBox info = new VBox(5);
        info.getChildren().addAll(
                new Label("Name: " + r.getName()),
                new Label("Ingredients: " + String.join(", ", r.getIngredients())),
                new Label("Category: " + r.getCategory())
        );

        Button editButton = new Button("Edit");
        Button removeButton = new Button("Remove");

        editButton.setOnAction(e -> openEditRecipeWindow(r));
        removeButton.setOnAction(e -> {
            manager.deleteRecipe(r.getId());
            refreshRecipeList(manager.getAllRecipes());
        });

        HBox card = new HBox(10, info, editButton, removeButton);
        card.setPadding(new Insets(5));
        card.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-padding: 5;");
        return card;
    }

    // REFRESH RECIPE LIST
    private void refreshRecipeList(ArrayList<Recipe> list) {
        recipeListVBox.getChildren().clear();
        for (Recipe r : list) {
            recipeListVBox.getChildren().add(createRecipeCard(r));
        }
    }

    // ADD RECIPE WINDOW 
    private void openAddRecipeWindow() {
        Stage window = new Stage();

        TextField nameField = new TextField();
        TextField ingredientsField = new TextField();

        ComboBox<String> categoryCombo = new ComboBox<>();
        categoryCombo.getItems().addAll("Breakfast", "Lunch", "Dinner");

        CheckBox favoriteBox = new CheckBox("Favorite");

        Button confirmButton = new Button("Confirm / Add");
        Button cancelButton = new Button("Cancel");

        confirmButton.setOnAction(e -> {
            String name = nameField.getText();
            ArrayList<String> ingredients = new ArrayList<>();
            for (String s : ingredientsField.getText().split(",")) {
                ingredients.add(s.trim());
            }
            String category = categoryCombo.getValue();
            boolean favorite = favoriteBox.isSelected();

            // Generate ID based on existing recipes
            int id = manager.getAllRecipes().size() + 1;

            Recipe newRecipe = new Recipe(id, name, category, favorite, ingredients,
                    "Instructions not set", false);

            manager.addRecipe(newRecipe);
            refreshRecipeList(manager.getAllRecipes());
            window.close();
        });

        cancelButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10,
                new Label("Add New Recipe"),
                new Label("Name:"), nameField,
                new Label("Ingredients (comma separated):"), ingredientsField,
                new Label("Category:"), categoryCombo,
                favoriteBox,
                confirmButton, cancelButton
        );
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 350);
        window.setScene(scene);
        window.setTitle("Add Recipe");
        window.show();
    }

    // EDIT RECIPE WINDOW 
    private void openEditRecipeWindow(Recipe r) {
        Stage window = new Stage();

        TextField nameField = new TextField(r.getName());
        TextField ingredientsField = new TextField(String.join(", ", r.getIngredients()));

        ComboBox<String> categoryCombo = new ComboBox<>();
        categoryCombo.getItems().addAll("Breakfast", "Lunch", "Dinner");
        categoryCombo.setValue(r.getCategory());

        CheckBox favoriteBox = new CheckBox("Favorite");
        favoriteBox.setSelected(r.isFavorite());

        Button confirmButton = new Button("Confirm / Edit");
        Button cancelButton = new Button("Cancel");

        confirmButton.setOnAction(e -> {
            r.setName(nameField.getText());
            ArrayList<String> ingredients = new ArrayList<>();
            for (String s : ingredientsField.getText().split(",")) {
                ingredients.add(s.trim());
            }
            r.setIngredients(ingredients);
            r.setCategory(categoryCombo.getValue());
            r.setFavorite(favoriteBox.isSelected());

            refreshRecipeList(manager.getAllRecipes());
            window.close();
        });

        cancelButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10,
                new Label("Edit Recipe"),
                new Label("Name:"), nameField,
                new Label("Ingredients (comma separated):"), ingredientsField,
                new Label("Category:"), categoryCombo,
                favoriteBox,
                confirmButton, cancelButton
        );
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 300, 350);
        window.setScene(scene);
        window.setTitle("Edit Recipe");
        window.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

