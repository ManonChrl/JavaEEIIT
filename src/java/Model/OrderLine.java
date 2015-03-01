/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Manon
 */
@NamedQueries({
    @NamedQuery(name = "OrderLine.findAll", query = "SELECT ol FROM OrderLine ol"),
    @NamedQuery(name = "OrderLine.findByOrderlineID", query = "SELECT ol FROM OrderLine ol WHERE ol.orderLineID = :orderlineID")})

@Entity
@Table(name = "orderLine")
public class OrderLine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderLineID", unique = true)
    private int orderLineID;

    @ManyToOne
    private Orders orders;

    @ManyToMany(mappedBy = "orderLines")
    private List<Product> products = new ArrayList<Product>();

    @Column(name = "quantity")
    private int quantity;

    /**
     * Empty constructor for OrderLine.
     *
     */
    public OrderLine() {
    }

    /**
     * Full constructor for OrderLine
     *
     * @param quantity quantity of products in an orderline
     */
    public OrderLine(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Associate product and orderLine
     *
     * @param p product to associate
     */
    public void addProduct(Product p) {
        if (!this.products.contains(p)) {
            this.products.add(p);
        }
        if (!p.getOrderLines().contains(this)) {
            p.getOrderLines().add(this);
        }
    }

    /**
     * Get the value of OrderLineID
     *
     * @return the value of OrderLineID
     */
    public int getOrderLineID() {
        return orderLineID;
    }

    /**
     * Set the value of orderLineID
     *
     * @param orderLineID new value of orderLineID
     */
    public void setOrderLineID(int orderLineID) {
        this.orderLineID = orderLineID;
    }

    /**
     * Get the value of orders
     *
     * @return the value of orders
     */
    public Orders getOrders() {
        return orders;
    }

    /**
     * Set the value of orders
     *
     * @param orders new value of orders
     */
    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the list of products
     *
     * @return the list of products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Set the list of products
     *
     * @param products new list of products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
