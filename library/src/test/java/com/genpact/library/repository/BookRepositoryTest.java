package com.genpact.library.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.genpact.library.domain.Book;

@DataJpaTest
class BookRepositoryTest {
	@Autowired
	private BookRepository repository;
	@Test
	public void testFindAll() {
		List<Book> books = repository.findAll();
		assertThat(books).hasSize(5);
		assertThat(books).anyMatch(book->book.getBookName().equals("H C Verma"));
	}
	
	@Test
	public void testSave() {
		Book book = new Book();
		book.setBookId(6);
		book.setBookName("Algebra");
		book.setAuthor("K C Sinha");
		book.setPublication("Student friend");
		 repository.save(book);
		 List<Book> books = repository.findAll();
		assertThat(books).hasSize(6);
		assertThat(books).anyMatch(bk->bk.getAuthor().equals("K C Sinha"));
	}
	
	@Test
	public void testUpdate() {
		Book book = new Book();
		book.setBookId(6);
		book.setBookName("Algebra");
		book.setAuthor("R D Sharama");
		book.setPublication("Student friend");
		 repository.save(book);
		 List<Book> books = repository.findAll();
		assertThat(books).hasSize(6);
		assertThat(books).anyMatch(bk->bk.getAuthor().equals("R D Sharama"));
	}
	
	@Test
	public void testDelete() {
		repository.deleteById(1);
		 List<Book> books = repository.findAll();
		assertThat(books).hasSize(4);
		
	}
	
	

}
