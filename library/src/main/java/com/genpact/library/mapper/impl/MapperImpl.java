package com.genpact.library.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.genpact.library.domain.Book;
import com.genpact.library.mapper.Mapper;

@Component
public class MapperImpl implements Mapper {

	@Override
	public List<Book> mapToEntity(List<com.genpact.library.model.Book> books) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book mapToEntity(com.genpact.library.model.Book book) {
		Book entity = new Book();
		entity.setBookId(book.getBookId());
		entity.setBookName(book.getBookName());
		entity.setAuthor(book.getAuthor());
		entity.setPublication(book.getPublication());
		return entity;
	}

	@Override
	public List<com.genpact.library.model.Book> mapToBook(List<Book> books) {
		List<com.genpact.library.model.Book> allBooks = new ArrayList<>();
		for (Book entity : books) {
			com.genpact.library.model.Book book = new com.genpact.library.model.Book();
			book.setBookId(entity.getBookId());
			book.setBookName(entity.getBookName());
			book.setAuthor(entity.getAuthor());
			book.setPublication(entity.getPublication());
			allBooks.add(book);
		}
		return allBooks;
	}

	@Override
	public com.genpact.library.model.Book mapToBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

}
