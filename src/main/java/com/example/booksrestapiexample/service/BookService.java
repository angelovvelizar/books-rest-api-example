package com.example.booksrestapiexample.service;

import com.example.booksrestapiexample.model.dto.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void initiliazeBooks();

    List<BookDTO> findAllBooks();

    Optional<BookDTO> findBookById(Long id);

    void deleteById(Long id);

    Long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);

    Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy);
}
