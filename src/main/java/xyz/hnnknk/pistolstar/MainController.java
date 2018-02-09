package xyz.hnnknk.pistolstar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("note.fxml"))));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
