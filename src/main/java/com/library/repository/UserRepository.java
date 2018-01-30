package com.library.repository;

import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Tworzy repozytorium danych z klasy User
 */
public interface UserRepository extends JpaRepository<User, Long>{

    /**
     * Zapytanie zwracające obiekt User po pobraniu nazwy użytkownika
     */
    User findByUsername(String username);

    User findByUserID(Long id);
    /**
     * Zapytanie znajdujące użytkownika po nazwie
     */
    @Query("select u from User u where u.firstname like %?1% OR u.surname like %?1% OR u.username like %?1% OR u.email like %?1%")
    List<User> findPhrase(String phrase);

    /**
     * Zapytanie aktualizujące dane użytkownika
     */
    @Modifying
    @Transactional
    @Query("update User u set u.firstname=?1, u.surname=?2, u.username=?3, u.password=?4, u.email=?5 where u.userID=?6")
    void update(String firstname, String surname, String username, String password, String email, Long id);

    /**
     * Zapytanie wyświetlające liczbę wypożyczonych książek do profilu użytkownika
     */
    @Query(value = "SELECT COUNT(BookBorrow.borrowID) from BookBorrow,User WHERE BookBorrow.user_userID=User.userID and User.username=?1",nativeQuery = true)
    int books(String username);


    /**
     * Zapytanie zwracające nazwy wypozyczonych ksiazek przez użytkownika
     */
    @Query(value = "SELECT Book.name FROM BookBorrow,User,Book WHERE BookBorrow.book_bookID=Book.bookID AND BookBorrow.user_userID=User.userID AND User.username=?1",nativeQuery =true)
    List<String> userBooks(String username);
}
