package com.springtestcontainer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
 private long id;
 private String bookTitle;
 private String author;
 
}
