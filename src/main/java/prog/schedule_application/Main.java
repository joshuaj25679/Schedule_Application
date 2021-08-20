package prog.schedule_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import org.controlsfx.control.spreadsheet.Grid;
import prog.schedule_application.controllers.Program;
import prog.schedule_application.models.Course;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    @FXML
    static TextArea filePath;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fileExplorer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Schedule Application");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
//        ArrayList<String> testUserCourse = new ArrayList<>();
//        testUserCourse.add("CSC180");
//        testUserCourse.add("ENG110");
//        testUserCourse.add("MAT150");
//        testUserCourse.add("DBT230");
//        testUserCourse.add("PRO100");
            Program.buildCourses(Program.getPathName());
            launch();
//        Program.courseListCreator(1, testUserCourse, Program.buildCourses(String.valueOf(filePath)));
//        Program.courseListCreator(2, testUserCourse, Program.buildCourses(String.valueOf(filePath)));
//        Program.courseListCreator(3, testUserCourse, Program.buildCourses(String.valueOf(filePath)));
    }

}

