package com.dropwizard.demo.client;

import com.dropwizard.demo.model.Authors;
import com.dropwizard.demo.model.Books;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BooksUtil {

    //util for creating 10 hardcoded books , for the purpose of this proof of concept

    public List<Books> retrieveBooksForSaving() {
        List<Books> booksList = new ArrayList<>();
        Books book = new Books();
        book.setNumOfPages(200);
        book.setGenre(Books.Genre.CLASSIC);
        book.setIsbn(RandomStringUtils.random(13, false, true));
        book.setTitle("Slika Dorijana Greja");
        Authors author = new Authors();
        Set<Authors> authorsSet = new HashSet<>();
        author.setFullName("Oskar Vajld");
        authorsSet.add(author);
        book.setAuthors(authorsSet);
        booksList.add(book);

        Books book2 = new Books();
        book2.setNumOfPages(200);
        book2.setGenre(Books.Genre.CLASSIC);
        book2.setIsbn(RandomStringUtils.random(13, false, true));
        book2.setTitle("Besnilo");
        Authors author2 = new Authors();
        Set<Authors> authorsSet2 = new HashSet<>();
        author2.setFullName("Borsilav Pekic");
        authorsSet2.add(author2);
        book2.setAuthors(authorsSet2);
        booksList.add(book2);


        Books book3 = new Books();
        book3.setNumOfPages(200);
        book3.setGenre(Books.Genre.ART);
        book3.setIsbn(RandomStringUtils.random(13, false, true));
        book3.setTitle("Geniji Umetnosti");
        Authors author3 = new Authors();
        Set<Authors> authorsSet3 = new HashSet<>();
        author3.setFullName("Piter Brojgel");
        authorsSet3.add(author3);
        book3.setAuthors(authorsSet3);
        booksList.add(book3);

        Books book4 = new Books();
        book4.setNumOfPages(200);
        book4.setGenre(Books.Genre.CLASSIC);
        book4.setIsbn(RandomStringUtils.random(10, false, true));
        book4.setTitle("Knjiga4");
        Authors author4 = new Authors();
        Set<Authors> authorsSet4 = new HashSet<>();
        author4.setFullName("Marko Markovic");
        authorsSet4.add(author4);
        book4.setAuthors(authorsSet4);
        booksList.add(book4);

        Books book5 = new Books();
        book5.setNumOfPages(200);
        book5.setGenre(Books.Genre.CLASSIC);
        book5.setIsbn(RandomStringUtils.random(10, false, true));
        book5.setTitle("Knjiga5");
        Authors author5 = new Authors();
        Set<Authors> authorsSet5 = new HashSet<>();
        author5.setFullName("Pera Peric");
        authorsSet5.add(author5);
        book5.setAuthors(authorsSet5);
        booksList.add(book5);

        Books book6 = new Books();
        book6.setNumOfPages(200);
        book6.setGenre(Books.Genre.ADVENTURE);
        book6.setIsbn(RandomStringUtils.random(10, false, true));
        book6.setTitle("Avanture");
        Set<Authors> authorsSet6 = new HashSet<>();
        Authors author6 = new Authors();
        author6.setFullName("Ivica Zivic");
        Authors author6drugi = new Authors();
        author6drugi.setFullName("Biljana Misic");
        authorsSet6.add(author6);
        authorsSet6.add(author6drugi);
        book6.setAuthors(authorsSet6);
        booksList.add(book6);

        Books book7 = new Books();
        book7.setNumOfPages(200);
        book7.setGenre(Books.Genre.CLASSIC);
        book7.setIsbn(RandomStringUtils.random(13, false, true));
        book7.setTitle("Lovac u Zitu");
        Authors author7 = new Authors();
        Set<Authors> authorsSet7 = new HashSet<>();
        author7.setFullName("Dz Selindzer");
        authorsSet7.add(author7);
        book7.setAuthors(authorsSet7);
        booksList.add(book7);

        Books book8 = new Books();
        book8.setNumOfPages(200);
        book8.setGenre(Books.Genre.CLASSIC);
        book8.setIsbn(RandomStringUtils.random(13, false, true));
        book8.setTitle("Mali Princ");
        Authors author8 = new Authors();
        Set<Authors> authorsSet8 = new HashSet<>();
        author8.setFullName("Antoan De Sent Egziperi");
        authorsSet8.add(author8);
        book8.setAuthors(authorsSet8);
        booksList.add(book8);

        Books book9 = new Books();
        book9.setNumOfPages(200);
        book9.setGenre(Books.Genre.CLASSIC);
        book9.setIsbn(RandomStringUtils.random(13, false, true));
        book9.setTitle("Na Drini Cuprija");
        Authors author9 = new Authors();
        Set<Authors> authorsSet9 = new HashSet<>();
        author9.setFullName("Ivo Andric");
        authorsSet9.add(author9);
        book9.setAuthors(authorsSet9);
        booksList.add(book9);

        Books book10 = new Books();
        book10.setNumOfPages(200);
        book10.setGenre(Books.Genre.CLASSIC);
        book10.setIsbn(RandomStringUtils.random(13, false, true));
        book10.setTitle("Tri ratna druga");
        Authors author10 = new Authors();
        Set<Authors> authorsSet10 = new HashSet<>();
        author10.setFullName("Erih Maria Remark");
        authorsSet10.add(author10);
        book10.setAuthors(authorsSet10);
        booksList.add(book10);

        return booksList;
    }
}
