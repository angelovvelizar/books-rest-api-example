package com.example.booksrestapiexample.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "author")
    private Set<BookEntity> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }
}
