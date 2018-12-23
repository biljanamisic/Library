package com.dropwizard.demo.api;

import java.io.Serializable;

import com.dropwizard.demo.model.Authors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 7663586332968044903L;

	private Long authorId;

	private String fullName;

	public AuthorDTO(){}

	public AuthorDTO(Authors author) {
		this.authorId = author.getAuthor_id();
		this.fullName = author.getFullName();
	}
	
	@JsonProperty
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@JsonProperty
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public static Authors toAuthor(AuthorDTO authorDTO) {
		Authors author = new Authors();
		author.setAuthor_id(authorDTO.getAuthorId());
		author.setFullName(authorDTO.getFullName());
		return author;
	}	
	
	public String toString() {
		return fullName;
	}
}
