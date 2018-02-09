package xyz.hnnknk.pistolstar.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {

    private StringProperty name;
    private StringProperty date;
    private StringProperty body;

    public Note() {
        this.name = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
        this.body = new SimpleStringProperty();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setDate(String date) {
        this.date.set(date);
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
