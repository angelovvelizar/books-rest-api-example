package com.example.booksrestapiexample.service.impl;

import com.example.booksrestapiexample.repository.BookRepository;
import com.example.booksrestapiexample.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
