package domain;

public class User extends Entity<Long> {
    private String LastName;
    private String FirstName;

    public User( String lastName, String firstName) {
        super();
        LastName = lastName;
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
}
