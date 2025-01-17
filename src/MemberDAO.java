

import java.sql.*;

import JDBC.DB;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Data Access Object (DAO) class for Member
public class MemberDAO {
    // Method to register a new member in the database
    public static void registerMember(Member member) {
        String query = "INSERT INTO members (firstName, lastName, email, membership_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DB.connect() ;
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, member.getName()); // Set name parameter
            statement.setString(2, member.getFirstName()); // Set first name parameter
            statement.setString(3, member.getEmail()); // Set email parameter
            statement.setDate(4, Date.valueOf(member.getMembershipDate())); // Set membership date parameter
            statement.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a member from the database
    public static void deleteMember(int id) {
        String query = "DELETE FROM members WHERE id = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id); // Set id parameter
            statement.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to search for a member by name
    public static Member searchMemberByName(String name) {
        String query = "SELECT * FROM members WHERE firstName = ?";
        try (Connection connection = DB.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name); // Set name parameter
            ResultSet resultSet = statement.executeQuery(); // Execute the query
            if (resultSet.next()) {
                return new Member(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("email"),
                        resultSet.getDate("membership_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

