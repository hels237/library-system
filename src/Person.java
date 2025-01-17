

public class Person {
    private String firstName; // models.Member name
    private String lastName; // models.Member first name
    private String email; // models.Member email


    public Person(String name, String firstName, String email) {
        this.firstName = name;
        this.lastName = firstName;
        this.email = email;
    }

    // Getters and setters


    public String getName() { return firstName; }
    public void setName(String firstName) { this.firstName = firstName; }

    public String getFirstName() { return lastName; }
    public void setFirstName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}
