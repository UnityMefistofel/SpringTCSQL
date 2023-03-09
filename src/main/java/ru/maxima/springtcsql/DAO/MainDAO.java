package ru.maxima.springtcsql.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springtcsql.Book;
import ru.maxima.springtcsql.Person;

import javax.sql.DataSource;
import java.util.List;

@Component
public class MainDAO {

    private final String SQL_FIND_PERSON = "select * from persons_view where id = ?";
    private final String SQL_DELETE_PERSON = "update persons set is_del = true where id = ?";
    private final String SQL_DELETE_PERSON_LINKS = "update books set owner = null where owner = ?";
    private final String SQL_UPDATE_PERSON = "update persons set fio = ?, birthyear  = ? where id = ?";
    private final String SQL_GET_ALL_PERSON = "select * from persons_view";
    private final String SQL_INSERT_PERSON = "insert into persons(fio, birthyear) values(?,?)";

    private final String SQL_LOOKUP_BOOKS_BY_PERSON = "select * from books where owner = ? and is_del = false";

    private final String SQL_GET_ALL_BOOK = "select * from books";
    private final String SQL_FIND_BOOK = "select * from books where id = ?";
    private final String SQL_INSERT_BOOK = "insert into books(name,author,releaseyear) values(?,?,?)";
    private final String SQL_UPDATE_BOOK = "update books set name = ?, author = ?, releaseyear = ? where id = ?";
    private final String SQL_DELETE_BOOK = "update books set is_del = true where id = ?";

    private final String SQL_BOOK_SET_AT = "update books set owner = ? where id = ?";

    private final String SQL_BOOK_RETURN = "update books set owner = null where id = ?";

    JdbcTemplate pgT;

    @Autowired
    public void MainDAO(DataSource dataSource) {
        pgT = new JdbcTemplate(dataSource);
    }
//    public void MainDAO(DataSource dataSource) {
//        pgT = new JdbcTemplate(dataSource);
//    }

    public Person getPersonById(Long id) {
        return pgT.query(SQL_FIND_PERSON, new Object[] {id}, new PersonMapper())
                .stream().findFirst().orElse(new Person());
    }

    public List<Person> getAllPersons() {
        return pgT.query(SQL_GET_ALL_PERSON, new PersonMapper());
    }

    public boolean createPerson(Person person) {
        return pgT.update(SQL_INSERT_PERSON, person.getFio(), person.getBirthyear()) > 0;
    }

    public boolean updatePerson(Person person) {
        return pgT.update(SQL_UPDATE_PERSON, person.getFio(), person.getBirthyear(),
                person.getId()) > 0;
    }

//    public boolean deletePerson(Person person) {
//        return pgT.update(SQL_DELETE_PERSON, person.getId()) > 0;
//    }

    public boolean deletePerson(Long id) {

        return pgT.update(SQL_DELETE_PERSON, id) +
                pgT.update(SQL_DELETE_PERSON_LINKS, id) > 0;
    }

    public Book getBookById(Long id) {
        return pgT.query(SQL_FIND_BOOK, new Object[] {id}, new BookMapper())
                                                        .stream().findFirst().orElse(new Book());
    }

    public List<Book> getAllBooks() {
        return pgT.query(SQL_GET_ALL_BOOK, new BookMapper());
    }

    public boolean createBook(Book book) {
        return pgT.update(SQL_INSERT_BOOK,book.getName(),book.getAuthor(),book.getReleaseyear()) > 0;
    }

    public boolean updateBook(Book book) {
        return pgT.update(SQL_UPDATE_BOOK, book.getName(),book.getAuthor(),book.getReleaseyear(), book.getId()) > 0;
    }

    public boolean deleteBook(Long id) {
        return pgT.update(SQL_DELETE_BOOK, id) > 0;
    }

    public List<Book> getBooksByOwner(Long ownerId) {
        return pgT.query(SQL_LOOKUP_BOOKS_BY_PERSON, new Object[] {ownerId}, new BookMapper());
    }

    public boolean setBookToOwner(Long bookId, Long ownerId) {
        return pgT.update(SQL_BOOK_SET_AT,new Object[] {ownerId,bookId}) >0;
    }

    public boolean returnBook(Long id) {
        return pgT.update(SQL_BOOK_RETURN,id) > 0;
    }

}
