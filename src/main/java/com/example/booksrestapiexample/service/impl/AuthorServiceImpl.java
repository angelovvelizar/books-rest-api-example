package com.example.booksrestapiexample.service.impl;

import com.example.booksrestapiexample.repository.AuthorRepository;
import com.example.booksrestapiexample.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
