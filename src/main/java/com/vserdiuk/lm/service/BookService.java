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
        System.out.println("Book " + book.getAuthor() + " \"" + book.getBookName() + "\" " + "has been deleted");
    }

    public void addBook(String inputBook) {
        String[] splitedBook = splitBookByAuthorAndTitle(inputBook);
        Book book = getBookFromArray(splitedBook);
        bookRepository.save(book);
        System.out.println("Book " + book.getAuthor() + " \"" + book.getBookName() + "\" " + "has been added");
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    private Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }
    
    public String[] splitBookByAuthorAndTitle(String inputBook) {
        String[] splitedBook = inputBook.split(" \"");
        if(splitedBook.length > 1) {
            splitedBook[1] = splitedBook[1].substring(0, splitedBook[1].length()-1);
        } else {
            splitedBook[0] = splitedBook[0].substring(1);
            splitedBook[0] = splitedBook[0].substring(0, splitedBook[0].length()-1);
        }
        return splitedBook;
    }

    private Book getBookFromArray(String[] splitedBook) {
        int arrayLength = splitedBook.length;
        Book book;
        if (arrayLength > 1) {
            book = new Book.BookBuilder()
                    .buildByAuthor(splitedBook[0])
                    .buildByBookName(splitedBook[1])
                    .build();
        } else {
            book = new Book.BookBuilder()
                    .buildByBookName(splitedBook[0])
                    .build();
        }
        return book;
    }

}
