package com.library.repository;

import com.library.model.BookBorrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long>{

    /*
    custom queries below
     */
    //void issueBook(String username);
}
