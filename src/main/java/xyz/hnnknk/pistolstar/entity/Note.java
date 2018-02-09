package xyz.hnnknk.pistolstar.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {

    private long id;
    private StringProperty name;
    private StringProperty date;
    private StringProperty body;

    public Note() {
        this.name = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.body = new SimpleStringProperty();
    }

    public Note(long id, String name, String date, String body) {
        this.id = id;
        this.name.set(name);
        this.date.set(date);
        this.body.set(body);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getBody() {
        return body.get();
    }

    public void setBody(String body) {
        this.body.set(body);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty bodyProperty() {
        return body;
    }
}
