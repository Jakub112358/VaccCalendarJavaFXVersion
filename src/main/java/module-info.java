module com.example.vacccalendarjavafxversion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.immunizationcalendar to javafx.fxml;
    exports com.immunizationcalendar;
    exports com.immunizationcalendar.wiev;
    opens com.immunizationcalendar.wiev to javafx.fxml;
}