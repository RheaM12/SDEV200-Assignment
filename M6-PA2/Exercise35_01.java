

import java.sql.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise35_01_BatchUpdate extends Application {

    Connection conn; // Database connection

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Batch Update Demo");

        // Buttons for actions
        Button btnConnect = new Button("Connect to Database");
        Button btnNonBatch = new Button("Non-Batch Update (1000 records)");
        Button btnBatch = new Button("Batch Update (1000 records)");
        Label lblStatus = new Label();

        // Connect to database
        btnConnect.setOnAction(e -> {
            conn = DBConnectionPanel.showDialog(stage); // Opens dialog for connection
            lblStatus.setText((conn != null) ? "Connected!" : "Connection failed.");
        });

        // Non-batch insert
        btnNonBatch.setOnAction(e -> {
            if (conn != null) {
                long start = System.currentTimeMillis();
                insertNonBatch();
                long end = System.currentTimeMillis();
                lblStatus.setText("Non-Batch Update completed. Elapsed time: " + (end - start) + " ms");
            }
        });

        // Batch insert
        btnBatch.setOnAction(e -> {
            if (conn != null) {
                long start = System.currentTimeMillis();
                insertBatch();
                long end = System.currentTimeMillis();
                lblStatus.setText("Batch Update completed. Elapsed time: " + (end - start) + " ms");
            }
        });

        VBox root = new VBox(10, btnConnect, btnNonBatch, btnBatch, lblStatus);
        root.setStyle("-fx-padding: 15;");
        stage.setScene(new Scene(root, 400, 200));
        stage.show();
    }

    // Inserts 1000 records one by one
    void insertNonBatch() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Temp"); // Clear table
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
            for (int i = 0; i < 1000; i++) {
                ps.setDouble(1, Math.random());
                ps.setDouble(2, Math.random());
                ps.setDouble(3, Math.random());
                ps.executeUpdate(); // Non-batch
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Inserts 1000 records using batch
    void insertBatch() {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Temp"); // Clear table
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
            for (int i = 0; i < 1000; i++) {
                ps.setDouble(1, Math.random());
                ps.setDouble(2, Math.random());
                ps.setDouble(3, Math.random());
                ps.addBatch(); // Add to batch
            }
            ps.executeBatch(); // Execute all at once
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// helper class: DBConnectionPanel

class DBConnectionPanel {

    public static Connection showDialog(Stage parent) {
        Dialog<Connection> dialog = new Dialog<>();
        dialog.initOwner(parent);
        dialog.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        dialog.setTitle("Connect to Database");

        VBox grid = new VBox(10);

        TextField tfURL = new TextField("jdbc:mysql://localhost:3306/yourDB");
        TextField tfUser = new TextField("root");
        PasswordField pfPassword = new PasswordField();

        grid.getChildren().addAll(
                new Label("Database URL:"), tfURL,
                new Label("Username:"), tfUser,
                new Label("Password:"), pfPassword
        );

        dialog.getDialogPane().setContent(grid);

        ButtonType btnConnect = new ButtonType("Connect", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnConnect, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnConnect) {
                try {
                    return DriverManager.getConnection(tfURL.getText(), tfUser.getText(), pfPassword.getText());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Connection Failed!");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}


