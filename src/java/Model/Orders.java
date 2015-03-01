/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manon
 */
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findByOrderID", query = "SELECT o FROM Orders o WHERE o.orderID = :orderID"),
    @NamedQuery(name = "Orders.findBydateCreated", query = "SELECT o FROM Orders o WHERE o.dateCreated = :dateCreated"),
    @NamedQuery(name = "Orders.findBydateShipped", query = "SELECT o FROM Orders o WHERE o.dateShipped = :dateShipped"),
    @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
    @NamedQuery(name = "Orders.findByPrice", query = "SELECT o FROM Orders o WHERE o.price = :price")})

@Entity
@Table(name = "orders")
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID", unique = true)
    private int orderID;

    @OneToMany(mappedBy = "orders")
    List<OrderLine> orderLines = new ArrayList<OrderLine>();

    @Column(name = "dateCreated")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "dateShipped")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateShipped;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private float price;

    /**
     * Empty constructor for Orders.
     *
     */
    public Orders() {

    }

    /**
     * Full constructor for Orders
     *
     * @param dateCreated date of creation of order
     * @param dateShipped date of shipping of order
     * @param status status of order
     * @param price price of order
     */
    public Orders(Date dateCreated, Date dateShipped, String status, float price) {
        this.dateCreated = dateCreated;
        this.dateShipped = dateShipped;
        this.status = status;
        this.price = price;
    }

    /**
     * Associate an orderline and an order
     *
     * @param ol orderLine to associate
     */
    public void addOrderLine(OrderLine ol) {
        if (!this.orderLines.contains(ol)) {
            this.orderLines.add(ol);
        }
        if (ol.getOrders() != this) {
            ol.setOrders(this);
        }
    }

    /**
     * Get the value of orderID
     *
     * @return the value of orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Set the value of orderID
     *
     * @param orderID new value of orderID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Get the value of dateCreated
     *
     * @return the value of dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the value of dateCreated
     *
     * @param dateCreated new value of dateCreated
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * Get the value of dateShipped
     *
     * @return the value of dateShipped
     */
    public Date getDateShipped() {
        return dateShipped;
    }

    /**
     * Set the value of dateShipped
     *
     * @param dateShipped new value of dateShipped
     */
    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Get the list of orderLines
     *
     * @return the list of orderLines
     */
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * Set the list of orderLines
     *
     * @param orderLines new list of orderLines
     */
    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Order");
        sb.append("{Order ID='").append(orderID).append('\'');
        sb.append(", date created='").append(dateCreated).append('\'');
        sb.append(", date shipped='").append(dateShipped).append('\'');
        sb.append(", order status='").append(status).append('\'');
        sb.append(", order price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
