package hw.book.repository;

import hw.book.dto.BookRequestDto;
import hw.book.dto.BookResponseDto;
import hw.book.entity.Book;

import java.util.List;

public interface IBookRepository {
    List<BookResponseDto> findAll();
    BookResponseDto findByIsbn(String isbn);
    boolean addBook(BookRequestDto book);
    boolean removeBook(String isbn);

}
