package com.dropwizard.demo.resources;

import com.dropwizard.demo.model.Books;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/book")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LibraryResource {

    @GET
    @UnitOfWork
    @Path("/{book_id}")
    Books getBook(@PathParam("book_id") Long book_id);

    @POST
    @UnitOfWork
    Books saveBook(@Valid Books book) throws Exception;

}
