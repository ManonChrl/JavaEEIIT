/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import EJB.security.GroupBean;
import EJB.security.UserBean;
import Model.Address;
import Model.Customer;
import Model.Employee;
import Model.OrderLine;
import Model.Orders;
import Model.Product;
import Model.security.Group;
import Model.security.User;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Manon
 */
@Singleton
@Startup
public class StartupBean {

    @EJB
    private UserBean userBean;
    @EJB
    private GroupBean groupBean;
    @EJB
    private ProductBean productBean;
    @EJB
    private OrdersBean ordersBean;
    @EJB
    private OrderLineBean orderLineBean;
    @EJB
    private EmployeeBean employeeBean;
    @EJB
    private CustomerBean customerBean;

    /**
     * Empty Constructor.
     */
    public StartupBean() {
    }

    @PostConstruct
    private void populateDatabase() {

        //Groups
        Group customersGroup = new Group("customers", "Group of all customers");
        Group employeesGroup = new Group("employees", "Group of all employees");

        //Users
        User u1 = new User("mchancer", "mchancer");
        User u2 = new User("mhuger", "mhuger");
        User u3 = new User("pweider", "pweider");
        User u4 = new User("rsavoure", "rsavoure");
        User u5 = new User("jleroi", "jleroi");
        u1.addGroup(customersGroup);
        u2.addGroup(customersGroup);
        u3.addGroup(customersGroup);
        u4.addGroup(employeesGroup);
        u5.addGroup(employeesGroup);

        //Customer
        Customer c1 = new Customer("A20330164", "Manon", "Chancereul", "chancereul.manon@gmail.com", "8723011934");
        Customer c2 = new Customer("A33333333", "Matthieu", "Huger", "matthieu.huger@gmail.com", "8721231234");
        Customer c3 = new Customer("A20330160", "Pierre", "Weider", "weider.pierre@gmail.com", "8728013934");
        c1.setUser(u1);
        c2.setUser(u2);
        c3.setUser(u3);

        //Employee
        Employee e1 = new Employee("E1", "Romain", "Savoure", "savoure.romain@gmail.com", "8723387934");
        Employee e2 = new Employee("E2", "Justine", "Leroi", "leroi.justine@gmail.com", "8723641934");
        c1.addEmployee(e2);
        c2.addEmployee(e1);
        c2.addEmployee(e2);
        c3.addEmployee(e1);
        e1.setUser(u4);
        e2.setUser(u5);

        //Address
        Address a1 = new Address("3521 N Reta Ave", "Chicago", "IL", "60657", "USA");
        Address a2 = new Address("755 W Buena Ave", "Chicago", "IL", "60614", "USA");
        c1.setAddress(a1);
        c2.setAddress(a1);
        c3.setAddress(a2);

        //Orders
        Date dateCreated = new GregorianCalendar(2014, 9, 1).getTime();
        Date dateShipped = new GregorianCalendar(2014, 9, 10).getTime();
        Orders o1 = new Orders(dateCreated, dateShipped, "Delivered", 311.14F);
        dateCreated = new GregorianCalendar(2014, 9, 11).getTime();
        dateShipped = new GregorianCalendar(2014, 9, 21).getTime();
        Orders o2 = new Orders(dateCreated, dateShipped, "In preparation", 179.37F);
        Orders o3 = new Orders(dateCreated, dateShipped, "Shipped", 227.37F);
        c1.addOrder(o1);
        c1.addOrder(o3);
        c2.addOrder(o2);

        //OrderLine
        OrderLine ol1 = new OrderLine(4);
        OrderLine ol2 = new OrderLine(2);
        OrderLine ol3 = new OrderLine(3);
        OrderLine ol4 = new OrderLine(3);
        o1.addOrderLine(ol1);
        o1.addOrderLine(ol2);
        o2.addOrderLine(ol3);
        o3.addOrderLine(ol4);

        //Product
        Product p1 = new Product("nice hoodie", 39.80F, "S", "black", "Hollister", "Top", "Women", 4);
        Product p2 = new Product("nice sweater", 19.99F, "M", "pink", "Abercrombie", "Top", "Men", 3);
        Product p3 = new Product("nice jean", 35.99F, "L", "blue", "Urban Outfitters", "Bottom", "Boys", 4);
        Product p4 = new Product("pretty dress", 33.49F, "M", "white", "American Eagle", "Dress", "Women", 4);
        Product p5 = new Product("beautiful pants", 43.49F, "XS", "black", "American Eagle", "Bottom", "Women", 4);
        Product p6 = new Product("jacket", 123.49F, "S", "khaki", "Hollister", "Outerwear", "Women", 4);
        Product p7 = new Product("military jean", 38.49F, "M", "khaki", "American Eagle", "Bottom", "Men", 4);
        Product p8 = new Product("pretty parka", 28.99F, "XL", "white", "Abercrombie", "Outerwear", "Men", 4);
        Product p9 = new Product("pretty tee shirt", 25.49F, "M", "green", "Aeropostale", "Top", "Boys", 4);
        Product p10 = new Product("parka", 19.49F, "S", "black", "Aeropostale", "Outerwear", "Boys", 4);
        Product p11 = new Product("pretty parka", 28.99F, "XL", "white", "Abercrombie", "Dress", "Girls", 4);
        Product p12 = new Product("tee shirt", 29.49F, "M", "purple", "Zara", "Top", "Girls", 4);
        Product p13 = new Product("trench coat", 19.49F, "S", "blue", "H&M", "Outerwear", "Girls", 4);
        Product p14 = new Product("jean", 19.49F, "S", "pink", "pink", "Bottom", "Girls", 4);
        Product p15 = new Product("boots", 119.49F, "8", "black", "Aldo", "Boots", "Shoes", 4);
        Product p16 = new Product("flip flops", 59.49F, "9", "navy", "Hollister", "FlipFlops", "Shoes", 4);
        Product p17 = new Product("sneakers", 69.99F, "6", "green", "Zara", "Sneakers", "Shoes", 4);
        Product p18 = new Product("beautiful hat", 19.49F, "U", "purple", "Forever 21", "Hats", "Accessories", 4);
        Product p19 = new Product("classic belt", 15.99F, "8", "black", "Banana Republic", "Belts", "Accessories", 4);
        Product p20 = new Product("original bow tie", 9.49F, "U", "navy", "Gap", "BowTies", "Accessories", 4);
        Product p21 = new Product("rainbow umbrella", 6.99F, "U", "rainbow", "Old Navy", "Umbrellas", "Accessories", 4);

        ol1.addProduct(p1);
        ol1.addProduct(p2);
        ol2.addProduct(p3);
        ol3.addProduct(p2);
        ol3.addProduct(p1);
        ol4.addProduct(p1);
        ol4.addProduct(p3);

        groupBean.create(customersGroup);
        groupBean.create(employeesGroup);
        userBean.create(u1);
        userBean.create(u2);
        userBean.create(u3);
        userBean.create(u4);
        userBean.create(u5);
        customerBean.create(c1);
        customerBean.create(c2);
        customerBean.create(c3);
        employeeBean.create(e1);
        employeeBean.create(e2);
        ordersBean.create(o1);
        ordersBean.create(o2);
        ordersBean.create(o3);
        orderLineBean.create(ol1);
        orderLineBean.create(ol2);
        orderLineBean.create(ol3);
        orderLineBean.create(ol4);
        productBean.create(p1);
        productBean.create(p2);
        productBean.create(p3);
        productBean.create(p4);
        productBean.create(p5);
        productBean.create(p6);
        productBean.create(p7);
        productBean.create(p8);
        productBean.create(p9);
        productBean.create(p10);
        productBean.create(p11);
        productBean.create(p12);
        productBean.create(p13);
        productBean.create(p14);
        productBean.create(p15);
        productBean.create(p16);
        productBean.create(p17);
        productBean.create(p18);
        productBean.create(p19);
        productBean.create(p20);
        productBean.create(p21);

    }

}
