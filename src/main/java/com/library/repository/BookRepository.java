package com.library.repository;


import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    /*
    custom queries below
     */
    @Query("select b from Book b where b.name like %?1% OR b.author like %?1%")
    List<Book> findPhrase(String phrase);

}
