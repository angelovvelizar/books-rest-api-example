package com.example.booksrestapiexample.web;


import com.example.booksrestapiexample.model.dto.BookDTO;
import com.example.booksrestapiexample.service.BookService;
import org.springframework.data.domain.Page;
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

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDTO>> getBooks(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy){


        return ResponseEntity.ok(this.bookService.getBooks(pageNo,pageSize,sortBy));
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

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") Long bookId,
                                          @RequestBody BookDTO bookDTO){
        bookDTO.setId(bookId);
        Long updatedBookId = this.bookService.updateBook(bookDTO);
        return updatedBookId != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable Long id) {
        this.bookService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
