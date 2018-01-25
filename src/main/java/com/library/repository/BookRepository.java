package com.library.repository;


import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{

    /*
    custom queries below
     */
    @Query("select b from Book b where b.name like %?1% OR b.author like %?1% OR b.isbn like %?1%")
    List<Book> findPhrase(String phrase);

    @Modifying
    @Transactional
    @Query("update Book b set b.author = ?1, b.name = ?2, b.quantity = ?3, b.isbn = ?4 where b.bookID = ?5")
    void update(String author, String name, Long quantity, String isbn, Long ID);

    @Query("select b from Book b order by b.name asc")
    List<Book> orderByNameAsc();

    @Query("select b from Book b order by b.name desc")
    List<Book> orderByNameDesc();

    @Query("select b from Book b order by b.name asc")
    List<Book> orderByAuthorAsc();

    @Query("select b from Book b order by b.name desc")
    List<Book> orderByAuthorDesc();

    @Query("select b from Book b order by b.name asc")
    List<Book> orderByPriceAsc();

    @Query("select b from Book b order by b.name desc")
    List<Book> orderByPriceDesc();

    @Query("select b.quantity from Book b where b.name = ?1 AND b.author = ?2 AND b.isbn = ?3")
    Long getQuantity(String name, String author, String isbn);

    @Query("select b from Book b where b.name = ?1 AND b.author = ?2 AND b.isbn = ?3")
    Book getIdenticalLike(String name, String author, String isbn);

}
