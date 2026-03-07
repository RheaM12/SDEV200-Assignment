
//This program will change color when the mouse is pressed


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ColorMouse extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Change Circle Color with Mouse");

        Circle circle = new Circle(50); // radius 50
        circle.setFill(Color.WHITE);     // initial color

        // Event: mouse pressed → black
        circle.setOnMousePressed(e -> circle.setFill(Color.BLACK));

        // Event: mouse released → white
        circle.setOnMouseReleased(e -> circle.setFill(Color.WHITE));

        StackPane root = new StackPane(circle);
        Scene scene = new Scene(root, 300, 300);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
