/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CustomerBean;
import EJB.EmailBean;
import EJB.EmployeeBean;
import EJB.security.GroupBean;
import EJB.security.UserBean;
import Model.Address;
import Model.Customer;
import Model.Employee;
import Model.Orders;
import Model.security.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Manon
 */
@Named
@RequestScoped
public class CustomerController extends AbstractController {

    @EJB
    private EmployeeBean employeeBean;

    @EJB
    private EmailBean emailBean;

    @EJB
    private GroupBean groupBean;

    @EJB
    private UserBean userBean;

    @EJB
    private CustomerBean customerBean;

    private User user;
    private Customer customer;
    private Address address;

    private String message;

    @NotNull(message = "Please enter your username")
    private String login;

    @Size(min = 6, message = "The password should be at least 6 characters")
    @NotNull(message = "Please enter your password")
    private String password;

    /**
     * Empty constructor that instanciate a new customer and a new address.
     */
    public CustomerController() {
        customer = new Customer();
        address = new Address();
    }

    /**
     * Get the customer
     *
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the address
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the user
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Get the login
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Edit info of a specific customer
     *
     * @return to the page that shows info of the customer
     */
    public String editInfo() {
        Customer c = customerBean.find(customer.getCustomerID());
        c.setUser(userBean.find(facesContext.getExternalContext().getRemoteUser()));
        c.setFirstName(customer.getFirstName());
        c.setLastName(customer.getLastName());
        c.setEmail(customer.getEmail());
        c.setPhoneNo(customer.getPhoneNo());
        c.getAddress().setStreet(address.getStreet());
        c.getAddress().setCity(address.getCity());
        c.getAddress().setCountry(address.getCountry());
        c.getAddress().setZipCode(address.getZipCode());
        c.getAddress().setState(address.getState());
        customerBean.update(c);
        return "infosCust.xhtml";
    }

    /**
     * Display every ordersID from a customer
     *
     * @param cust the customer
     * @return the list of ordersID
     */
    public String displayOrdersID(Customer cust) {
        List<String> ordersList = new ArrayList<>();
        for (Orders o : cust.getOrders()) {
            ordersList.add("" + o.getOrderID());
        }
        return String.join(",", ordersList);
    }

    /**
     * Display every employeeID from a customer
     *
     * @param cust the customer
     * @return the list of employeeId
     */
    public String displayEmployeeID(Customer cust) {
        List<String> employeeList = new ArrayList<>();
        for (Employee e : cust.getEmployees()) {
            employeeList.add("" + e.getEmployeeID());
        }
        return String.join(",", employeeList);
    }

    /**
     * Display the list of orders from a customer
     *
     * @param cust the customer
     * @return the list of orders
     */
    public List<Orders> displayOrders(Customer cust) {
        List<Orders> orders = cust.getOrders();
        return orders;
    }

    /**
     * Create a new user, a new address, and a new customer, send an email to
     * the new customer
     *
     * @return the page to say that the customer is registered
     */
    public String register() {

        boolean usernameExisted = false;
        List<Customer> cList = customerBean.findAll();
        for (Customer c : cList) {
            String custLogin = c.getUser().getLogin();
            if (custLogin.equals(login)) {
                usernameExisted = true;
            }
        }

        if (!usernameExisted) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.addGroup(groupBean.findByName("customers"));
            Random r = new Random();
            customer.setCustomerID("A" + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9) + r.nextInt(9));
            customer.setUser(user);
            customer.setAddress(address);
            Employee e = employeeBean.findByLogin("rsavoure");
            customer.addEmployee(e);
            userBean.create(user);
            customerBean.create(customer);
            String body = "We are happy to count you in our privileged customers.\nThank you for that !\n We look forward to seeing you soon on our website ! ";
            emailBean.sendEmail(customer.getEmail(), "Welcome " + customer.getFirstName() + " " + customer.getLastName(), "Dear " + customer.getFirstName() + ",\n\n" + body);

            employeeBean.update(e);
            customer = new Customer();

            return "registered.xhtml";
        } else {
            message = "Username already existed";
            return "register.xhtml";
        }

    }

}
