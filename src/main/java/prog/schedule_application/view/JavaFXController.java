package prog.schedule_application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXController {
    @FXML
    Button upload;
    @FXML
    Button home;

    public void handleBtnUpload(ActionEvent actionEvent) throws IOException {


        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("UploadScreen.fxml"));
        Stage window = (Stage) this.upload.getScene().getWindow();
        window.setScene(new Scene(root, 320, 240));
        window.show();

    }

    public void handleBtnHome(ActionEvent actionEvent) throws IOException {


        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("homeScreen.fxml"));
        Stage window = (Stage) this.home.getScene().getWindow();
        window.setScene(new Scene(root, 320, 240));
        window.show();

    }


}