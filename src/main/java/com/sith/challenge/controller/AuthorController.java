package com.sith.challenge.controller;

import com.sith.challenge.models.entities.Author;
import com.sith.challenge.models.dao.DaoAuthor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sithChallenge")
public class AuthorController {

    //private Author aux = new Author();

    @GetMapping(path = "/authors/{id}")
    public Author getAuthorById(@PathVariable int id){ //Find by id
        DaoAuthor db = new DaoAuthor();
        return db.read(id);
    }

    @PostMapping(path = "/authors")
    public boolean addAuthor(@RequestBody Author author){
        DaoAuthor db = new DaoAuthor();
        return db.create(author);
    }

    @GetMapping(value = "/authors")
    public List<Author> getAuthors(){   //Find All
        DaoAuthor db = new DaoAuthor();
        return db.readAll();
    }

    @PutMapping(value = "/authors/{id}")
    public boolean update (@PathVariable int id, @RequestBody Author aux) {
        DaoAuthor db = new DaoAuthor();
        if (id > 0) {
            if (db.read(id) != null) {
                return db.update(aux, id);
            }
        }
        return false;
    }

    @DeleteMapping(value = "/authors/{id}")
    public boolean delete(@PathVariable int id) {
        DaoAuthor db = new DaoAuthor();
        if (id != 0) {
            if (db.read(id) != null) {
                return db.delete(id);
            }
        }
        return false;
    }
}
