

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

// The models.Member class represents a member of the library with their attributes and methods.
public class Member extends Person {
    private int id; // models.Member ID
    private LocalDate membershipDate; // Membership date

    // Constructor to initialize a new member
    public Member(int id, String firstName, String lastName, String email, LocalDate membershipDate) {
        super(firstName, lastName, email);
        this.id = id;
        this.membershipDate = membershipDate;
    }

    // Getters and setters for encapsulation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getMembershipDate() { return membershipDate; }
    public void setMembershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; }

    // Method to register a new member
    public static void registerMember(ArrayList<Member> members, Member newMember) {
        members.add(newMember);
    }

    // Method to delete a member from the list
    public static void deleteMember(ArrayList<Member> members, int id) {
        members.removeIf(member -> member.getId() == id);
    }

    // Method to search for a member by name
    public static Member searchMemberByName(ArrayList<Member> members, String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }
}
