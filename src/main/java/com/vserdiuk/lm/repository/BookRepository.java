package com.vserdiuk.lm.repository;

import com.vserdiuk.lm.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vserdiuk on 10/8/16.
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book order by book.bookName")
    List<Book> findAll();

    List<Book> findByBookName(String bookName);

}
