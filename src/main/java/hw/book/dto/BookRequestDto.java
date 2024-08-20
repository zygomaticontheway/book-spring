package hw.book.dto;

import hw.book.entity.Book;

public class BookRequestDto {
    private String isbn;
    private String title;
    private String author;


    public BookRequestDto(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public BookRequestDto() {
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public static Book toEntity (BookRequestDto request) {
        return new Book(request.getIsbn(), request.getTitle(), request.getAuthor());
    }
}
