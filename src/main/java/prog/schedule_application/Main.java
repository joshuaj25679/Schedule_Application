package prog.schedule_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;
import prog.schedule_application.controllers.Program;
import prog.schedule_application.models.Course;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Something!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        ArrayList<String> testUserCourse = new ArrayList<>();
        testUserCourse.add("CSC180");
        testUserCourse.add("ENG110");
        testUserCourse.add("MAT150");
        testUserCourse.add("DBT230");
        testUserCourse.add("PRO100");
        Program.courseListCreator(testUserCourse, Program.buildCourses("src/main/files/test.pdf"));
        //launch();
    }

}

