package xyz.hnnknk.pistolstar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import xyz.hnnknk.pistolstar.dao.NoteDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    public final int MAX_CHARS = 100;

    NoteDAO noteDAO;

    @FXML private TextField name;

    @FXML private TextArea area;

    @FXML public void setTextFormatter() {
        area.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_CHARS ? change : null));
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        insertNote();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"))));
    }

    @FXML
    private void insertNote () {
        new Thread(() -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
            try {
                noteDAO.insertNote(name.getText(),dateFormat.format(new Date()), area.getText());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noteDAO = new NoteDAO();
        setTextFormatter();
    }
}
