package com.vserdiuk.lm.service;

import com.vserdiuk.lm.entity.Book;
import com.vserdiuk.lm.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vserdiuk on 10/9/16.
 */

@Service
public class BookService {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookRepository bookRepository = context.getBean(BookRepository.class);

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void addBook(String author, String bookName) {
        Book book;
        if (author.equals("")) {
            book = new Book.BookBuilder()
                    .buildBookName(bookName)
                    .build();
        } else {
            book = new Book.BookBuilder()
                    .buildAuthor(author)
                    .buildBookName(bookName)
                    .build();
        }
        bookRepository.save(book);
        System.out.println("Book " + book.getAuthor() + " \"" + book.getBookName() + "\" " + "has been added");
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
        System.out.println("Book " + book.getAuthor() + " \"" + book.getBookName() + "\" " + "has been deleted");
    }

    private Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }

}
