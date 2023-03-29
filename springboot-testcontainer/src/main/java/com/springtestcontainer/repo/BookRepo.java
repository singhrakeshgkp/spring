package com.springtestcontainer.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.springtestcontainer.entity.Book;


public interface BookRepo extends JpaRepository<Book,Long> {

    Book findByUniqueBookid(String uniqueBookid);
}
