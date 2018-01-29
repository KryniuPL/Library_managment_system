package com.library.repository;


import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Tworzy repozytorium danych z klasy Book
 */
public interface BookRepository extends JpaRepository<Book, Long>{

    /**
     * Zapytanie wyszukujące książkę w księgozbiorze
     */
    @Query("select b from Book b where b.name like %?1% OR b.author like %?1% OR b.isbn like %?1%")
    List<Book> findPhrase(String phrase);

    Book findByName(String name);
    Book findByBookID(Long id);
    /**
     * Zapytanie aktualizujące książkę w księgozbiorze
     */
    @Modifying
    @Transactional
    @Query("update Book b set b.author = ?1, b.name = ?2, b.quantity = ?3, b.isbn = ?4 where b.bookID = ?5")
    void update(String author, String name, Long quantity, String isbn, Long ID);

    /**
     * Zapytanie aktualizujące status książki w księgozbiorze
     */
    @Modifying
    @Transactional
    @Query("update Book b set b.status=?1 where b.bookID=?2")
    void updateStatus(String status,Long id);

    /**
     * Zapytanie sortujące książki po nazwie rosnąco
     */
    @Query("select b from Book b order by b.name asc")
    List<Book> orderByNameAsc();

    /**
     * Zapytanie sortujące książki po nazwie malejąco
     */
    @Query("select b from Book b order by b.name desc")
    List<Book> orderByNameDesc();

    /**
     * Zapytanie sortujące książki po autorze rosnąco
     */
    @Query("select b from Book b order by b.name asc")
    List<Book> orderByAuthorAsc();

    /**
     * Zapytanie sortujące książki po autorze malejąco
     */
    @Query("select b from Book b order by b.name desc")
    List<Book> orderByAuthorDesc();

    /**
     * Zapytanie sortujące książki po cenie rosnąco
     */
    @Query("select b from Book b order by b.name asc")
    List<Book> orderByPriceAsc();

    /**
     * Zapytanie sortujące książki po cenie malejąco
     */
    @Query("select b from Book b order by b.name desc")
    List<Book> orderByPriceDesc();

    /**
     * TBA
     */
    @Query("select b.quantity from Book b where b.name = ?1 AND b.author = ?2 AND b.isbn = ?3")
    Long getQuantity(String name, String author, String isbn);

    /**
     * TBA
     */
    @Query("select b from Book b where b.name = ?1 AND b.author = ?2 AND b.isbn = ?3")
    Book getIdenticalLike(String name, String author, String isbn);

}
