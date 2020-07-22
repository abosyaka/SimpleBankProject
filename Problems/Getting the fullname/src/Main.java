
class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        this.firstName = java.util.Objects.requireNonNullElse(firstName, "");
    }

    public void setLastName(String lastName) {
        // write your code here
        this.lastName = java.util.Objects.requireNonNullElse(lastName, "");
    }

    public String getFullName() {
        if (firstName.isEmpty() && lastName.isEmpty()) return "Unknown";
        return (firstName + " " + lastName).trim(); // write your code here
    }
}