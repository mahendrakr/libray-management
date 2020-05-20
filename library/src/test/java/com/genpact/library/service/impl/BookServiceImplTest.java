package com.genpact.library.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.genpact.library.mapper.Mapper;
import com.genpact.library.model.Book;
import com.genpact.library.repository.BookRepository;
import com.genpact.library.service.BookService;

@SpringBootTest
class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	@MockBean
	private BookRepository repository;
	@MockBean
	private Mapper mapper;

	@Test
	void testAddBook() {
		Book model = getBookModel();
		com.genpact.library.domain.Book entity = getBookEntity();
		when(mapper.mapToEntity(model)).thenReturn(entity);
		when(repository.save(getBookEntity())).thenReturn(entity);
		bookService.addBook(model);
		verify(mapper, times(1)).mapToEntity(model);
		verify(repository, times(1)).save(entity);
	}

	@Test
	void testUpdateBook() {
		when(mapper.mapToEntity(getBookModel())).thenReturn(getBookEntity());
		when(repository.save(getBookEntity())).thenReturn(getBookEntity());
		int bookId = bookService.updateBook(getBookModel());
		assertThat(bookId).isEqualTo(1);

	}

	@Test
	void testDeleteBook() {
		when(mapper.mapToEntity(getBookModel())).thenReturn(getBookEntity());
		when(repository.save(getBookEntity())).thenReturn(getBookEntity());
		int bookId = bookService.deleteBook(getBookModel().getBookId());
		assertThat(bookId).isEqualTo(1);
	}

	@Test
	void testGetAllBooks() {
		List<Book> list1 = new ArrayList<>();
		list1.add(getBookModel());
		List<com.genpact.library.domain.Book> list2 = new ArrayList<>();
		list2.add(getBookEntity());
		when(mapper.mapToBook(list2)).thenReturn(list1);
		when(repository.findAll()).thenReturn(list2);
		List<Book> books = bookService.getAllBooks();
		assertThat(books).hasSize(1);
	}

	private com.genpact.library.domain.Book getBookEntity() {
		com.genpact.library.domain.Book bk = new com.genpact.library.domain.Book();
		bk.setBookId(1);
		bk.setBookName("Java");
		bk.setAuthor("Alok");
		bk.setPublication("TMH");
		return bk;
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
