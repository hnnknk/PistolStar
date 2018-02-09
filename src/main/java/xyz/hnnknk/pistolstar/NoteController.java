package xyz.hnnknk.pistolstar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import xyz.hnnknk.pistolstar.DAO.NoteDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    public final int MAX_CHARS = 100;

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
    private void insertNote () throws SQLException, ClassNotFoundException {
        try {
            NoteDAO.insertNote(name.getText(),"today", area.getText());
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTextFormatter();
    }
}
