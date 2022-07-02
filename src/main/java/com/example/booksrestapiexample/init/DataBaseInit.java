package com.example.booksrestapiexample.init;

import com.example.booksrestapiexample.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInit implements CommandLineRunner {
    private final BookService bookService;

    public DataBaseInit(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.bookService.initiliazeBooks();
    }
}
