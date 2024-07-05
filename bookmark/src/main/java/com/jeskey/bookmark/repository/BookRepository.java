package com.jeskey.bookmark.repository;


import com.jeskey.bookmark.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

    boolean existsById(String isbn);
}
