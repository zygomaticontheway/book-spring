package hw.book.service;

import hw.book.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> getBooks(String title, String author);
    boolean createNewBook(Book book);
    boolean removeBook(Long isbn);

}
