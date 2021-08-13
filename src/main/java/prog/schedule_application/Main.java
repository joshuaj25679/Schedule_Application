package prog.schedule_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;
import prog.schedule_application.controllers.Program;
import prog.schedule_application.models.Course;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String[] testUserCourse = new String[]{"CSC180", "ENG110", "MAT150", "DBT230", "PRO100"};
        ArrayList<Course> temp = Program.scheduleCreator(testUserCourse, Program.buildCourses(pathName));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Something!");
        stage.setScene(scene);
        stage.show();
    }

    private static String pathName = "src/main/files/test.pdf";

    public static void main(String[] args) {
//        String[] testUserCourse = new String[]{"CSC180", "ENG110", "MAT150", "DBT230", "PRO100"};
//        Program.scheduleCreator(testUserCourse, Program.buildCourses(pathName));
        launch();
    }

}

