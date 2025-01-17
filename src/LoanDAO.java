
import JDBC.DB;

import java.sql.*;
import java.util.Date;
import java.time.LocalDate;


// Data Access Object (DAO) class for Loan
public class LoanDAO {
    // Method to record a new loan in the database
    public static void recordLoan(Loan loan) {
        String query = "INSERT INTO loans (memberId, bookId, dateLoan, dateReturnPreview, effectiveReturnDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loan.getMemberId()); // Set memberId parameter
            statement.setInt(2, loan.getBookId()); // Set bookId parameter
            statement.setDate(3, java.sql.Date.valueOf(loan.getDateLoan())); // Set dateLoan parameter
            statement.setDate(4, java.sql.Date.valueOf(loan.getDateReturnPreview())); // Set dateReturnPreview parameter
            if (loan.getEffectiveReturnDate() != null) {
                statement.setDate(5, java.sql.Date.valueOf(loan.getEffectiveReturnDate())); // Set effectiveReturnDate parameter
            } else {
                statement.setNull(5, Types.DATE); // Set effectiveReturnDate parameter to null
            }
            statement.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to manage the return of a book by updating the effective return date
    public static void manageReturn(int idLoan, LocalDate returnDate) {
        String query = "UPDATE loans SET effectiveReturnDate = ? WHERE id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(returnDate)); // Set effectiveReturnDate parameter
            statement.setInt(2, idLoan); // Set idLoan parameter
            statement.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to calculate penalties for late returns
    public static void calculatePenalties() {
        String query = "SELECT id, dateReturnPreview, effectiveReturnDate FROM loans WHERE effectiveReturnDate IS NULL OR effectiveReturnDate > dateReturnPreview";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idLoan = resultSet.getInt("id");
                Date dateReturnPreview = resultSet.getDate("dateReturnPreview");
                Date effectiveReturnDate = resultSet.getDate("effectiveReturnDate");
                long daysLate;

                // If the book has not been returned, use the current date for penalty calculation
                if (effectiveReturnDate == null) {
                    daysLate = (new Date().getTime() - dateReturnPreview.getTime()) / (1000 * 60 * 60 * 24);
                } else {
                    daysLate = (effectiveReturnDate.getTime() - dateReturnPreview.getTime()) / (1000 * 60 * 60 * 24);
                }

                if (daysLate > 0) {
                    System.out.println("Loan ID: " + idLoan + " is " + daysLate + " days late. Penalty: " + daysLate * 100 + " CFA.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
