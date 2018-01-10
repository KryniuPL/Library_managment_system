package com.library.repository;


import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{

    /*
    custom queries below
     */
}
