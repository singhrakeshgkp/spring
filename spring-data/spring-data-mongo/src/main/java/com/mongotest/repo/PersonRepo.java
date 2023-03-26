package com.mongotest.repo;

import com.mongotest.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepo extends MongoRepository<Person,String> {
}
