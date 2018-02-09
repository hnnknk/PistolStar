package xyz.hnnknk.pistolstar.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xyz.hnnknk.pistolstar.database.Database;
import xyz.hnnknk.pistolstar.entity.Note;


import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteDAO {

    public static ObservableList<Note> searchNotes() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM note";

        try {
            ResultSet rsEmps = Database.dbExecuteQuery(selectStmt);

            ObservableList<Note> notesList = getNotesList(rsEmps);

            return notesList;
        } catch (SQLException e) {

            throw e;
        }
    }

    private static ObservableList<Note> getNotesList(ResultSet rs) throws SQLException, ClassNotFoundException {

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

    public static void insertNote(String name, String date, String body) throws SQLException, ClassNotFoundException {

        String updateStmt =
                        "INSERT INTO NOTE\n" +
                        "(NOTE_NAME, NOTE_DATE, NOTE_BODY)\n" +
                        "VALUES\n" +
                        "('"+name+"', '"+date+"','"+body+"');\n";

        try {
            Database.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            throw e;
        }
    }
}