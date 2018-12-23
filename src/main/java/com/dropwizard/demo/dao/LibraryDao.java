package com.dropwizard.demo.dao;

import com.dropwizard.demo.model.Books;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class LibraryDao extends AbstractDAO<Books> {

    public LibraryDao(SessionFactory factory) {
        super(factory);
    }

    public Books findByISBN(String ISBN) {
        return (get(ISBN));
    }

    public Books create(Books book) throws Exception  {
        Books book1;
        try{
             book1 =  persist(book);
        }catch(Exception e){
            throw new Exception(e);
        }
        return book1;
    }

}
