package model;

import java.util.regex.Pattern;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class Customer {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    private String firstName;
    private String lastName;
    private String email;
    
    public Customer(String firstName, String lastName, String email) {
        if(!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
            throw new IllegalArgumentException("Invalid email passed for customer");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Customer{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email
                + '\'' + '}';
    }
}
