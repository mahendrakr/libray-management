package com.genpact.library.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.genpact.library.mapper.Mapper;
import com.genpact.library.model.Book;
import com.genpact.library.repository.BookRepository;
import com.genpact.library.service.BookService;

/**
 * Implementation class of Book service.
 *
 */
@Service
public class BookServiceImpl implements BookService {
	@Resource
	private BookRepository repository;
	@Resource
	private Mapper mapper;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addBook(Book book) {
	 repository.save(mapper.mapToEntity(book));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateBook(Book book) {
		repository.save(mapper.mapToEntity(book));
		return book.getBookId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deleteBook(int bookId) {
		repository.deleteById(bookId);
		return bookId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Book> getAllBooks() {
		return mapper.mapToBook(repository.findAll());
	}

}
