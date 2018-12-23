package com.dropwizard.demo.api;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.dropwizard.demo.model.Authors;
import com.dropwizard.demo.model.Books;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO implements Serializable {
	
	private static final long serialVersionUID = 4310163996110951337L;

	private String title;
	
	private String isbn;
	
	private Set<AuthorDTO> authors = new HashSet<>();
	
	private int numberOfPages;
	
	private Books.Genre genre;
	
	public BookDTO() {}
	
	public BookDTO(Books book) {
		this.title = book.getTitle(); 
		this.isbn = book.getIsbn();
		Set<AuthorDTO> authorDTOs = book.getAuthors()
				.stream()
				.map(a -> new AuthorDTO(a))
				.collect(Collectors.toSet());
		this.authors = authorDTOs;		
		this.numberOfPages = book.getNumOfPages();
		this.genre = book.getGenre();
	}	

	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@JsonProperty
	public Set<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorDTO> authors) {
		this.authors = authors;
	}

	@JsonProperty
	public Integer getNumberOfPages() {
		return numberOfPages;
	}
	
	@JsonProperty
	public Books.Genre getGenre() {
		return genre;
	}

	public void setGenre(Books.Genre genre) {
		this.genre = genre;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	public static Books toBook(BookDTO bookDTO) {
		Books book = new Books();
		book.setIsbn(bookDTO.getIsbn());
		
		Set<Authors> authors = bookDTO.getAuthors()
				.stream()
				.map(dto -> AuthorDTO.toAuthor(dto))
				.collect(Collectors.toSet());
		
		book.setAuthors(authors);
		book.setNumOfPages(bookDTO.getNumberOfPages());
		book.setTitle(bookDTO.getTitle());
		book.setGenre(bookDTO.getGenre());
		
		return book;
	}
	
	public String toString() {
		return title + ", " + isbn + "authors: " + authors;
	}
}
