package com.example.booksrestapiexample.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity{

    @Column
    private String title;

    @Column
    private String isbn;

    @ManyToOne(cascade = CascadeType.ALL)
    private AuthorEntity author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}
