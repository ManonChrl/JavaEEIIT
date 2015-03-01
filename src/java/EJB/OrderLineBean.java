/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.OrderLine;
import Model.Product;
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
public class OrderLineBean extends AbstractBean<OrderLine> {

    /**
     * Empty Constructor.
     */
    public OrderLineBean() {
        super(OrderLine.class);
    }

    /**
     * Find all orderlines
     *
     * @return a list of orderlines
     */
    @Override
    public List<OrderLine> findAll() {
        return getEntityManager().createNamedQuery("OrderLine.findAll", OrderLine.class).getResultList();
    }

    /**
     * Create a new orderLine
     *
     * @param p product
     * @param quantity quantity of product
     */
    public void createNewOrderline(Product p, int quantity) {
        OrderLine ol = new OrderLine(quantity);
        ol.addProduct(p);
    }

    /**
     * Find an orderline with his id
     *
     * @param orderlineID id of orderline
     * @return an orderline
     */
    public OrderLine findByOrderlineID(int orderlineID) {
        TypedQuery<OrderLine> query = getEntityManager().createNamedQuery("OrderLine.findByOrderlineID", OrderLine.class);
        query.setParameter("orderlineID", orderlineID);
        return query.getSingleResult();
    }

    @Override
    public void delete(OrderLine unmanagedOL) {

        OrderLine managedOL = getEntityManager().find(OrderLine.class, unmanagedOL.getOrderLineID());
        Orders unmanagedO = unmanagedOL.getOrders();
        Orders managedO = getEntityManager().find(Orders.class, unmanagedOL.getOrders().getOrderID());

        managedO.setDateCreated(unmanagedO.getDateCreated());
        managedO.setDateShipped(unmanagedO.getDateShipped());
        managedO.setPrice(unmanagedO.getPrice());
        managedO.setStatus(unmanagedO.getStatus());

        getEntityManager().merge(managedO);
        managedOL.setQuantity(unmanagedOL.getQuantity());
        getEntityManager().remove(managedOL);
        managedO.getOrderLines().remove(managedOL);

    }
}
