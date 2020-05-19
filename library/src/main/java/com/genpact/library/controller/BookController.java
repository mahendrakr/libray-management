package com.genpact.library.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.library.model.Book;
import com.genpact.library.service.BookService;

/**
 * Controller class to handle HTTP request and response
 *
 */
@RestController
public class BookController {
	/** Book service */
	@Resource
	private BookService service;

	/**
	 * Fetch all library books
	 * 
	 * @return Response entity : List of books
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "/books")
	public ResponseEntity<List<Book>> retrieveAllBooks() {
		List<Book> books = service.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	/**
	 * Add book to library
	 * 
	 * @param book
	 * @return Response entity : String
	 */
	@PostMapping(path = "/books/save")
	public ResponseEntity<String> save(@RequestBody Book book) {
		service.addBook(book);
		return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);

	}

	/**
	 * Delete book from library
	 * 
	 * @param id
	 * @return Response entity : Integer
	 */
	@DeleteMapping(path = "/books/delete/{id}")
	public ResponseEntity<Integer> delete(@PathVariable int id) {
		int bookId = service.deleteBook(id);
		return new ResponseEntity<>(bookId, HttpStatus.OK);

	}

	/**
	 * Update existing book of the library
	 * 
	 * @param book
	 * @return Response entity : String
	 */
	@PutMapping(path = "/books/update")

	public ResponseEntity<String> update(@RequestBody Book book) {
		service.updateBook(book);
		return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
	}
}
