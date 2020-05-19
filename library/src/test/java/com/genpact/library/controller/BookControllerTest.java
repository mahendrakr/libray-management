package com.genpact.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genpact.library.model.Book;
import com.genpact.library.service.BookService;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookService bookrService;

	@Test
	void testRetrieveAllBooks() throws Exception {
		when(bookrService.getAllBooks()).thenReturn(getMockData());
		RequestBuilder request = MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("[{bookId:1,bookName:Java,author:Alok,publication:TMH}]")).andReturn();
		JSONAssert.assertEquals("[{bookId:1,bookName:Java,author:Alok,publication:TMH}]",
				result.getResponse().getContentAsString(), true);
		DocumentContext context = JsonPath.parse(result.getResponse().getContentAsString());
		int length = context.read("$.length()");
		assertThat(length).isEqualTo(1);
		List<Integer> ids = context.read("$..bookId");
		assertThat(ids).containsExactly(1);

	}

	@Test
	void testSave() throws Exception {
		doNothing().when(bookrService).addBook(getMockData().get(0));
		Book book = getMockData().get(0);
		String data2 = new ObjectMapper().writeValueAsString(book);
		RequestBuilder request = MockMvcRequestBuilders.post("/books/save").accept(MediaType.APPLICATION_JSON)
				.content(data2).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isCreated()).andReturn();
		assertThat(result.getResponse().getContentAsString()).contains("Book added successfully");

	}

	@Test
	void testDelete() throws Exception {
		when(bookrService.deleteBook(1)).thenReturn(1);

		RequestBuilder request = MockMvcRequestBuilders.delete("/books/delete/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		assertThat(result.getResponse().getContentAsString()).contains("1");
	}

	@Test
	void testUpdate() throws Exception {
		when(bookrService.updateBook(getMockData().get(0))).thenReturn(1);
		Book book = getMockData().get(0);
		String data = new ObjectMapper().writeValueAsString(book);
		RequestBuilder request = MockMvcRequestBuilders.put("/books/update").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		assertThat(result.getResponse().getContentAsString()).contains("Book updated successfully");
	}

	private static List<Book> getMockData() {
		List<Book> books = new ArrayList<>();
		Book book = new Book();
		book.setBookId(1);
		book.setBookName("Java");
		book.setAuthor("Alok");
		book.setPublication("TMH");
		books.add(book);
		return books;
	}

}
