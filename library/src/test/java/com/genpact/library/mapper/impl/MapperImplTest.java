package com.genpact.library.mapper.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.genpact.library.mapper.Mapper;
import com.genpact.library.model.Book;

@SpringBootTest
class MapperImplTest {
	@Autowired
	private Mapper mapper;

	@Test
	void testMapToEntity() {
		com.genpact.library.domain.Book entity = mapper.mapToEntity(getBookModel());
		System.out.println(entity);
		assertThat(entity.getAuthor()).isEqualTo("Alok");
		assertThat(entity.getBookId()).isEqualTo(1);
	}

	@Test
	void testMapToBookList() {
		List<Book> books = mapper.mapToBook(getBookEntity());
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getBookName()).isEqualTo("Java");

	}

	private List<com.genpact.library.domain.Book> getBookEntity() {
		List<com.genpact.library.domain.Book> books = new ArrayList<>();
		com.genpact.library.domain.Book bk = new com.genpact.library.domain.Book();
		bk.setBookId(1);
		bk.setBookName("Java");
		bk.setAuthor("Alok");
		bk.setPublication("TMH");
		books.add(bk);
		return books;
	}

	private Book getBookModel() {
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("Java");
		book.setAuthor("Alok");
		book.setPublication("TMH");
		return book;
	}

}
