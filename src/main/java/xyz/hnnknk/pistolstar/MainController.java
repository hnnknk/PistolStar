package xyz.hnnknk.pistolstar;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import xyz.hnnknk.pistolstar.DAO.NoteDAO;
import xyz.hnnknk.pistolstar.entity.Note;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView notesTable;

    @FXML
    private TableColumn<Note, String> nameColumn;
    @FXML
    private TableColumn<Note, String> dateColumn;
    @FXML
    private TableColumn<Note, String> bodyColumn;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("note.fxml"))));
    }

    @FXML
    private void searchNotes() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Note> notesData = NoteDAO.searchNotes();
            populateNotes(notesData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting notes information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void populateNotes (ObservableList<Note> noteData) {
        notesTable.setItems(noteData);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            searchNotes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        bodyColumn.setCellValueFactory(cellData -> cellData.getValue().bodyProperty());
    }
}
