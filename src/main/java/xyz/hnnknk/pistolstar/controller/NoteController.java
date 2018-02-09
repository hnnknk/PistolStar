package xyz.hnnknk.pistolstar.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import xyz.hnnknk.pistolstar.dao.NoteDAO;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    private final int MAX_AREA_CHARS = 100;
    private final int MAX_FIELD_CHARS = 30;

    private NoteDAO noteDAO;

    @FXML private TextField name;

    @FXML private TextField date;

    @FXML private TextArea area;

    @FXML
    private void setAreaTextFormatter() {
        area.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_AREA_CHARS ? change : null));
    }

    @FXML
    private void setFieldTextFormatter() {
        name.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_FIELD_CHARS ? change : null));
        date.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= MAX_FIELD_CHARS ? change : null));

    }


    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if(performCheckEmptyFields()) {
            insertNote();
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main.fxml")))));
        } else {
            showAlert();
        }

    }

    private boolean performCheckEmptyFields() {
        boolean result;
        result = !name.getText().trim().isEmpty() && !area.getText().trim().isEmpty() && !date.getText().trim().isEmpty();
        return result;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText("Все поля должны быть заполнены");
        alert.showAndWait();
    }

    @FXML
    private void insertNote () {
        new Thread(() -> noteDAO.insertNote(name.getText(),date.getText(), area.getText())).start();
    }

    private void setDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        date.setText(dateFormat.format(new Date()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noteDAO = new NoteDAO();
        setDate();
        setAreaTextFormatter();
        setFieldTextFormatter();
    }
}
