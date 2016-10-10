package com.vserdiuk.lm.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vserdiuk on 10/8/16.
 */

@Entity
@Table(name="books")
public class Book implements Serializable {

    private static final long serialVersionUID = 3703705978331623868L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "book_name", length = 255, nullable = false)
    private String bookName;

    @Column(name = "author", length = 255)
    private String author;

    public Book() {

    }

    public Book(BookBuilder bookBuilder) {
        this.id = bookBuilder.id;
        this.bookName = bookBuilder.bookName;
        this.author = bookBuilder.author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (bookName != null ? !bookName.equals(book.bookName) : book.bookName != null) return false;
        return author != null ? author.equals(book.author) : book.author == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.author + " \"" + this.bookName + "\"";
    }

    public static class BookBuilder {
        private Long id;
        private String bookName;
        private String author = "Unknown";

        public BookBuilder buildId(Long id) {
            this.id = id;
            return this;
        }

        public BookBuilder buildBookName(String bookName) {
            this.bookName = bookName;
            return this;
        }

        public BookBuilder buildAuthor(String author) {
            this.author = author;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
