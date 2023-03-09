package ru.maxima.springtcsql.DAO;

import org.springframework.jdbc.core.RowMapper;
import ru.maxima.springtcsql.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person pers = new Person(rs.getLong("id"),
                                 rs.getString("fio"),
                                 rs.getInt("birthyear"),
                                 rs.getInt("cnt"));
        return pers;
    }
}
