package com.example.booksrestapiexample.web;


import com.example.booksrestapiexample.model.dto.BookDTO;
import com.example.booksrestapiexample.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = this.bookService.findAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findBookById(@PathVariable Long id) {
        Optional<BookDTO> book = this.bookService.findBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book.get());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook, UriComponentsBuilder uriComponentsBuilder) {
        long newBookId = this.bookService.createBook(newBook);


        return ResponseEntity
                .created(uriComponentsBuilder.path("/books/{id}")
                        .build(newBookId))
                .build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id) {
        this.bookService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
