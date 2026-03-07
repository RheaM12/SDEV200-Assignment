//Program that allow users to set that time and it will display the analog clock 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ClockWithSetTime extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Set Clock Time");

        // Create ClockPane
        ClockPane clock = new ClockPane();

        // Text fields for user input
        TextField tfHour = new TextField();
        TextField tfMinute = new TextField();
        TextField tfSecond = new TextField();
        tfHour.setPrefColumnCount(2);
        tfMinute.setPrefColumnCount(2);
        tfSecond.setPrefColumnCount(2);

        Button btnSetTime = new Button("Set Time");

    
        HBox inputBox = new HBox(10);
        inputBox.getChildren().addAll(
                new Label("Hour:"), tfHour,
                new Label("Minute:"), tfMinute,
                new Label("Second:"), tfSecond,
                btnSetTime
        );

        VBox root = new VBox(10, clock, inputBox);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // set time in clock
        btnSetTime.setOnAction(e -> {
            try {
                int hour = Integer.parseInt(tfHour.getText());
                int minute = Integer.parseInt(tfMinute.getText());
                int second = Integer.parseInt(tfSecond.getText());

                clock.setHour(hour);
                clock.setMinute(minute);
                clock.setSecond(second);

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter valid numbers.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//Helper class: ClockPane

class ClockPane extends Pane {

    private int hour = 12;
    private int minute = 0;
    private int second = 0;
    private double w = 200, h = 200;

    public ClockPane() {
        paintClock();
    }

    public void setHour(int hour) {
        this.hour = hour % 24;
        paintClock();
    }

    public void setMinute(int minute) {
        this.minute = minute % 60;
        paintClock();
    }

    public void setSecond(int second) {
        this.second = second % 60;
        paintClock();
    }

    private void paintClock() {
        getChildren().clear();

        double clockRadius = Math.min(w, h) * 0.4;
        double centerX = w / 2;
        double centerY = h / 2;

        // Clock face
        Circle circle = new Circle(centerX, centerY, clockRadius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().add(circle);

        // Hour hand
        double hourLength = clockRadius * 0.5;
        double hourAngle = (hour % 12 + minute / 60.0) * 30;
        Line hourHand = new Line(centerX, centerY,
                centerX + hourLength * Math.sin(Math.toRadians(hourAngle)),
                centerY - hourLength * Math.cos(Math.toRadians(hourAngle)));
        hourHand.setStroke(Color.BLACK);
        hourHand.setStrokeWidth(3);
        getChildren().add(hourHand);

        // Minute hand
        double minuteLength = clockRadius * 0.7;
        double minuteAngle = (minute + second / 60.0) * 6;
        Line minuteHand = new Line(centerX, centerY,
                centerX + minuteLength * Math.sin(Math.toRadians(minuteAngle)),
                centerY - minuteLength * Math.cos(Math.toRadians(minuteAngle)));
        minuteHand.setStroke(Color.BLUE);
        minuteHand.setStrokeWidth(2);
        getChildren().add(minuteHand);

        // Second hand
        double secondLength = clockRadius * 0.9;
        double secondAngle = second * 6;
        Line secondHand = new Line(centerX, centerY,
                centerX + secondLength * Math.sin(Math.toRadians(secondAngle)),
                centerY - secondLength * Math.cos(Math.toRadians(secondAngle)));
        secondHand.setStroke(Color.RED);
        getChildren().add(secondHand);
    }

    @Override
    public double getWidth() { return w; }
    @Override
    public double getHeight() { return h; }
}


