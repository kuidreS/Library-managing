package com.vserdiuk.lm;

import com.vserdiuk.lm.entity.Book;
import com.vserdiuk.lm.service.BookService;
import org.omg.SendingContext.RunTime;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vserdiuk on 10/8/16.
 */

public class LibraryManagingApp {

    public static final String SEPARATOR = "********************************************************************************";

    static BookService bookService;
    static Scanner scanner;

    public static void main(String[] args) throws IOException {
        bookService = new BookService();
        scanner = new Scanner(System.in);
        String input;
        while (true) {
            showMainMenu();
            print(">> ");
            input = scanner.nextLine();
            switch(input) {
                case "1":
                    addBook();
                    break;
                case "2":
                    showAllBooks();
                    break;
                case "3":
                    updateBook();
                    break;
                case "4":
                    deleteBook();
                    break;
                case "5":
                    System.exit(0);
            }
        }
    }

    private static void deleteBook() throws IOException {
        cleanConsole();
        print("Enter the book name You want to delete: ");
        String bookName = scanner.nextLine();
        List<Book> books = bookService.getBooksByBookName(bookName);
        println("");
        printBooks(books);
        Book book;
        if (books.size() > 1) {
            print("Enter book number for delete: ");
            int index = scanner.nextInt() - 1;
            book = books.get(index);
        } else {
            book = books.get(0);
        }
        bookService.deleteBook(book);
    }

    private static void showAllBooks() throws IOException {
        cleanConsole();
        List<Book> books = bookService.getAllBooks();
        printBooks(books);
    }

    private static void updateBook() throws IOException {
        cleanConsole();
        print("Enter the book name You want to delete: ");
        String bookName = scanner.nextLine();
        List<Book> books = bookService.getBooksByBookName(bookName);
        println("");
        printBooks(books);
        Book book;
        if (books.size() > 1) {
            print("Enter book number for update: ");
            int index = scanner.nextInt() - 1;
            book = books.get(index);
            String author = enterAuthor();
            String bookTitle = enterBookName();
            book.setAuthor(author);
            book.setBookName(bookTitle);

        } else {
            book = books.get(0);
            String author = enterAuthor();
            String bookTitle = enterBookName();
            book.setAuthor(author);
            book.setBookName(bookTitle);
        }
        bookService.saveBook(book);
    }

    private static void printBooks(List<Book> books) {
        int index = 1;
        println(SEPARATOR);
        println("Books");
        println(SEPARATOR);
        for (Book book : books) {
            println(index + ". " + book.toString());
            index++;
        }
        println(SEPARATOR);
    }

    private static void addBook() throws IOException {
        cleanConsole();
        String author = enterAuthor();
        String bookName = enterBookName();
        bookService.addBook(author, bookName);
    }

    private static String enterAuthor() {
        println("Enter author: ");
        String author = scanner.nextLine();
        if (author.equals("")) {
            println("Author will be set up as \"Unknown\"");
        }
        return author;
    }

    private static String enterBookName() {
        println("Enter book name: ");
        String bookName = scanner.nextLine();
        return  bookName;
    }

    private static void cleanConsole() throws IOException {
        for (int i=0; i<=100; ++i) {
            println("");
        }
    }

    private static void showMainMenu() {
        println("\n1. Add new book");
        println("2. Show all books");
        println("3. Update book");
        println("4. Delete book");
        println("5. Quit");
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }
}
