package ch.hackathon.backend.services;

import ch.hackathon.backend.models.Book;
import ch.hackathon.backend.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }
}