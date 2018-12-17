package com.dropwizard.demo.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@NamedQueries(
        {
                @NamedQuery(
                        name = "SELECT_ALL_AUTHORS_FOR_SPECIFIC_BOOK",
                        query = "SELECT * FROM Authors"
                )
        })
public class Authors {


}
