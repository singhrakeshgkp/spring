package com.springtestcontainer.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtestcontainer.annotations.LogEntryExit;
import com.springtestcontainer.entity.Book;
import com.springtestcontainer.repo.BookRepo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookController {

	@Autowired
    private BookRepo bookRepo;
	
	@Operation(summary = "get all the books")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "books", 
			 content = {
					 @Content(mediaType = "application/json",schema = @Schema(implementation = Book.class))
			 })
	})
	@LogEntryExit
	@GetMapping("/books")
    public List<Book> books() {
		//log.info("BookController.books() start");
        List<Book> books = bookRepo.findAll();
        books.forEach(b -> log.info("Found a book: {}", b));
        //log.info("BookController.books() start");
        return books;
    }
}
