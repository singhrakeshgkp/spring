package com.springtestcontainer.service;


import com.springtestcontainer.entity.Book;
import com.springtestcontainer.repo.BookRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookServiceTxnTest {

   @Autowired BookRepo bookRepo;
   @SpyBean
   BookServiceImpl bookServiceImpl;

   @BeforeEach
   public void init(){

   }
    @Test
    @DisplayName("Test 1 @Transaction Test with default propagation behaviour")
    public void saveBookTxnEx1(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
       Book book1=  bookServiceImpl.saveBookTxnEx1(book);
        Assertions.assertEquals("123RJavaBook",book1.getUniqueBookid());
    }

    @Test
    @DisplayName("Test 2 @Transaction Throwing checked exception")
    public void saveBookTxnEx2(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
        assertThrows(NullPointerException.class,()->bookServiceImpl.saveBookTxnEx2(book));
        Book savedBook = bookServiceImpl.getBookByUniqueBookId(book.getUniqueBookid());
        assertTrue(savedBook==null);
    }

    @Test
    @DisplayName("Test 3 @Transaction Throwing checked exception")
    public void saveBookTxnEx3(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
        assertThrows(Exception.class,()->bookServiceImpl.saveBookTxnEx3(book));
        Book savedBook = bookServiceImpl.getBookByUniqueBookId(book.getUniqueBookid());
        assertTrue(savedBook!=null);
    }

    @Test
    @DisplayName("Test 4 @Transaction Throwing checked exception")
    public void saveBookTxnEx4(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
        assertThrows(Exception.class,()->bookServiceImpl.saveBookTxnEx4(book));
        Book savedBook = bookServiceImpl.getBookByUniqueBookId(book.getUniqueBookid());
        assertTrue(savedBook==null);
    }


    @Test
    @DisplayName("Test 5 - test case for transaction propagation required and required_new- Record should not be saved")
    public void saveBookTxnEx5(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
        assertThrows(Exception.class,()->bookServiceImpl.saveBookTxnEx5(book));
        Book savedBook = bookServiceImpl.getBookByUniqueBookId(book.getUniqueBookid());
        assertTrue(savedBook ==null);
    }

    @Test
    @DisplayName("Test 6 - test case for transaction propagation required and required_new- Record should be saved")
    public void saveBookTxnEx6(){
        Book book = new Book();
        book.setUniqueBookid("123RJavaBook");
        book.setAuthor("rakesh");
        assertThrows(Exception.class,()->bookServiceImpl.saveBookTxnEx6(book));
        Book savedBook = bookServiceImpl.getBookByUniqueBookId(book.getUniqueBookid());
        assertTrue(savedBook !=null);
    }

}
