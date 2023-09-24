create database book_management;
use book_management;
create table book(
	bookId varchar(4) primary key unique,
    bookName varchar(50),
    price float check(price > 0),
    author varchar(100),
    status bit
);

-- 1. Get all Book
DELIMITER //
create procedure get_all_book()
BEGIN
	select book.bookId, book.bookName, book.price, book.author, book.status from book;
END //
DELIMITER ;

-- 2. Create Book
DELIMITER //
create procedure create_book(
	bookid_in varchar(4),
    bookname_in varchar(50),
    price_in float,
    author_in varchar(100),
    bookstatus_in bit
)
BEGIN 
	insert into book(bookId, bookName, price, author, status)
    values(bookid_in, bookname_in, price_in, author_in, bookstatus_in);    
END //
DELIMITER ;

-- 3. Update Book
DELIMITER //
create procedure update_book(
	bookid_in varchar(4),
    bookname_in varchar(50),
    price_in float,
    author_in varchar(100),
    bookstatus_in bit
)
BEGIN
	update book
    set bookName = bookname_in,
    price = price_in,
    author = author_in,
    status = bookstatus_in
    where bookId = bookid_in;
END //
DELIMITER ;

-- 4. Delete Book
DELIMITER //
create procedure delete_book(bookid_in varchar(4))
BEGIN
	delete from book
    where bookId = bookid_in;
END //
DELIMITER ;

-- 5. Search Book by bookName
DELIMITER //
create procedure get_book_by_name(bookname_in varchar(50))
BEGIN
	select bookId, bookName, price, author, status from book
    where bookName like concat('%', bookname_in,'%');
END //
DELIMITER ;

-- 6. Book Statistical by Author
DELIMITER //
create procedure get_cntBook_by_author()
BEGIN
	select author, count(bookId) as bookCnt from book
    group by author;
END //
DELIMITER ;
-- 7. Sort price ASC
DELIMITER //
create procedure sort_price_asc()
BEGIN
	select bookId, bookName, price, author, status from book
    order by book.price ASC;
END //
DELIMITER ;

-- 8. Get Book by bookId
DELIMITER //
create procedure get_book_by_id(bookid_in varchar(4))
BEGIN 
	select bookId, bookName, price, author, status from book
    where bookId = bookid_in;
END //
DELIMITER ;