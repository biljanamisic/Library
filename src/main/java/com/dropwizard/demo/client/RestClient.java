package com.dropwizard.demo.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dropwizard.demo.exception.InvalidBookException;
import com.dropwizard.demo.model.Books;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.ArrayList;
import java.util.List;

@Path("/client/books")
@Produces(MediaType.APPLICATION_JSON)
public class RestClient {
	
	private static Logger logger = Logger.getLogger(RestClient.class);
	private Client client;
	private static String BASE_URI = "http://localhost:8080/api/book/";
	private WebTarget getBookTarget;
	private WebTarget saveBookTarget;
	int maxNumOfBooks = 10;
	
	public RestClient(Client client) throws InvalidBookException {
		this.client = client;
		getBookTarget = client.target(BASE_URI).path("/{book_id}");
		saveBookTarget = client.target(BASE_URI);
	}

	@GET
	@Path("/ten-books")
	public Response saveTenBooks() throws WebApplicationException{
		List<Books> booksForSaving = new ArrayList<>();
		try {
			booksForSaving.addAll(new BooksUtil().retrieveBooksForSaving());
			if (booksForSaving.size() > maxNumOfBooks) {
				logger.log(Priority.ERROR,"User has tried saving more than " + maxNumOfBooks + " books.");
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
			logger.info("Sending request for saving" + booksForSaving.size() + " books");
			for (Books books : booksForSaving) {
				Response response = saveBookTarget
						.request()
						.post(Entity.json(books), Response.class);
				if (Response.Status.OK.getStatusCode() != response.getStatus()) {
					logger.log(Priority.ERROR, "Error while trying to save book with isbn: " + books.getIsbn());
					throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
				}
			}
		} catch (Exception e) {
			logger.log(Priority.INFO, "Exception while trying to save books: " + e.getMessage());
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/{book_id}")
	public Books getBookById(@PathParam("book_id") Long book_id) {
		try {
			logger.log(Priority.INFO, "Sending request to service in order to retrieve a book with bookId: " + book_id);
			Books response = getBookTarget
				.resolveTemplate("book_id", book_id)
				.request()
				.get(Books.class);
				System.out.println("Writing out Book " +  response);
	        return response;
		} catch(WebApplicationException ex) {
			logger.log(Priority.ERROR, "Error while trying to retrieve book with bookId: " + book_id);
			throw new WebApplicationException("Not able to retrieve book with id: " + book_id, ex.getResponse());
		} 
	}
}
