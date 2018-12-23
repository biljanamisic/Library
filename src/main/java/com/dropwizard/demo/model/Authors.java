package com.dropwizard.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Authors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;

    @Column(name = "fullName")
    private String fullName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private Set<Books> books = new HashSet<>();

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long id) {
        this.author_id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
        this.books = books;
    }
}
