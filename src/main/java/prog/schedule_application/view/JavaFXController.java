package prog.schedule_application.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import prog.schedule_application.Main;
import prog.schedule_application.controllers.Program;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JavaFXController implements Initializable {
    @FXML
    Button upload;
    @FXML
    Button home;
    @FXML
    Button log;
    @FXML
    Button addClass;
    @FXML
    Button submit;
    @FXML
    GridPane grid;

    //COURSE CODE ITEMS
    TextField txtcode;
    Button btncodeadd;
    Button btndone;
    TextArea txtcoursearea;
    ScrollBar scbar;


    public void handleBtnUpload(ActionEvent actionEvent) throws IOException {
//        Window owner = upload.getScene().getWindow();
//        Alerter.showAlert(Alert.AlertType.INFORMATION, owner, "Add Classes", "Enter Course Code for the classes you want added!");
        Parent root = FXMLLoader.load(Main.class.getResource("courseCodes.fxml"));
        Stage window = (Stage) this.upload.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();

    }

    public void handleBtnHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("homeScreen.fxml"));
        Stage window = (Stage) this.home.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    public void handleBtnSubmitCodes(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("add-Class.fxml"));
        Stage window = (Stage) this.home.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }

    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) log.getScene().getWindow();
        stage.close();
    }

    public void handleAddClassButton(ActionEvent event){
        //Retrieve text in the text box
        //Call addToUserCourses
        //Display String[] to Big Text box
        TextArea classCodeInput = new TextArea();
        String userInput = classCodeInput.getText();
        Program.addToInputCourses(userInput);
        classCodeInput.setText(Program.getInputCourses().toString());
    }

    public void handleSubmitButton(ActionEvent event){
        //Get String[] array of user classes
        //Run scheduleCreator() with the userClasses
        //Print out to the Scroll Text Box the ArrayList<Course>
        TextArea courseListOutput = new TextArea();
        courseListOutput.setText(Program.courseListCreator(1, Program.getInputCourses(), Program.buildCourses(Program.getPathName())).toString());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}