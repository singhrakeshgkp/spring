package com.springtestcontainer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id;

 @Column(name="book_title")
 private String bookTitle;

 @Column(name="author")
 private String author;

 @Column(name="unique_book_id",unique = true)
 private  String uniqueBookid;
}
