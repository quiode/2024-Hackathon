package ch.hackathon.backend.controllers;

import ch.hackathon.backend.models.Book;
import ch.hackathon.backend.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    private List<Book> getAllBooks() {
        return bookService.list();
    }
}
