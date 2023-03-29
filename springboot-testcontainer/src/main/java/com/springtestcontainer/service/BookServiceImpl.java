package com.springtestcontainer.service;

import com.springtestcontainer.entity.Book;
import com.springtestcontainer.repo.BookRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements  BookService{
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    BookServiceTxnDemo bookServiceTxnDemo;
    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepo.findById(id);
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepo.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }
    @Override
    public Book getBookByUniqueBookId(String uniqueBookid) {
        return bookRepo.findByUniqueBookid(uniqueBookid);
    }

    /*spring transactions method start*/
    @Override
    @Transactional
    public Book saveBookTxnEx1(Book book) {
          return bookRepo.save(book);
    }

    @Override
    @Transactional
    public Book saveBookTxnEx2(Book book) {
        Book book1 = bookRepo.save(book);
       if(book1.getUniqueBookid().equalsIgnoreCase(book.getUniqueBookid()))
        throw new NullPointerException("NPE");
        return book1;
    }

    @Override
    @Transactional
    public Book saveBookTxnEx3(Book book) throws Exception{
        Book book1 = bookRepo.save(book);
        if(book1.getUniqueBookid().equalsIgnoreCase(book.getUniqueBookid()))
            throw new Exception("exception occurred");
        return book1;
    }

    @Override
    @Transactional(rollbackOn={Exception.class})
    public Book saveBookTxnEx4(Book book) throws Exception{
        Book book1 = bookRepo.save(book);
        if(book1.getUniqueBookid().equalsIgnoreCase(book.getUniqueBookid()))
            throw new IllegalArgumentException("exception occurred");
        return book1;
    }

    @Override
    @Transactional(value= Transactional.TxType.REQUIRED)
    public Book saveBookTxnEx5(Book book) {
        Book book1 = callM1(book);
        if(book1.getUniqueBookid().equalsIgnoreCase(book.getUniqueBookid()))
            throw new IllegalArgumentException("exception occurred");
        return  book1;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Book callM1(Book book){
        Book book1 = bookRepo.save(book);
        return book1;
    }

    @Override
    @Transactional(value= Transactional.TxType.REQUIRED)
    public Book saveBookTxnEx6(Book book) {
        Book book1 = bookServiceTxnDemo.testRequireNew(book);
        if(book1.getUniqueBookid().equalsIgnoreCase(book.getUniqueBookid()))
            throw new IllegalArgumentException("exception occurred");
        return  book1;
    }

}
