package com.library.repository;

import com.library.model.BookBorrow;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tworzy repozytorium danych z klasy BookBorrow
 */
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long>{


    BookBorrow findByUser(User username);
    /*
    custom queries below
     */
    //void issueBook(String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE from BookBorrow where BookBorrow.borrowID=?1",nativeQuery = true)
    void deleteByBorrowID(Long id);


    @Query(value = "SELECT BookBorrow.borrowID FROM BookBorrow,User,Book WHERE BookBorrow.user_userID=User.userID AND BookBorrow.book_bookID=Book.bookID AND Book.bookID=?1",nativeQuery = true)
    Long findBorrowIDByBookID(Long bookID);
}
