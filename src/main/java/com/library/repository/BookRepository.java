package com.library.repository;


import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    /*
    custom queries below
     */
    @Query("select b from Book b where b.name like %?1% OR b.author like %?1%")
    List<Book> findPhrase(String phrase);

    @Modifying
    @Transactional
    @Query("update Book b set b.author = ?1, b.name = ?2, b.price = ?3 where b.bookID = ?4")
    void update(String author, String name, Integer price, Long ID);

}
