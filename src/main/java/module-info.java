module prog.schedule_application {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires pdfbox.app;

    opens prog.schedule_application to javafx.fxml;
    exports prog.schedule_application;
}