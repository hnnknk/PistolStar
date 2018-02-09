package xyz.hnnknk.pistolstar.dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.hnnknk.pistolstar.database.Database;
import xyz.hnnknk.pistolstar.entity.Note;


import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteDAO implements Runnable{

    public ObservableList<Note> searchNotes() throws Exception {

        String selectStmt = "SELECT * FROM note";
        ResultSet rsEmps = Database.dbExecuteQuery(selectStmt);

        return getNotesList(rsEmps);
    }

    private ObservableList<Note> getNotesList(ResultSet rs) throws SQLException {

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

    public void insertNote(String name, String date, String body) {

            String updateStmt =
                    "INSERT INTO NOTE\n" +
                            "(NOTE_NAME, NOTE_DATE, NOTE_BODY)\n" +
                            "VALUES\n" +
                            "('"+name+"', '"+date+"','"+body+"');\n";


            try {
                Database.dbExecuteUpdate(updateStmt);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void run() {

    }
}