package com.readtracker.repo;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.readtracker.persistent.Author;

public interface AuthorRepo extends CassandraRepository<Author, String>{

	
}
