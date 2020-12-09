package Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.sun.jdi.request.DuplicateRequestException;
import model.Customer;

/**
 * @author shubham sharma
 *         <p>
 *         03/10/20
 */
public class CustomerService {
    
    private static List<Customer> customers = new ArrayList<>();
    private static List<String> emails = new ArrayList<>();
    
    public static final CustomerService customerService = new CustomerService();
    
    
    public void addCustomer(String email, String firstName, String lastName){
        if (Objects.isNull(email) || "".equals(email.trim())) {
            throw new IllegalArgumentException("Invalid email passed for customer");
        }
        if(emails.contains(email)) {
            throw new DuplicateRequestException("Customer with email " + email + " already exists");
        }
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
        emails.add(email);
    }
    
    public Customer getCustomer(String customerEmail){
        return customers.stream()
                        .filter(customer -> customer.getEmail().equalsIgnoreCase(customerEmail))
                        .findFirst()
                        .orElseThrow(() -> new NoSuchElementException("Customer not found with email " + customerEmail));
    }
    
    public Collection<Customer> getAllCustomers(){
        return customers;
    }
    
    public static CustomerService getInstance() {
        return customerService;
    }
}
