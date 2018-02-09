package xyz.hnnknk.pistolstar.dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.hnnknk.pistolstar.database.Database;
import xyz.hnnknk.pistolstar.entity.Note;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.*;

public class NoteDAO implements Runnable{

    public ObservableList<Note> searchNotes() throws Exception {

        String selectStmt = "SELECT * FROM note";
        ResultSet rsEmps = Database.dbExecuteQuery(selectStmt);

        ObservableList<Note> list = getNotesList(rsEmps);

        return list;
    }

    private ObservableList<Note> getNotesList(ResultSet rs) throws SQLException, ClassNotFoundException {

        ObservableList<Note> notesList = FXCollections.observableArrayList();

        while (rs.next()) {
            Note note = new Note();
            note.setName(rs.getString("NOTE_NAME"));
            note.setDate(rs.getString("NOTE_DATE"));
            note.setBody(rs.getString("NOTE_BODY"));
            notesList.add(note);
        }

        return notesList;
    }

    public void insertNote(String name, String date, String body) throws InterruptedException {

            String updateStmt =
                    "INSERT INTO NOTE\n" +
                            "(NOTE_NAME, NOTE_DATE, NOTE_BODY)\n" +
                            "VALUES\n" +
                            "('"+name+"', '"+date+"','"+body+"');\n";


            try {
                Database.dbExecuteUpdate(updateStmt);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void run() {

    }
}