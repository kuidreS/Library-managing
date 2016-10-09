package com.vserdiuk.lm;

import com.vserdiuk.lm.entity.Book;
import com.vserdiuk.lm.service.BookService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vserdiuk on 10/8/16.
 */

public class LibraryManagingApp {

    public static void main(String[] args) throws IOException {
        BookService service = new BookService();
        Scanner scanner = new Scanner(System.in);
        String choise;

        showMainMenu();
        choise = scanner.nextLine();

        if (choise.equals("1")){
            showAddUpdateBookMenu();
            System.out.print("New book: ");
            String newBook = scanner.nextLine();
            service.addBook(newBook);
        }
        if (choise.equals("2")) {
            List<Book> allBooks = service.getAllBooks();
            printBooks(allBooks);
            System.out.println("--------------------------------------------------------------------------------");
            showDeleteUpdateMenu();
            choise = scanner.nextLine();
            if (choise.equals("1")) {
                System.out.print("Book name You want to change: ");
                choise = scanner.nextLine();

                List<Book> booksByName = service.getBooksByBookName(choise);
                printBooks(booksByName);

                System.out.print("Please enter book number You want to update: ");
                choise = scanner.nextLine();

                int index = Integer.parseInt(choise);
                Book bookForUpdate = booksByName.get(index - 1);

                showAddUpdateBookMenu();

                System.out.print("Update book: ");
                String updateBook = scanner.nextLine();
                String[] authorAndBookName = service.splitBookByAuthorAndTitle(updateBook);

                if(authorAndBookName.length > 1) {
                    bookForUpdate.setAuthor(authorAndBookName[0]);
                    bookForUpdate.setBookName(authorAndBookName[1]);
                } else {
                    bookForUpdate.setBookName(authorAndBookName[0]);
                }
                service.saveBook(bookForUpdate);
            }
            if (choise.equals("2")) {
                System.out.print("Book name You want to delete: ");
                choise = scanner.nextLine();

                List<Book> booksByName = service.getBooksByBookName(choise);
                printBooks(booksByName);

                System.out.print("Please enter book number You want to delete: ");
                choise = scanner.nextLine();

                int index = Integer.parseInt(choise);
                Book bookForDelete = booksByName.get(index - 1);
                service.deleteBook(bookForDelete);
            }
        }
    }

    private static void showAddUpdateBookMenu() {
        System.out.println("Inpput new book and press Enter");
        System.out.println("Book format could be:");
        System.out.println("book_author \"book_name\"");
        System.out.println("\"book_name\"");
    }

    private static void printBooks(List<Book> books) {
        int i = 1;
        for (Book book : books) {
            System.out.println(i + ". " + book);
            i++;
        }
    }

    private static void showMainMenu() {
        System.out.println("1. Add book");
        System.out.println("2. Show all books \n");
        System.out.print("Your coise: ");
    }

    private static void showDeleteUpdateMenu() {
        System.out.println("1. Update book");
        System.out.println("2. Delete book \n");
        System.out.print("Your coise: ");
    }

}
