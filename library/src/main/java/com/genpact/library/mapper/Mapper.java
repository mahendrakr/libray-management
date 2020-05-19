package com.genpact.library.mapper;

import java.util.List;

import com.genpact.library.domain.Book;

public interface Mapper {
public List<Book>  mapToEntity(List<com.genpact.library.model.Book> books);
public Book mapToEntity(com.genpact.library.model.Book book);
public List<com.genpact.library.model.Book> mapToBook(List<Book> books);
public com.genpact.library.model.Book mapToBook(Book book);
}