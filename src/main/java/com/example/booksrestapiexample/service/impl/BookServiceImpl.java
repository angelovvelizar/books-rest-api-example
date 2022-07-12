package com.example.booksrestapiexample.service.impl;

import com.example.booksrestapiexample.model.dto.AuthorDTO;
import com.example.booksrestapiexample.model.dto.BookDTO;
import com.example.booksrestapiexample.model.entity.AuthorEntity;
import com.example.booksrestapiexample.model.entity.BookEntity;
import com.example.booksrestapiexample.repository.AuthorRepository;
import com.example.booksrestapiexample.repository.BookRepository;
import com.example.booksrestapiexample.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }


    @Override
    public void initiliazeBooks() {
        if(this.bookRepository.count() == 0) {
            BookEntity book1 = new BookEntity();
            book1.setTitle("First Book");
            book1.setIsbn("12345");
            AuthorEntity author1 = new AuthorEntity();
            author1.setName("First Author");
            book1.setAuthor(author1);

            BookEntity book2 = new BookEntity();
            book2.setTitle("Second Book");
            book2.setIsbn("1234567");
            AuthorEntity author2 = new AuthorEntity();
            author2.setName("Second Author");
            book2.setAuthor(author2);

            BookEntity book3 = new BookEntity();
            book3.setTitle("Third Book");
            book3.setIsbn("12345678");
            AuthorEntity author3 = new AuthorEntity();
            author3.setName("Third Author");
            book3.setAuthor(author3);

            this.bookRepository.saveAll(List.of(book1, book2, book3));
        }
    }

    @Override
    public List<BookDTO> findAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(bookEntity -> this.modelMapper.map(bookEntity, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> findBookById(Long id) {
        return this.bookRepository.findById(id).map(bookEntity -> this.modelMapper.map(bookEntity, BookDTO.class));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Long createBook(BookDTO bookDTO) {
        BookEntity bookEntity = this.modelMapper.map(bookDTO, BookEntity.class);
        this.bookRepository.save(bookEntity);

        return bookEntity.getId();
    }

    @Override
    public Long updateBook(BookDTO bookDTO) {
        BookEntity bookEntity = this.bookRepository.findById(bookDTO.getId()).orElse(null);
        if(bookEntity == null){
            return null;
        }

        AuthorEntity author = this.authorRepository.findByName(bookDTO.getAuthor().getName())
                .orElseGet(() -> {
                    AuthorEntity newAuthor = new AuthorEntity();
                    newAuthor.setName(bookDTO.getAuthor().getName());
                    return authorRepository.save(newAuthor);
                });

        bookEntity.setTitle(bookDTO.getTitle());
        bookEntity.setIsbn(bookDTO.getIsbn());
        bookEntity.setAuthor(author);

        return this.bookRepository.save(bookEntity).getId();
    }

    @Override
    public Page<BookDTO> getBooks(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize , Sort.by(sortBy));

        return this.bookRepository.findAll(pageable).map(e -> {
            BookDTO book = this.modelMapper.map(e, BookDTO.class);
            AuthorDTO author = this.modelMapper.map(e.getAuthor(), AuthorDTO.class);
            book.setAuthor(author);
            return book;
        });
    }


}
