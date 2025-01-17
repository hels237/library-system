import JDBC.DB;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (var connection =  DB.connect()){
            System.out.println("Connected to the PostgreSQL database.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n");

        System.out.println("Welcome to my Library console application.!");

        // Initialize ArrayLists to store books, members, and loans
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();
        ArrayList<Loan> loans = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        // Main menu loop
        while (true) {
            // Display the main menu
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Edit Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book by Title");
            System.out.println("5. Search Book by Category");
            System.out.println("6. Show All Available Books");
            System.out.println("7. Register Member");
            System.out.println("8. Delete Member");
            System.out.println("9. Search Member by Name");
            System.out.println("10. Record Loan");
            System.out.println("11. Manage Return");
            System.out.println("12. Calculate Penalties");
            System.out.println("13. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Book
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter number of copies: ");
                    int numberOfCopies = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Book newBook = new Book(bookId, title, author, category, numberOfCopies);
                    BookDAO.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    // Edit Book
                    System.out.print("Enter book ID to edit: ");
                    int editBookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scanner.nextLine();
                    System.out.print("Enter new number of copies: ");
                    int newNumberOfCopies = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    Book updatedBook = new Book(editBookId, newTitle, newAuthor, newCategory, newNumberOfCopies);
                    BookDAO.editBook(books, editBookId, updatedBook);
                    System.out.println("Book edited successfully.");
                    break;

                case 3:
                    // Delete Book
                    System.out.print("Enter book ID to delete: ");
                    int deleteBookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    BookDAO.deleteBook(books, deleteBookId);
                    System.out.println("Book deleted successfully.");
                    break;

                case 4:
                    // Search Book by Title
                    System.out.print("Enter title: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = BookDAO.searchBookByTitle(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook.getTitle() + " by " + foundBook.getAuthor());
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 5:
                    // Search Book by Category
                    System.out.print("Enter category: ");
                    String searchCategory = scanner.nextLine();
                    ArrayList<Book> booksByCategory = BookDAO.searchBookByCategory(books, searchCategory);
                    if (booksByCategory.isEmpty()) {
                        System.out.println("No books found in this category.");
                    } else {
                        for (Book book : booksByCategory) {
                            System.out.println("Book: " + book.getTitle() + " by " + book.getAuthor());
                        }
                    }
                    break;

                case 6:
                    // Show All Available Books
                    ArrayList<Book> availableBooks = BookDAO.getAllBooks();

                    if (availableBooks.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        BookDAO.showAllAvailableBooks(availableBooks);
                    }
                    break;


                case 7:
                    // Register Member

                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter membership date (yyyy-mm-dd): ");
                    String membershipDateString = scanner.nextLine();

                    LocalDate membershipDate = null;

                    try {
                        membershipDate = LocalDate.parse(membershipDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid membership date format. Please enter the date in the following format: yyyy-mm-dd");
                        break;
                    }

                    Member newMember = new Member(memberId, firstName, lastName, email, membershipDate);
                    MemberDAO.registerMember(newMember);
                    System.out.println("Member registered successfully.");
                    break;

                case 8:
                    // Delete Member
                    System.out.print("Enter member ID to delete: ");
                    int deleteMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    MemberDAO.deleteMember(deleteMemberId);
                    System.out.println("Member deleted successfully.");
                    break;

                case 9:
                    // Search Member by Name
                    System.out.print("Enter firstName: ");
                    String searchName = scanner.nextLine();
                    Member foundMember = MemberDAO.searchMemberByName(searchName);
                    if (foundMember != null) {
                        System.out.println("Member found: " + foundMember.getName() + " " + foundMember.getFirstName());
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;

                case 10:
                    // Record Loan

                    System.out.print("Enter loan ID: ");
                    int loanId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter member ID: ");
                    int loanMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book ID: ");
                    int loanBookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter loan date (yyyy-mm-dd): ");
                    String loanDateString = scanner.nextLine();

                    LocalDate loanDate = null;
                    try {
                        loanDate = LocalDate.parse(loanDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date. Please enter the date in the format yyyy-mm-dd.");
                        break;
                    }

                    // Create a new loan return object
                    System.out.print("Enter return preview date (yyyy-mm-dd): ");

                    String returnPreviewDateString = scanner.nextLine();

                    LocalDate returnPreviewDate = null;

                    try {
                        returnPreviewDate = LocalDate.parse(returnPreviewDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date. Please enter the date in the format yyyy-mm-dd.");
                        break;
                    }
                    Loan newLoan = new Loan(loanId, loanMemberId, loanBookId, loanDate, returnPreviewDate, null);
                    LoanDAO.recordLoan(newLoan);
                    System.out.println("Loan recorded successfully.");
                    break;

                case 11:
                    // Manage Return
                    System.out.print("Enter loan ID to manage return: ");
                    int manageLoanId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter actual return date (yyyy-mm-dd): ");
                    String returnDateString = scanner.nextLine();
                    LocalDate returnDate = null;
                    try {
                        returnDate = LocalDate.parse(returnDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid date. Please enter the date in the format yyyy-mm-dd.");
                        break;
                    }

                    LoanDAO.manageReturn(manageLoanId, returnDate);
                    System.out.println("Return managed successfully.");
                    break;

                case 12:
                    // Calculate Penalties
                    LoanDAO.calculatePenalties();
                    break;

                case 13:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}