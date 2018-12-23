package com.dropwizard.demo.resources;

import com.dropwizard.demo.api.BookDTO;
import com.dropwizard.demo.model.Books;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/book")
public interface LibraryResource {

    @GET
    @Path("/{bookId}")
    @UnitOfWork
    Books getBook(@PathParam("bookId") String name);

    @POST
    @UnitOfWork
    Books saveBook(@Valid Books book) throws Exception;

}
