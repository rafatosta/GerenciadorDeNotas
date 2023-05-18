module com.uefs.gerenciadordenotas {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;

    opens com.uefs.gerenciadordenotas.model;
    opens com.uefs.gerenciadordenotas.controller  to javafx.graphics, javafx.fxml;

    opens com.uefs.gerenciadordenotas to javafx.fxml;
    exports com.uefs.gerenciadordenotas;
    exports com.uefs.gerenciadordenotas.controller;





}