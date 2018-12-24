package com.dropwizard.demo.resources;

import com.dropwizard.demo.dao.LibraryDao;
import com.dropwizard.demo.model.Books;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class LibraryResourceImpl implements LibraryResource {

    private final LibraryDao libraryDao;

    public LibraryResourceImpl(LibraryDao libraryDao){
        this.libraryDao = libraryDao;
    }

    @Override
    public Books getBook(Long book_id){
        Books book = libraryDao.findById(book_id);
        if(book == null){
            throw new WebApplicationException("Book with id "+ book_id +" is not found", Response.Status.NOT_FOUND);
        }
        return  book;
    }

    @Override
    public Books saveBook(Books book) {
        return libraryDao.create(book);
    }


}
