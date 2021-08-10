package prog.schedule_application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prog.schedule_application.controllers.PDFtoTXT;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-prog.schedule_application.view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Something!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        PDFtoTXT a = new PDFtoTXT();
        //launch();
        a.test("src/main/files/test.pdf");
    }
}