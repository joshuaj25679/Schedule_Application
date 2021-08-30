package prog.schedule_application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import org.controlsfx.control.spreadsheet.Grid;
import prog.schedule_application.controllers.PDFtoTXT;
import prog.schedule_application.controllers.Program;
import prog.schedule_application.models.Course;
import prog.schedule_application.view.Alerter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    @FXML
    static TextArea filePath;
    @Override
    public void start(Stage stage) throws IOException {
        Alert a = Alerter.createAlert(Alert.AlertType.INFORMATION, "Select a valid PDF from Neumont Important Documents so you can begin adding courses!");
        a.setHeaderText("Add PDF");
        a.setTitle("Instructions");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fileExplorer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Schedule Application");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        a.show();
    }

    public static void main(String[] args) {
        launch();
        //Program.getUserCourseList().clear();
    }
}