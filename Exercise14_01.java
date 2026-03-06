import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise14_01 extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Image flag1 = new Image("flag1.gif");
        Image flag2 = new Image("flag2.gif");
        Image flag6 = new Image("flag6.gif");
        Image flag7 = new Image("flag7.gif");

        ImageView iv1 = new ImageView(flag1);
        ImageView iv2 = new ImageView(flag2);
        ImageView iv3 = new ImageView(flag6);
        ImageView iv4 = new ImageView(flag7);

        iv1.setFitWidth(100); iv1.setFitHeight(60);
        iv2.setFitWidth(100); iv2.setFitHeight(60);
        iv3.setFitWidth(100); iv3.setFitHeight(60);
        iv4.setFitWidth(100); iv4.setFitHeight(60);

        grid.add(iv1, 0, 0);
        grid.add(iv2, 1, 0);
        grid.add(iv3, 0, 1);
        grid.add(iv4, 1, 1);

        Scene scene = new Scene(grid, 220, 140);
        primaryStage.setTitle("Exercise 14.1: Four Flags");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}