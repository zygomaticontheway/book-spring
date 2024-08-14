package hw.book.controller;

import hw.book.entity.Book;
import hw.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks (@RequestParam(name = "title", required = false, defaultValue = "")String title,
                                @RequestParam(name = "author", required = false, defaultValue = "")String author){
      return bookService.getBooks(title, author);
    };

    @PostMapping("/books")
    public Book addBook(Book book){
        bookService.createNewBook(book);
        return book;
    }

    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(@PathVariable(name = "isbn") Long isbn){
        return bookService.findByIsbn(isbn);
    }

}
