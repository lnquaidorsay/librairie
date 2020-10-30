package com.librairie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librairie.domain.security.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContaining(String keyword);
}
