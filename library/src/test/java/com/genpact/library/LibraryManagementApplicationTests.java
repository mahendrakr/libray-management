package com.genpact.library;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genpact.library.model.Book;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class LibraryManagementApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() throws Exception {
		// Update book details where bookId =1
		restTemplate.put("/books/update", getMockData());

		// Add a new book to library
		Book request = getMockData();
		request.setBookId(10);
		ResponseEntity<String> result = restTemplate.postForEntity("/books/save", request, String.class);
		assertThat(result.getBody()).contains("Book added successfully");
		assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		/**
		 * At application start up time there are 5 books in the library. Now we added a
		 * new book to the library so total book count in the library will be 6. Also we
		 * updated the book details with Mock data which bookId =1. We can verify
		 * updated records by fetching all the records from the database.
		 */
		List res = restTemplate.getForObject("/books", List.class);
		String data = new ObjectMapper().writeValueAsString(res);
		DocumentContext context = JsonPath.parse(data);
		int i = context.read("$.length()");
		assertThat(i).isEqualTo(6);
		List<Integer> ids = context.read("$..bookId");
		assertThat(ids).containsExactly(1, 2, 3, 4, 5, 10);
		List<String> authors = context.read("$..author");
		assertThat(authors.get(0)).isEqualTo("Alok");

		// We are deleting a book which bookId =5.
		restTemplate.delete("/books/delete/5");
		
        // And to verify we are fetching all the records from the database.
		List response = restTemplate.getForObject("/books", List.class);
		String jsonData = new ObjectMapper().writeValueAsString(response);
		DocumentContext context1 = JsonPath.parse(jsonData);
		List<Integer> bookIds = context1.read("$..bookId");
		assertThat(bookIds).containsExactly(1, 2, 3, 4, 10);
		assertThat(bookIds).doesNotContain(5);

	}

	private static Book getMockData() {
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("Java");
		book.setAuthor("Alok");
		book.setPublication("TMH");
		return book;
	}

}
