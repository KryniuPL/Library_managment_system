package com.library.repository;

import com.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{

    /*
    Custom queries below
     */

    User findByUsername(String username);

    @Query("select u from User u where u.firstname like %?1% OR u.surname like %?1% OR u.username like %?1% OR u.email like %?1%")
    List<User> findPhrase(String phrase);

    @Modifying
    @Transactional
    @Query("update User u set u.firstname=?1, u.surname=?2, u.username=?3, u.password=?4, u.email=?5 where u.userID=?6")
    void update(String firstname, String surname, String username, String password, String email, Long id);

}
