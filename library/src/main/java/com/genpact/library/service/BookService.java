package com.genpact.library.service;

import java.util.List;

import com.genpact.library.model.Book;

/**
 * Book service interface to perform CRUD operation on book.
 *
 */
public interface BookService {
	/**
	 * Method to add the book
	 * 
	 * @param book
	 */
	public void addBook(Book book);

	/**
	 * Method to update the book
	 * 
	 * @param book
	 * @return int : bookId
	 */
	public int updateBook(Book book);

	/**
	 * Method to delete the book by bookId.
	 * 
	 * @param bookId
	 * @return int : bookId
	 */
	public int deleteBook(int bookId);

	/**
	 * Method to retrieve all books
	 * 
	 * @return List : Book
	 */
	public List<Book> getAllBooks();

}
