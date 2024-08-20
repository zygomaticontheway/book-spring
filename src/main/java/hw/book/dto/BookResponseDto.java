package hw.book.dto;

import hw.book.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookResponseDto {
    private String title;
    private String author;
    private Integer yearOfPublication;

    public BookResponseDto(String title, String author, Integer yearOfPublication) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public BookResponseDto() {
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public static BookResponseDto of (Book book) {
        return new BookResponseDto( book.getTitle(), book.getAuthor(), book.getYearOfPublication());
    }

    public static List<BookResponseDto> of (List<Book> books) {
        return books.stream()
                .map(BookResponseDto::of)
                .collect(Collectors.toList());
    }
}
