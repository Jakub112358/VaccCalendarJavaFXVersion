module com.example.vacccalendarjavafxversion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vacccalendarjavafxversion to javafx.fxml;
    exports com.example.vacccalendarjavafxversion;
}