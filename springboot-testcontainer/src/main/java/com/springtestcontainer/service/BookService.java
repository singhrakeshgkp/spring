package com.springtestcontainer.service;

import com.springtestcontainer.entity.Book;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public Optional<Book> getBookById(long id);
    public List<Book> findAllBook();
    public Book saveBook(Book book);
    public Book saveBookTxnEx1(Book book);

    Book saveBookTxnEx2(Book book);

    Book getBookByUniqueBookId(String uniqueBookid);

    Book saveBookTxnEx3(Book book) throws  Exception;

    Book saveBookTxnEx4(Book book)throws  Exception;

    Book saveBookTxnEx5(Book book) ;


    public Book saveBookTxnEx6(Book book) ;
}
