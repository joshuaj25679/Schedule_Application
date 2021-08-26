package prog.schedule_application.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Window;

public class Alerter {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static Alert returnAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
        return alert;
    }

    public static Alert createAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);

        StringBuilder sb = new StringBuilder(message);
        for (int i = 0; i < message.length(); i += 72) {
            sb.insert(i, "\n");
        }

        Label t = new Label(sb.toString());
        alert.getDialogPane().setContent(t);
//        alert.getDialogPane().setPrefSize(500, 150);
        return alert;
    }
}
