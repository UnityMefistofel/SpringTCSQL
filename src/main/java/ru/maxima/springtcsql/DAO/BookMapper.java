package ru.maxima.springtcsql.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springtcsql.Book;
import ru.maxima.springtcsql.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book(rs.getLong("id"),
                             rs.getString("name"),
                             rs.getString("author"),
                             rs.getInt("releaseyear"),
                             rs.getLong("owner"));
        return book;
    }
}
