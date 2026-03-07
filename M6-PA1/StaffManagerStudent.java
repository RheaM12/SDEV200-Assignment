
//This program allows the user to
 //View a staff record by ID.
 //Insert a new staff record.
 //Update an existing staff record.



import java.sql.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StaffManagerStudent extends Application {

    // Database connection info
    String DB_URL = "jdbc:mysql://localhost:3306/yourDB";
    String DB_USER = "root";
    String DB_PASSWORD = "password";

    // GUI fields
    TextField tfId = new TextField();
    TextField tfLastName = new TextField();
    TextField tfFirstName = new TextField();
    TextField tfMi = new TextField();
    TextField tfAddress = new TextField();
    TextField tfCity = new TextField();
    TextField tfState = new TextField();
    TextField tfTelephone = new TextField();
    TextField tfEmail = new TextField();
    Label lblStatus = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Staff Manager");

        GridPane grid = new GridPane();

        // Add labels and text fields
        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(tfLastName, 1, 1);
        grid.add(new Label("First Name:"), 0, 2);
        grid.add(tfFirstName, 1, 2);
        grid.add(new Label("MI:"), 0, 3);
        grid.add(tfMi, 1, 3);
        grid.add(new Label("Address:"), 0, 4);
        grid.add(tfAddress, 1, 4);
        grid.add(new Label("City:"), 0, 5);
        grid.add(tfCity, 1, 5);
        grid.add(new Label("State:"), 0, 6);
        grid.add(tfState, 1, 6);
        grid.add(new Label("Telephone:"), 0, 7);
        grid.add(tfTelephone, 1, 7);
        grid.add(new Label("Email:"), 0, 8);
        grid.add(tfEmail, 1, 8);

        // Buttons
        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        grid.add(btnView, 0, 9);
        grid.add(btnInsert, 1, 9);
        grid.add(btnUpdate, 2, 9);
        grid.add(lblStatus, 0, 10, 3, 1);

        // Button actions
        btnView.setOnAction(e -> performAction("view"));
        btnInsert.setOnAction(e -> performAction("insert"));
        btnUpdate.setOnAction(e -> performAction("update"));

        stage.setScene(new Scene(grid, 500, 400));
        stage.show();
    }

    // One simple method for all actions
    void performAction(String action) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = null;

            if (action.equals("view")) {
                stmt = conn.prepareStatement("SELECT * FROM Staff WHERE id=?");
                stmt.setString(1, tfId.getText());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    tfLastName.setText(rs.getString("lastName"));
                    tfFirstName.setText(rs.getString("firstName"));
                    tfMi.setText(rs.getString("mi"));
                    tfAddress.setText(rs.getString("address"));
                    tfCity.setText(rs.getString("city"));
                    tfState.setText(rs.getString("state"));
                    tfTelephone.setText(rs.getString("telephone"));
                    tfEmail.setText(rs.getString("email"));
                    lblStatus.setText("Record found.");
                } else {
                    lblStatus.setText("No record with this ID.");
                }

            } else if (action.equals("insert")) {
                stmt = conn.prepareStatement(
                        "INSERT INTO Staff VALUES(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, tfId.getText());
                stmt.setString(2, tfLastName.getText());
                stmt.setString(3, tfFirstName.getText());
                stmt.setString(4, tfMi.getText());
                stmt.setString(5, tfAddress.getText());
                stmt.setString(6, tfCity.getText());
                stmt.setString(7, tfState.getText());
                stmt.setString(8, tfTelephone.getText());
                stmt.setString(9, tfEmail.getText());
                stmt.executeUpdate();
                lblStatus.setText("Record inserted.");

            } else if (action.equals("update")) {
                stmt = conn.prepareStatement(
                        "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?");
                stmt.setString(1, tfLastName.getText());
                stmt.setString(2, tfFirstName.getText());
                stmt.setString(3, tfMi.getText());
                stmt.setString(4, tfAddress.getText());
                stmt.setString(5, tfCity.getText());
                stmt.setString(6, tfState.getText());
                stmt.setString(7, tfTelephone.getText());
                stmt.setString(8, tfEmail.getText());
                stmt.setString(9, tfId.getText());
                stmt.executeUpdate();
                lblStatus.setText("Record updated.");
            }

            conn.close();
        } catch (Exception e) {
            lblStatus.setText("Error occurred.");
        }
    }
}