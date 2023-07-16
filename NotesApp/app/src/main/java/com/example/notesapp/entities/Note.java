package com.example.notesapp.entities;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

public class Note implements Serializable {

    private int id;
    private String title;
    private String body;
    private int priority;
    private boolean unread;

    public Note(String title, String body, int priority, boolean unread) {
        this.title = title;
        this.body = body;
        this.priority = priority;
        this.unread = unread;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public Note(JSONObject jsonData)
    {
        try
        {
            fromJSON(jsonData);
        } catch (JSONException e) {

        }
    }
    public JSONObject toJson() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("title" , this.getTitle());
        data.put("body" , this.getBody());
        data.put("priority" , this.getPriority());
        data.put("unread" , this.isUnread());
        return data;
    }

    public void fromJSON(JSONObject jsonData) throws JSONException {
        this.id = jsonData.getInt("id");
        this.title = jsonData.getString("title");
        this.body = jsonData.getString("body");
        this.priority = jsonData.getInt("priority");
        this.unread = jsonData.getBoolean("unread");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && priority == note.priority && unread == note.unread && title.equals(note.title) && body.equals(note.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, priority, unread);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", priority=" + priority +
                ", unread=" + unread +
                '}';
    }
}
