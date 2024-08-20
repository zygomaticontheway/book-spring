package hw.book.repository;

import hw.book.entity.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> findAll();
    Book findByIsbn(String isbn);
    boolean addBook(Book book);
    boolean removeBook(Book book);

}
