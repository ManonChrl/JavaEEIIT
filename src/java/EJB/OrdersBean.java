/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Customer;
import Model.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Manon
 */
@Named
@Stateless
public class OrdersBean extends AbstractBean<Orders> {

    /**
     * Empty Constructor.
     */
    public OrdersBean() {
        super(Orders.class);
    }

    /**
     * Find all orders
     *
     * @return a list of orders
     */
    @Override
    public List<Orders> findAll() {
        return getEntityManager().createNamedQuery("Orders.findAll", Orders.class).getResultList();
    }

    /**
     * Find an order with his id
     *
     * @param orderID order ID
     * @return an order
     */
    public Orders findByOrderID(int orderID) {
        TypedQuery<Orders> query = getEntityManager().createNamedQuery("Orders.findByOrderID", Orders.class);
        query.setParameter("orderID", orderID);
        return query.getSingleResult();
    }

    /**
     * Find an order with his id
     *
     * @param status status of the order
     * @return an order
     */
    public Orders findByStatus(String status) {
        TypedQuery<Orders> query = getEntityManager().createNamedQuery("Orders.findByStatus", Orders.class);
        query.setParameter("status", status);
        return query.getSingleResult();
    }

    /**
     * Delete an order from a customer
     *
     * @param unmanagedO the order to delete
     * @param unmanagedC the customer from who the order has to be deleted
     * @return the updated customer
     */
    public Customer delete(Orders unmanagedO, Customer unmanagedC) {

        Orders managedO = getEntityManager().find(Orders.class, unmanagedO.getOrderID());
        Customer managedC = getEntityManager().find(Customer.class, unmanagedC.getCustomerID());

        managedO.setDateCreated(unmanagedO.getDateCreated());
        managedO.setDateShipped(unmanagedO.getDateShipped());
        managedO.setPrice(unmanagedO.getPrice());
        managedO.setStatus(unmanagedO.getStatus());

        managedC.setAddress(unmanagedC.getAddress());
        managedC.setEmail(unmanagedC.getEmail());
        managedC.setEmployees(unmanagedC.getEmployees());
        managedC.setFirstName(unmanagedC.getFirstName());
        managedC.setLastName(unmanagedC.getLastName());
        managedC.setPhoneNo(unmanagedC.getPhoneNo());
        managedC.setUser(unmanagedC.getUser());
        managedC.setOrders(unmanagedC.getOrders());

        getEntityManager().merge(managedC);
        managedC.getOrders().remove(managedO);
        getEntityManager().remove(managedO);

        return managedC;

    }
}
