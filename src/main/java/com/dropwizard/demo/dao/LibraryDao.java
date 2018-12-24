package com.dropwizard.demo.dao;

import com.dropwizard.demo.model.Books;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class LibraryDao extends AbstractDAO<Books> {

    public LibraryDao(SessionFactory factory) {
        super(factory);
    }

    public Books findById(Long book_id) {
        return get(book_id);
    }

    public Books create(Books book) {
        return persist(book);
    }

}
