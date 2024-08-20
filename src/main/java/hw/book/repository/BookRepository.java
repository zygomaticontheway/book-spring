package hw.book.repository;

import hw.book.entity.Book;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository implements IBookRepository {

    private List<Book> db = new ArrayList<>( List.of(
            new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee"),
            new Book("9780451524935", "1984",  "George Orwell"),
            new Book("9781503290563", "Pride and Prejudice", "Jane Austen"),
            new Book("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald")
    ));


    @Override
    public List<Book> findAll() {
        return new ArrayList<>(db);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return findAll()
                .stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addBook(Book book) {
        if(findByIsbn(book.getIsbn()) == null){
            db.add(book);
            return true;
        } else{
            System.out.println("Book already exists");
            return false;
        }
    }

    @Override
    public boolean removeBook(Book book) {
        return db.remove(book);
    }
}
