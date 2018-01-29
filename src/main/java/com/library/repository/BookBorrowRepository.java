package com.library.repository;

import com.library.model.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Tworzy repozytorium danych z klasy BookBorrow
 */
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long>{

    /*
    custom queries below
     */
    //void issueBook(String username);
}
