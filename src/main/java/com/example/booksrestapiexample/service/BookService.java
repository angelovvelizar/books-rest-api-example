package com.example.booksrestapiexample.service;

import com.example.booksrestapiexample.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void initiliazeBooks();

    List<BookDTO> findAllBooks();

    Optional<BookDTO> findBookById(Long id);

    void deleteById(Long id);

    Long createBook(BookDTO bookDTO);
}
