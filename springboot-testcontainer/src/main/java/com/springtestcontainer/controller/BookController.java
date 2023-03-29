package com.springtestcontainer.controller;


import java.util.List;

import com.springtestcontainer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private BookService bookService;
	
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
        List<Book> books = bookService.findAllBook();
        books.forEach(b -> log.info("Found a book: {}", b));
        //log.info("BookController.books() start");
        return books;
    }

	@LogEntryExit
	@PostMapping("/book")
	public ResponseEntity<Book> saveBooks(@RequestBody Book book){
		try{
			Book savedBook = bookService.saveBook(book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		}catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
