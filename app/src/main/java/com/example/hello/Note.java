package com.example.hello;

public class Note {
    private int id;
    private String titre;
    private String contenu;
    private String date;

    public Note(int id, String titre, String contenu, String date) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }
}
