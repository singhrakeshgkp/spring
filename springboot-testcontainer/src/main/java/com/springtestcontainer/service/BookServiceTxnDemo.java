package com.springtestcontainer.service;

import com.springtestcontainer.entity.Book;
import com.springtestcontainer.repo.BookRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceTxnDemo {
    @Autowired
    BookRepo bookRepo;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public Book testRequireNew(Book book){
        return bookRepo.save(book);
    }
}
