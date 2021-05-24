package com.sith.challenge.models.dao;

import com.sith.challenge.models.Database;
import com.sith.challenge.models.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAuthor {

    public boolean create(Author author){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "INSERT INTO AUTHOR (NAME, BIRTH) VALUES (?, ?)";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, author.getName());
            query.setDate(2, author.getBirth());
            query.execute();
            query.close();
            return true;
        }catch (SQLException e){
            System.out.println("Error on create, DaoAuthor: " + e);
        }
        return false;
    }

    public Author read(int id) {

        Database db = new Database();
        Connection con = db.getConnection();

        String sql = "SELECT * FROM AUTHOR WHERE ID = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);  //protection against sql injection
            ResultSet rs = query.executeQuery();
            Author author = (rs.next())? getAuthorFromResultSet(rs) : null;
            rs.close();
            query.close();
            return author;
        }catch(SQLException e){
            System.out.println("Error on read, DaoAuthor: " + e);
        }
        return null;
    }

    public List<Author> readAll(){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql= "SELECT * FROM AUTHOR";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            ResultSet rs = query.executeQuery();
            List<Author> list = new ArrayList<Author>();
            while(rs.next()){
                list.add(getAuthorFromResultSet(rs));
            }
            rs.close();
            query.close();
            return list;
        }catch(SQLException e){
            System.out.println("Error on readAll, DaoAuthor: " + e);
        }
        return null;
    }

    public boolean update(Author author, int id){

        Database db = new Database();
        Connection con = db.getConnection();

        String sql="UPDATE AUTHOR SET NAME = ?, BIRTH = ?  WHERE ID = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);

            query.setString(1, author.getName());  //protection against sql injection
            query.setDate(2, author.getBirth());  //protection against sql injection
            query.setInt(3, id);    //protection against sql injection
            query.execute();
            query.close();
            return true;

        }catch(SQLException e){
            System.out.println("Error on update, DaoAuthor: " + e);
        }
        return false;
    }

    public boolean delete(int id) {

        Database db = new Database();
        Connection con = db.getConnection();
        String sql = "DELETE FROM AUTHOR WHERE ID = ?";
        try{
            PreparedStatement query = con.prepareStatement(sql);
            query.setInt(1, id);
            query.execute();
            query.close();
            return true;
        }catch(SQLException e){
            System.out.println("Error on delete, DaoAuthor: " + e);
        }
        return false;
    }

    public Author getAuthorFromResultSet(ResultSet rs) {

        Author author = new Author();
        try {
            author.setId(rs.getInt("ID"));
            author.setName(rs.getString("NAME"));
            author.setBirth(rs.getDate("BIRTH"));
            return author;
        }catch(SQLException e){
            System.out.println("Error on getAuthorFromResultSet, DaoAuthor: " + e);
        }
        return null;
    }
}
