package com.sith.challenge.models.dao;

import com.sith.challenge.models.Database;
import com.sith.challenge.models.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoBook {
    public boolean create(Book book){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "INSERT INTO books (id, name) VALUES (?, ?, ?)";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, book.getName());
            query.setInt(2, book.getEdition());
            query.setInt(3, book.getAuthorId());
            query.execute();
            query.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error on create, DaoBook: " + e);
        }
        return false;
    }

    public Book read(int id) {

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "SELECT * FROM books WHERE id = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);  //protection against sql injection
            ResultSet rs = query.executeQuery();
            Book book = (rs.next())? getBookFromResultSet(rs) : null;
            rs.close();
            query.close();
            return book;
        }catch(SQLException e){
            System.out.println("Error on read, DaoBook: " + e);
        }
        return null;
    }

    public List<Book> readAll(){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "SELECT * FROM books";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            List<Book> list = new ArrayList<Book>();
            while(rs.next()){
                list.add(getBookFromResultSet(rs));
            }
            rs.close();
            query.close();
            return list;
        }catch(SQLException e){
            System.out.println("Error on readAll, DaoBook: " + e);
        }
        return null;
    }

    public List<Book> readByAuthor(int author_id){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "SELECT * FROM books WHERE id = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, author_id);
            ResultSet rs = query.executeQuery();
            List<Book> list = new ArrayList<Book>();
            while(rs.next()){
                list.add(getBookFromResultSet(rs));
            }
            rs.close();
            query.close();
            return list;
        }catch (SQLException e){
            System.out.println("Error on readByAuthor, DaoBook: " + e);
        }
        return null;
    }

    public boolean update(Book book, int id){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql="UPDATE books SET name = ?, id = ?  WHERE id = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);

            query.setString(1, book.getName());  //protection against sql injection
            query.setInt(2, book.getEdition());  //protection against sql injection
            query.setInt(3, book.getAuthorId());//protection against sql injection
            query.execute();
            query.close();
            return true;

        }catch(SQLException e){
            System.out.println("Error on update, DaoBook: " + e);
        }
        return false;
    }

    public boolean delete(int id) {

        Database db = new Database();
        Connection con = db.getConnection();
        String sql = "DELETE FROM books WHERE id = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);
            query.execute();
            query.close();
            return true;
        }catch(SQLException e){
            System.out.println("Error on delete, DaoBook: " + e);
        }
        return false;
    }

    public Book getBookFromResultSet(ResultSet rs) {

        Book book = new Book();
        try {
            book.setId(rs.getInt("ID"));
            book.setName(rs.getString("NAME"));
            book.setEdition(rs.getInt("EDITION"));
            book.setAuthorId(rs.getInt("AUTHORID"));
            return book;
        }catch(SQLException e){
            System.out.println("Error on getBookFromResultSet, DaoBook: " + e);
        }
        return null;
    }
}
