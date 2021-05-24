package com.sith.challenge.models.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sith.challenge.deserializer.CustomJsonDateDeserializer;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Author {
    @NotNull
    private int id;

    @NotNull
    private String name;

    @NotNull
    private Date birth;

    public Author() {
    }

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

    public Date getBirth() {
        return birth;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
