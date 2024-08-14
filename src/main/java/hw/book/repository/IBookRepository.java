package hw.book.repository;

import hw.book.entity.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> findAll();
    Book findByIsbn(Long isbn);
    boolean createNewBook (Book book);
    boolean removeBook (Book book);

}
