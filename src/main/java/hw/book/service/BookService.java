package hw.book.service;

import hw.book.entity.Book;
import hw.book.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService implements IBookService {

    private final IBookRepository repository;

    @Autowired
    public BookService(IBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getBooks(String title, String author) {

        Predicate<Book> predicateByTitle = (title.equals("") ? book -> true : book -> book.getTitle().equals(title));
        Predicate<Book> predicateByAuthor = (author.equals("") ? book -> true : book -> book.getAuthor().equals(author));

        Predicate<Book> allconditions = predicateByTitle.and(predicateByAuthor);

        return repository.findAll()
                .stream()
                .filter(allconditions)
                .toList();
    }

    @Override
    public boolean createNewBook(Book book) {
        return repository.createNewBook(book);
    }

    @Override
    public boolean removeBook(Long isbn) {
        if(findByIsbn(isbn) != null) {
            return repository.removeBook(findByIsbn(isbn));
        }
        return false;
    }

    public Book findByIsbn(Long isbn) {
        return repository.findByIsbn(isbn);
    }
}
