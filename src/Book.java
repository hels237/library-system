

import java.util.ArrayList;

// The Book class represents a book in the library with its attributes and methods.
public class Book {
    private int id; // Book ID
    private String title; // Book title
    private String author; // Book author
    private String category; // Book category
    private int numberOfCopies; // Number of copies available

    // Constructor to initialize a new book
    public Book(int id, String title, String author, String category, int numberOfCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.numberOfCopies = numberOfCopies;
    }

    // Getters and setters for encapsulation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getNumberOfCopies() { return numberOfCopies; }
    public void setNumberOfCopies(int numberOfCopies) { this.numberOfCopies = numberOfCopies; }

    // Method to add a book to the list
    public static void addBook(ArrayList<Book> books, Book newBook) {
        books.add(newBook);
    }

    // Method to edit a book's details
    public static void editBook(ArrayList<Book> books, int id, Book updatedBook) {
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setNumberOfCopies(updatedBook.getNumberOfCopies());
                break;
            }
        }
    }

    // Method to delete a book from the list
    public static void deleteBook(ArrayList<Book> books, int id) {
        books.removeIf(book -> book.getId() == id);
    }

    // Method to search for a book by title
    public static Book searchBookByTitle(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to search for books by category
    public static ArrayList<Book> searchBookByCategory(ArrayList<Book> books, String category) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    // Method to show all available books
    public static void showAllAvailableBooks(ArrayList<Book> books) {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getCategory() + ") - Copies: " + book.getNumberOfCopies());
        }
    }
}
