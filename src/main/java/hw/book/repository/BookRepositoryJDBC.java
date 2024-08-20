package hw.book.repository;

import hw.book.dto.BookRequestDto;
import hw.book.dto.BookResponseDto;
import hw.book.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookRepositoryJDBC implements IBookRepository{

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryJDBC(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Book> BOOK_ROW_MAPPER = (row, rowNum) -> {

        String isbn = row.getString("isbn");
        String title = row.getString("title");
        String author = row.getString("author");
        Integer yearOfPublication = row.getInt("yearofpublication");

        return new Book(isbn, title, author, yearOfPublication);
    };

    @Override
    public List<BookResponseDto> findAll() {

        String query = "SELECT * FROM books";

        return BookResponseDto.of(jdbcTemplate.query(query, BOOK_ROW_MAPPER));
    }

    @Override
    public BookResponseDto findByIsbn(String isbn) {

        String query = "SELECT * FROM books WHERE isbn = ?";

        Book foundedBook = jdbcTemplate.queryForObject(query, new Object[]{isbn}, BOOK_ROW_MAPPER);

        return BookResponseDto.of(foundedBook);
    }

    @Override
    public boolean addBook(BookRequestDto book) {

        String query = "INSERT INTO books (isbn, title, author) VALUES (?, ?, ?)";

        if(findByIsbn(book.getIsbn()) == null) {
            jdbcTemplate.update(query, book.getIsbn(), book.getTitle(), book.getAuthor());
            return true;
        } else {
            System.out.println("Book already exists");
            return false;
        }

    }

    @Override
    public boolean removeBook(String isbn) {
        String query = "DELETE FROM books WHERE isbn = ?";

        if(findByIsbn(isbn) != null) {
            int rowsAffected = jdbcTemplate.update(query, isbn);
            return rowsAffected == 1;
        } else {
            System.out.println("Book does not exist");
            return false;
        }

    }
}
