package com.library.repository;

import com.library.model.Book;
import com.library.model.BookBorrow;
import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Tworzy repozytorium danych z klasy BookBorrow
 */
public interface BookBorrowRepository extends JpaRepository<BookBorrow, Long>{



    List<BookBorrow> findByUser(User user);
    //BookBorrow findByUser(User username);
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

    @Query(value = "SELECT Book.name,BookBorrow.startDate,BookBorrow.endDate,DATEDIFF(BookBorrow.endDate,BookBorrow.startDate) FROM Book,BookBorrow,User WHERE BookBorrow.book_bookID=Book.bookID AND BookBorrow.user_userID=User.userID AND User.userID=?1",nativeQuery = true)
    List<String> borrowedBooks(Long userID);


    @Query(value = "SELECT Book.name FROM BookBorrow,User,Book WHERE BookBorrow.book_bookID=Book.bookID AND BookBorrow.user_userID=User.userID AND User.userID=?1",nativeQuery = true)
    ArrayList<String> bookNames(Long userID);

    @Query(value = "SELECT Book.author FROM BookBorrow,User,Book WHERE BookBorrow.book_bookID=Book.bookID AND BookBorrow.user_userID=User.userID AND User.userID=?1",nativeQuery = true)
    ArrayList<String> bookAuthors(Long userID);

    @Query("select b.name, b.author, bb.endDate,\n" +
            "u.firstname, u.surname\n" +
            "from BookBorrow bb\n" +
            "inner join Book b \n" +
            "\ton bb.book.bookID = b.bookID\n" +
            "inner join User u\n" +
            "\ton bb.user.userID = u.userID")
    List<Book> findAllBorrowsBooksUsers();
}
