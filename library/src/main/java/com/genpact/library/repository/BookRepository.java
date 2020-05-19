package com.genpact.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
