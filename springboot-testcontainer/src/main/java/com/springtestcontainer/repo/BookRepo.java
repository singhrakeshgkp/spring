package com.springtestcontainer.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springtestcontainer.entity.Book;

@Service
public class BookRepo {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 public List<Book> findAll() {
	        return jdbcTemplate.query(
	                "SELECT id, book_title, author FROM books",
	                (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("book_title"), rs.getString("author"))
	        );
	    }
}
