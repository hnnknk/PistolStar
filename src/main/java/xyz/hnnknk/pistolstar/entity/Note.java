package xyz.hnnknk.pistolstar.entity;

public class Note {

    private long id;
    private String name;
    private String date;
    private String body;

    public Note() {
    }

    public Note(long id, String name, String date, String body) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
