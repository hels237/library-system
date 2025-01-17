

import JDBC.*;

import java.sql.*;
import java.util.ArrayList;

// Data Access Object (DAO) class for Book
public class BookDAO {
    // Method to add a book to the database
    public static void addBook(Book book) {
        String query = "INSERT INTO books (title, author, category, number_of_copies) VALUES (?, ?, ?, ?)";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setInt(4, book.getNumberOfCopies());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to edit a book's details in the database
    public static void editBook(ArrayList<Book> books, int id, Book updatedBook) {
        String query = "UPDATE books SET title = ?, author = ?, category = ?, number_of_copies = ? WHERE id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedBook.getTitle());
            statement.setString(2, updatedBook.getAuthor());
            statement.setString(3, updatedBook.getCategory());
            statement.setInt(4, updatedBook.getNumberOfCopies());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a book from the database
    public static void deleteBook(ArrayList<Book> books, int id) {
        String deleteLoansQuery = "DELETE FROM loans WHERE bookId = ?";
        String deleteBookQuery = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement deleteLoansStatement = connection.prepareStatement(deleteLoansQuery);
             PreparedStatement deleteBookStatement = connection.prepareStatement(deleteBookQuery)) {
            // Delete related loans
            deleteLoansStatement.setInt(1, id);
            deleteLoansStatement.executeUpdate();

            // Delete the book
            deleteBookStatement.setInt(1, id);
            deleteBookStatement.executeUpdate();

            System.out.println("Book and related loans deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a book by title
    public static Book searchBookByTitle(String title) {
        String query = "SELECT * FROM books WHERE title = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getInt("number_of_copies")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to search for books by category
    public static ArrayList<Book> searchBookByCategory(ArrayList<Book> books, String category) {
        String query = "SELECT * FROM books WHERE category = ?";
        //ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getInt("number_of_copies")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Method to show all available books
    public static ArrayList<Book> getAllBooks() {
        String query = "SELECT * FROM books";
       ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("category"),
                        resultSet.getInt("number_of_copies")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void showAllAvailableBooks(ArrayList<Book> books) {
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getCategory() + ") - Copies: " + book.getNumberOfCopies());
        }
    }

}
