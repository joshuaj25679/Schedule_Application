package prog.schedule_application.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import prog.schedule_application.Main;
import prog.schedule_application.controllers.Program;
import javafx.stage.FileChooser;
import prog.schedule_application.models.Course;

import java.io.File;
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
    Button addAClass;
    @FXML
    Button submit;
    @FXML
    Button browse;
    @FXML
    public ListView listview;
    @FXML
    Button submitToHome;
    @FXML
    TextArea filePath;

    public void handleBtnSubmitHome(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("homeScreen.fxml"));
        if(filePath.getText().contains(".pdf")){
            Program.setPathName(filePath.getText());
        }
        System.out.println(Program.getPathName());
        Program.setCourseList(Program.buildCourses(Program.getPathName()));
        Stage window = (Stage) this.submitToHome.getScene().getWindow();
        window.setScene(new Scene(root));
        window.show();
    }


    //COURSE CODE ITEMS
    @FXML
    TextField txtcode;
    @FXML
    Button btncodeadd;
    @FXML
    Button btndone;
    @FXML
    TextArea txtcoursearea;
    @FXML
    ScrollBar scbar;
    @FXML
    ImageView img;

    //ADD COURSE ITEMS
//    @FXML
//    TextArea selectedClass;
    @FXML
    TextArea classList;
    @FXML
    Button classSelection;
    @FXML
    Button btnTest;
    @FXML
    ComboBox<Course> comboBox;

    public void handleBtnUpload(ActionEvent actionEvent) throws IOException {

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
        Window owner = home.getScene().getWindow();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Continue?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES){
            Parent root = FXMLLoader.load(Main.class.getResource("add-Class.fxml"));
            Stage window = (Stage) this.home.getScene().getWindow();
            window.setScene(new Scene(root, 800, 600));
            window.show();
            Alerter.showAlert(Alert.AlertType.INFORMATION, owner, "Add Classes", "Select the button called Classes to begin." +
                    " Once button is pressed your desired classes will appear in the drop down menu and then you will be able to make your selections of the available times and sections. Select the class and press the add button " +
                    "and class(s) will appear in the middle of the screen.");
        }

    }

    public void onClickRunSetTextTest(ActionEvent actionEvent){
//        selectedClass.setText("Hello");
//        classList.setText("Hello");
        if (comboBox.getItems().isEmpty()){
            ObservableList<Course> classes = FXCollections.observableArrayList(Program.courseListCreator(3, Program.getInputCourses(), Program.buildCourses("src/main/files/test.pdf")));
            comboBox.getItems().addAll(classes);
        }
//        selectedClass.setText(Program.printCourseList());
    }

    public void handleBtnAddCodes(ActionEvent actionEvent){
        String code = txtcode.getText();
        txtcode.clear();
        Program.addToInputCourses(code);
        txtcoursearea.setText(Program.getInputCourses().toString());
    }

    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) log.getScene().getWindow();
        stage.close();
    }

    public void handleFEButton(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
       File selectedFile = fileChooser.showOpenDialog(null);
        //filePath.setText((filePath.getText()));
        if (selectedFile != null) {
            filePath.setText((selectedFile.getAbsolutePath()));
        }else {
            System.out.println("File is not valid!");
        }

    }



    public void handleSubmitButton(ActionEvent event){
        //Get inputCourse
        //run courseListCreator for the needed course



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}