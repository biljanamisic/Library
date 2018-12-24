package com.dropwizard.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Books {

    public enum Genre {
        ART, DRAMA, CLASSIC, ADVENTURE, AUTOBIOGRAPHY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "numOfPages", nullable = false)
    private Integer numOfPages;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull
    @Column(name = "isbn", unique = true)
    @Length(min = 10, max = 13, message = "length can be from 10 to 13 digits")
    @Pattern(regexp = "[0-9]*$" , message = "can contain only digits")
    private String isbn;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_authors",
            joinColumns = { @JoinColumn(name ="book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<Authors> authors = new HashSet<>();

    @JsonIgnore
    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long id) {
        this.book_id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }

    @JsonProperty
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @JsonProperty
    public Set<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Authors> authors) {
        this.authors = authors;
    }

    @JsonProperty
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String ISBN) {
        this.isbn = ISBN;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        result = prime * result + ((numOfPages == null) ? 0 : numOfPages.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Books other = (Books) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (numOfPages == null) {
            if (other.numOfPages != null)
                return false;
        } else if (!numOfPages.equals(other.numOfPages))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", ISBN: " + isbn +
                ", " + genre.name() + ", number of pages: " +
                numOfPages + ", authors: " + authors;
    }
}
