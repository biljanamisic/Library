package com.dropwizard.demo.resources;

import com.dropwizard.demo.api.BookDTO;
import com.dropwizard.demo.dao.LibraryDao;
import com.dropwizard.demo.model.Books;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class LibraryResourceImpl implements LibraryResource {

    private final LibraryDao libraryDao;

    public LibraryResourceImpl(LibraryDao libraryDao){
        this.libraryDao = libraryDao;
    }

    @Override
    public Books getBook(@PathParam("isbn") String isbn){
        return  libraryDao.findByISBN(isbn);
    }

    @Override
    public Books saveBook(Books book) throws Exception{
        return libraryDao.create(book);
    }


}
