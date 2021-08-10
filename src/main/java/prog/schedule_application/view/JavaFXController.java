package prog.schedule_application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class JavaFXController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Here is Something Professor!");
    }
}