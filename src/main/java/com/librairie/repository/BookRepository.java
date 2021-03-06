package com.librairie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.librairie.domain.security.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContaining(String keyword);

	@Query("SELECT b FROM Book b WHERE b.id = ?1")
	Book chercherLivre(Long id);
}
