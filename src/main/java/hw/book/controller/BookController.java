package hw.book.controller;

import hw.book.dto.BookRequestDto;
import hw.book.dto.BookResponseDto;
import hw.book.entity.Book;
import hw.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookResponseDto> getBooks (@RequestParam(name = "title", required = false, defaultValue = "")String title,
                                           @RequestParam(name = "author", required = false, defaultValue = "")String author,
                                           @RequestParam(name = "yearOfPublication", required = false, defaultValue = "")int yearOfPublication){
      return bookService.getBooks(title, author, yearOfPublication);
    };

    @PostMapping("/books")
    public boolean addBook(BookRequestDto book){
        return bookService.createNewBook(book);
    }

    @GetMapping("/books/{isbn}")
    public BookResponseDto getBookByIsbn(@PathVariable(name = "isbn") String isbn){
        return bookService.findByIsbn(isbn);
    }
    @DeleteMapping("/books/{isbn}")
    public boolean deleteBookByIsbn(@PathVariable(name = "isbn") String isbn){
        return bookService.removeBook(isbn);
    };

}
