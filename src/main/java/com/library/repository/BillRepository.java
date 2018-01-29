package com.library.repository;

import com.library.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface BillRepository extends JpaRepository<Bill,Long>
{
    @Query(value = "SELECT BookBorrow.endDate FROM BookBorrow,User,Book WHERE BookBorrow.book_bookID=Book.bookID AND BookBorrow.user_userID=User.userID AND User.username=?1 AND Book.name=?2",nativeQuery = true)
    Date endDate(String username, String bookname);

}
