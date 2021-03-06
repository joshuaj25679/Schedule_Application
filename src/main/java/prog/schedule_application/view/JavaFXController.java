package prog.schedule_application.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAbstractAppearanceHandler;
import org.controlsfx.control.textfield.TextFields;
import prog.schedule_application.Main;
import prog.schedule_application.controllers.PDFtoTXT;
import prog.schedule_application.controllers.Program;
import javafx.stage.FileChooser;
import prog.schedule_application.models.Course;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    TextField filePath;
    @FXML
    Label lblStatus;
    @FXML
    Button btnFile;
    @FXML
    Text txtNotValid;
    @FXML
    Button btnDisplaySchedule;
    @FXML
    TextArea txtSprintOne;
    @FXML
    TextArea txtSprintTwo;

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
    Button btnClasses;
    @FXML
    ComboBox<Course> comboBox;
    @FXML
    Button btnClear;

    //File Explorer Screen
    public void handleBtnSubmitHome(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(Main.class.getResource("homeScreen.fxml"));
        if(filePath.getText().contains(".pdf")){
            Program.setPathName(filePath.getText());
            //System.out.println(Program.getPathName());
            Program.setCourseList(Program.buildCourses(Program.getPathName()));
            //System.out.println(Program.getCourseList());
//            Stage window = (Stage) this.submitToHome.getScene().getWindow();
//            window.setScene(new Scene(root));
//            window.show();
            ChangeScene.changeScene(actionEvent, "homeScreen.fxml");
        }else {
//            lblStatus.setText("Either PDF file is not found or supported. Try again, or choose a different PDF file.");
            txtNotValid.setVisible(true);
        }
//        PDFtoTXT.test(filePath.toString());
    }

    public void handleFEButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            filePath.setText((selectedFile.getAbsolutePath()));

        }else {
            //System.out.println("File is not valid!");

        }

    }

    //Home Screen
    public void handleBtnUpload(ActionEvent actionEvent) throws IOException {
        Program.getInputCourses().clear();
        Program.getUserCourseList().clear();
        if (!Program.getCourseList().isEmpty()){
//            Parent root = FXMLLoader.load(Main.class.getResource("courseCodes.fxml"));
//            Stage window = (Stage) this.upload.getScene().getWindow();
//            window.setScene(new Scene(root));
//            window.show();
            ChangeScene.changeScene(actionEvent, "courseCodes.fxml");
        } else {
            Alert a = Alerter.createAlert(Alert.AlertType.ERROR, "PDF IS INVALID DOESN'T CONTAIN COURSES!");
            a.show();
        }
    }

    public void handleBtnFileUpload(ActionEvent actionEvent) throws IOException {
        Program.getCourseList().clear();
        ChangeScene.changeScene(actionEvent, "fileExplorer.fxml");
//        Parent root = FXMLLoader.load(Main.class.getResource("fileExplorer.fxml"));
//        Stage window = (Stage) this.upload.getScene().getWindow();
//        window.setScene(new Scene(root));
//        window.show();
    }

    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) log.getScene().getWindow();
        stage.close();
    }

    public void onMouseEnterDisplayUserSchedule() {
        txtSprintOne.setText(Program.buildSchedule(1, Program.getUserCourseList()));
        txtSprintTwo.setText(Program.buildSchedule(2, Program.getUserCourseList()));
    }

    //User Input Screen
    public void handleBtnHome(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(Main.class.getResource("homeScreen.fxml"));
//        Stage window = (Stage) this.home.getScene().getWindow();
//        window.setScene(new Scene(root));
//        window.show();
        ChangeScene.changeScene(actionEvent, "homeScreen.fxml");
    }

    public void handleBtnAddCodesEnter(ActionEvent actionEvent){
        txtcode.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                    handleBtnAddCodes(actionEvent);
                }
            }
        });
    }

    //NULL POINTER EXCEPTION
    public void handleBtnFileEnter(ActionEvent actionEvent){
        filePath.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                    try {
                        handleBtnSubmitCodes(actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void handleBtnAddCodes(ActionEvent actionEvent){
        String code = txtcode.getText();
        txtcode.clear();
        boolean valid = Program.addToInputCourses(code);
        if (valid){
            txtcoursearea.setText(Program.getInputCourses().toString());
        } else {
            Alert a = Alerter.createAlert(Alert.AlertType.ERROR, "NOT A VALID COURSE CODE! PLEASE TRY AGAIN.");
            a.show();
        }
    }

    public void handleBtnSubmitCodes(ActionEvent actionEvent) throws IOException {
//        Window owner = home.getScene().getWindow();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Continue?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES){
            Parent root = FXMLLoader.load(Main.class.getResource("add-Class.fxml"));
            Stage window = (Stage) this.home.getScene().getWindow();
            window.setScene(new Scene(root, 800, 600));
            window.setResizable(false);
            window.show();
//            a = new Alert(Alert.AlertType.INFORMATION, "Select the box called Classes to begin. The Drop down menu will contain the classes with different times and sections. Once you have selected you class click add class and once done press submit to finish.");
//            a.show();
//            a.setResizable(true);
            a = Alerter.createAlert(Alert.AlertType.INFORMATION, "Select the box called Classes to begin. The Drop down menu will contain the classes with different times and sections. Once you have selected you class click add class and once done press submit to finish.");
            a.setTitle("Instructions");
            a.setHeaderText("Instructions");
            a.show();
//            Alerter.showAlert(Alert.AlertType.INFORMATION, owner, "Add Classes",
        }
    }

    //Schedule Creator Screen
    public void onShowTest(){

        if (comboBox.getItems().isEmpty()) {
            ObservableList<Course> classes = FXCollections.observableArrayList(Program.courseListCreator(3, Program.getInputCourses(), Program.getCourseList()));
            comboBox.getItems().addAll(classes);
            if (comboBox.getItems().isEmpty()){
                System.out.println("Courses not added!");
            } else {
                comboBox.show();
            }
        }
//        comboBox
    }

    public void onClickSubmitCourse(ActionEvent actionEvent){
        ArrayList<Course> tempArray = new ArrayList<>();
        Course temp = comboBox.getValue();
        Program.setUserCourses(temp);
        tempArray.add(temp);
        classList.appendText(tempArray.get(0).toString() + "\n");
        tempArray.remove(0);
        //System.out.println(Program.getUserCourseList());
    }

    public void onClickClearCourses(ActionEvent actionEvent){
        classList.clear();
        Program.getUserCourseList().clear();
    }

    public void onClickSubmit(ActionEvent actionEvent) throws IOException{
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Continue?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        a.showAndWait();
        if (a.getResult() == ButtonType.YES){
//            Parent root = FXMLLoader.load(Main.class.getResource("homeScreen.fxml"));
//            Stage window = (Stage) this.submit.getScene().getWindow();
//            window.setScene(new Scene(root));
//            window.show();
            ChangeScene.changeScene(actionEvent, "homeScreen.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}