DROP TABLE IF EXISTS books;
CREATE TABLE books(id int not null auto_increment primary key, book_title VARCHAR(255), author VARCHAR(255));
insert into books (book_title, author) values ('spring', 'abc');
insert into books (book_title, author) values ('cassandra', 'xyz');