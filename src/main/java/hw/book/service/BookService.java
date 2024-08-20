package hw.book.service;

import hw.book.dto.BookRequestDto;
import hw.book.dto.BookResponseDto;
import hw.book.entity.Book;
import hw.book.repository.IBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Service
public class BookService implements IBookService {

    private final IBookRepository repository;
    private final ModelMapper mapper;


    @Autowired
    public BookService(@Qualifier("getRepository") IBookRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<BookResponseDto> getBooks(String title, String author, Integer yearOfPublication) {

        Predicate<BookResponseDto> predicateByTitle = (title.equals("") ? book -> true : book -> book.getTitle().toLowerCase().contains(title));
        Predicate<BookResponseDto> predicateByAuthor = (author.equals("") ? book -> true : book -> book.getAuthor().toLowerCase().contains(author));
        Predicate<BookResponseDto> predicateByYearOfPublication = (yearOfPublication.equals(0) ? book -> true : book -> Objects.equals(book.getYearOfPublication(), yearOfPublication));

        Predicate<BookResponseDto> allconditions = predicateByTitle.and(predicateByAuthor).and(predicateByYearOfPublication);

        List<BookResponseDto> list = repository.findAll()
                .stream()
                .filter(allconditions)
                .toList();

        return list;
    }

    @Override
    public boolean createNewBook(BookRequestDto book) {

        return repository.addBook(book);
    }

    @Override
    public boolean removeBook(String isbn) {
        if(findByIsbn(isbn) != null) {
            return repository.removeBook(isbn);
        }
        return false;
    }

    public BookResponseDto findByIsbn(String isbn) {

        return repository.findByIsbn(isbn);
    }
}
