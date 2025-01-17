

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

// The models.Loan class represents a loan of a book to a member with its attributes and methods.
public class Loan {
    private int idLoan; // Unique identifier for the loan
    private int memberId; // ID of the member who borrowed the book
    private int bookId; // ID of the book that is borrowed
    private LocalDate dateLoan; // Date when the loan was made
    private LocalDate dateReturnPreview; // Expected return date of the book
    private LocalDate effectiveReturnDate; // Actual return date of the book

    // Constructor to initialize a new loan
    public Loan(int idLoan, int memberId, int bookId, LocalDate dateLoan, LocalDate dateReturnPreview, LocalDate effectiveReturnDate) {
        this.idLoan = idLoan;
        this.memberId = memberId;
        this.bookId = bookId;
        this.dateLoan = dateLoan;
        this.dateReturnPreview = dateReturnPreview;
        this.effectiveReturnDate = effectiveReturnDate;
    }

    // Getters and setters for encapsulation
    public int getIdLoan() { return idLoan; }
    public void setIdLoan(int idLoan) { this.idLoan = idLoan; }

    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public LocalDate getDateLoan() { return dateLoan; }
    public void setDateLoan(LocalDate dateLoan) { this.dateLoan = dateLoan; }

    public LocalDate getDateReturnPreview() { return dateReturnPreview; }
    public void setDateReturnPreview(LocalDate dateReturnPreview) { this.dateReturnPreview = dateReturnPreview; }

    public LocalDate getEffectiveReturnDate() { return effectiveReturnDate; }
    public void setEffectiveReturnDate(LocalDate effectiveReturnDate) { this.effectiveReturnDate = effectiveReturnDate; }

    // Method to record a new loan
    public static void recordLoan(ArrayList<Loan> loans, Loan newLoan) {
        loans.add(newLoan);
    }

    // Method to manage the return of a book by updating the effective return date
    public static void manageReturn(ArrayList<Loan> loans, int idLoan, LocalDate returnDate) {
        for (Loan loan : loans) {
            if (loan.getIdLoan() == idLoan) {
                loan.setEffectiveReturnDate(returnDate);
                break;
            }
        }
    }

    // Method to calculate penalties for late returns
    public static void calculatePenalties(ArrayList<Loan> loans) {
        for (Loan loan : loans) {
            if (loan.getEffectiveReturnDate() == null) {
                // Calculate penalty based on current date and dateReturnPreview
                long daysLate = (LocalDate.now().toEpochDay() - loan.getDateReturnPreview().toEpochDay());
                if (daysLate > 0) {
                    System.out.println("Loan ID: " + loan.getIdLoan() + " is " + daysLate + " days late. Penalty: " + daysLate * 100 + " CFA.");
                } else {
                    daysLate = loan.getEffectiveReturnDate().toEpochDay() - loan.getDateReturnPreview().toEpochDay();
                    if (daysLate > 0) {
                        System.out.println("Loan ID: " + loan.getIdLoan() + " is " + daysLate + " days late. Penalty: " + daysLate * 100 + " CFA.");
                    }
                }
            }
        }
    }
}
