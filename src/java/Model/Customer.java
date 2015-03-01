/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manon
 */
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByLogin", query = "SELECT c FROM Customer c WHERE c.user.login = :login"),
    @NamedQuery(name = "Customer.findByFirstName", query = "SELECT c FROM Customer c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Customer.findByLastName", query = "SELECT c FROM Customer c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByPhoneNo", query = "SELECT c FROM Customer c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "Customer.findByCustomerID", query = "SELECT c FROM Customer c WHERE c.customerID = :customerID")})

@Entity
@Table(name = "customer")
public class Customer extends Users implements Serializable {

    @Id
    @Column(name = "customerID", unique = true)
    private String customerID;

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "login")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    private List<Orders> orders = new ArrayList<Orders>();

    @ManyToMany(mappedBy = "customers")
    private List<Employee> employees = new ArrayList<Employee>();

    /**
     * Empty constructor for Customer.
     *
     */
    public Customer() {

    }

    /**
     * Full constructor for Customer
     *
     * @param customerID customer ID
     * @param firstName customer first name
     * @param lastName customer last name
     * @param email customer email
     * @param phoneNo customer phone number
     */
    public Customer(String customerID, String firstName, String lastName, String email, String phoneNo) {
        super(firstName, lastName, email, phoneNo);
        this.customerID = customerID;
    }

    /**
     * Associate an order and a customer
     *
     * @param o order to add
     */
    public void addOrder(Orders o) {
        if (!this.orders.contains(o)) {
            this.orders.add(o);
        }
    }

    /**
     * Associate an employee and a customer
     *
     * @param e employee to add
     */
    public void addEmployee(Employee e) {
        if (!this.employees.contains(e)) {
            this.employees.add(e);
        }
        if (!e.getCustomers().contains(this)) {
            e.getCustomers().add(this);
        }
    }

    /**
     * Get the value of customerID
     *
     * @return the value of customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Set the value of customerID
     *
     * @param customerID new value of customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get the list of orders
     *
     * @return the list of orders
     */
    public List<Orders> getOrders() {
        return orders;
    }

    /**
     * Get the list of orders in cart
     *
     * @return the list of orders
     */
    public Orders getOrderInCart() {
        int orderExist = 0;
        Orders o1 = null;
        for (Orders o : this.orders) {
            if (o.getStatus() == "In cart") {
                orderExist = 1;
                o1 = o;
            }
        }
        return o1;
    }

    /**
     * Set the list of orders
     *
     * @param orders new list of orders
     */
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    /**
     * Get the list of employees
     *
     * @return the list of employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Set the list of employees
     *
     * @param employees new list of employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Customer");
        sb.append("{Customer ID='").append(customerID).append('\'');
        sb.append(", first name='").append(this.getFirstName()).append('\'');
        sb.append(", last name='").append(this.getLastName()).append('\'');
        sb.append(", email='").append(this.getEmail()).append('\'');
        sb.append(", phone number='").append(this.getPhoneNo()).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
