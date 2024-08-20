package hw.book.service;

import hw.book.dto.BookRequestDto;
import hw.book.dto.BookResponseDto;
import hw.book.entity.Book;

import java.util.List;

public interface IBookService {

    List<BookResponseDto> getBooks(String title, String author, Integer yearOfPublication);
    boolean createNewBook(BookRequestDto book);
    boolean removeBook(String isbn);

}
