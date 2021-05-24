package com.sith.challenge.controller;

import com.sith.challenge.models.dao.DaoBook;
import com.sith.challenge.models.entities.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sithChallenge")
public class BookController {

    @GetMapping(path = "/books/{id}")
    public Book getBookById(@PathVariable int id){
        DaoBook db = new DaoBook();
        return db.read(id);
    }

    @GetMapping(path = "/books/author/{id}")
    public List<Book> getBookByAuthor(@PathVariable int id){
        DaoBook dbBook = new DaoBook();
        return dbBook.readByAuthor(id);
    }

    @PostMapping(path = "/books")
    public boolean addBook(@RequestBody Book book){
        DaoBook db = new DaoBook();
        return db.create(book);
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks(){
        DaoBook db = new DaoBook();
        return db.readAll();
    }

    @PutMapping(value = "/books/{id}")
    public boolean update (@PathVariable int id, @RequestBody Book aux) {
        DaoBook db = new DaoBook();
        if (id > 0) {
            if (db.read(id) != null) {
                return db.update(aux, id);
            }
        }
        return false;
    }

    @DeleteMapping(value = "/books/{id}")
    public boolean delete(@PathVariable int id) {
        DaoBook db = new DaoBook();
        if (id != 0) {
            if (db.read(id) != null) {
                return db.delete(id);
            }
        }
        return false;
    }
}
