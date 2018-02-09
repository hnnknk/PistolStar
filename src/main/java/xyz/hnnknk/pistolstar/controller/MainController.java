package xyz.hnnknk.pistolstar.controller;

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
import xyz.hnnknk.pistolstar.dao.NoteDAO;
import xyz.hnnknk.pistolstar.entity.Note;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView notesTable;

    private NoteDAO noteDAO;

    @FXML
    private TableColumn<Note, String> nameColumn;
    @FXML
    private TableColumn<Note, String> dateColumn;
    @FXML
    private TableColumn<Note, String> bodyColumn;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("note.fxml")))));
    }


    @FXML
    private void searchNotes() {
        try {
            ObservableList<Note> notesData = noteDAO.searchNotes();
            populateNotes(notesData);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void populateNotes (ObservableList<Note> noteData) {
        notesTable.setItems(noteData);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noteDAO = new NoteDAO();
        new Thread(this::searchNotes).start();

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        bodyColumn.setCellValueFactory(cellData -> cellData.getValue().bodyProperty());
    }
}
