package com.dropwizard.demo.resources;

import com.dropwizard.demo.client.BooksUtil;
import com.dropwizard.demo.model.Authors;
import com.dropwizard.demo.model.Books;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryResourceImplTest {

    private static final LibraryResource mockedLibraryResource = mock(LibraryResourceImpl.class);

    private BooksUtil util = new BooksUtil();
    private List<Books> booksList = util.retrieveBooksForSaving();
    private Books oneBook = booksList.get(0);
    private Books book = new Books();
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder().
            addResource(mockedLibraryResource)
            .build();

    @Before
    public void setUp() throws Exception{
        book.setBook_id(1L);
        book.setGenre(Books.Genre.CLASSIC);
        book.setIsbn(RandomStringUtils.random(13, false, true));
        book.setTitle("Slika Dorijana Greja");
        Authors author = new Authors();
        Set<Authors> authorsSet = new HashSet<>();
        author.setFullName("Oskar Vajld");
        authorsSet.add(author);
        book.setAuthors(authorsSet);
        when(mockedLibraryResource.getBook(1L)).thenReturn(oneBook);
        when(mockedLibraryResource.saveBook(book)).thenReturn(book);
    }

    @Test
    public void saveBookStatusOkSuccessfully() {
        Entity<Books> entity = Entity.entity(book, MediaType.APPLICATION_JSON_TYPE);
        Response response = resources.client().target("/api/book")
                .request().post(entity);
        Assert.assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void getBookByIdSuccessfully() {
        Books booksResponce = resources.client().target("/api/book/1")
                .request().get(Books.class);
        Assert.assertEquals(booksResponce.getIsbn(), oneBook.getIsbn());
    }

    @Test(expected = WebApplicationException.class)
    public void throwWebApplicationExceptionIfInvalidRequest() {
        resources.client().target("/api/book/15llkk545")
                .request().get(Books.class);
    }


}
