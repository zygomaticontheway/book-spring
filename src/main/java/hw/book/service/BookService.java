package hw.book.service;

import hw.book.entity.Book;
import hw.book.repository.IBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Book> getBooks(String title, String author) {

        Predicate<Book> predicateByTitle = (title.equals("") ? book -> true : book -> book.getTitle().toLowerCase().contains(title));
        Predicate<Book> predicateByAuthor = (author.equals("") ? book -> true : book -> book.getAuthor().toLowerCase().contains(author));

        Predicate<Book> allconditions = predicateByTitle.and(predicateByAuthor);

        return repository.findAll()
                .stream()
                .filter(allconditions)
                .toList();
    }

    @Override
    public boolean createNewBook(Book book) {
        return repository.addBook(book);
    }

    @Override
    public boolean removeBook(String isbn) {
        if(findByIsbn(isbn) != null) {
            return repository.removeBook(findByIsbn(isbn));
        }
        return false;
    }

    public Book findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }
}
