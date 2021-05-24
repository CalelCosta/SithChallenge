package com.sith.challenge.models.entities;

import javax.validation.constraints.NotEmpty;

public class Book {
    @NotEmpty
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private int edition;

    public Book() {
    }

    private int author_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getAuthorId() {
        return author_id;
    }

    public void setAuthorId(int author_id) {
        this.author_id = author_id;
    }
}
