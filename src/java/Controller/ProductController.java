/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.CustomerBean;
import EJB.OrderLineBean;
import EJB.OrdersBean;
import EJB.ProductBean;
import Model.Customer;
import Model.OrderLine;
import Model.Orders;
import Model.Product;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Manon
 */
@Named
@RequestScoped
public class ProductController extends AbstractController {

    @EJB
    private OrdersBean ordersBean;
    @EJB
    private CustomerBean customerBean;
    @EJB
    private OrderLineBean orderLineBean;
    @EJB
    private ProductBean productBean;

    private Product product;
    private OrderLine orderline;
    private Customer customer;
    private String search;
    private String message;

    /**
     * Empty constructor that instanciate a new product and a new orderline.
     */
    public ProductController() {
        product = new Product();
        orderline = new OrderLine();
    }

    /**
     * Add a product
     *
     * @return to the page that shows products
     */
    public String addProduct() {
        productBean.create(product);
        product = new Product();
        return "products.xhtml";
    }

    /**
     * Edit a product
     *
     * @return to the page that shows products
     */
    public String editProduct() {
        productBean.update(product);
        product = new Product();
        return "products.xhtml";
    }

    /**
     * Delete a product
     *
     * @return to the page that shows products
     */
    public String deleteProduct() {
        Product p = productBean.findByProductID(product.getProductID());
        productBean.delete(p);
        product = new Product();
        return "products.xhtml";
    }

    /**
     * Add a product to the order in the cart
     *
     * @return to the page that shows what is inside the cart
     */
    public String addProductToCart() {
        int productID = product.getProductID();
        int quantity = orderline.getQuantity();
        setProduct(productBean.findByProductID(productID));
        if (orderline.getQuantity() <= product.getQuantity()) {
            Date dateCreated = new Date();
            Date dateShipped = new Date(dateCreated.getTime() + 432000000);
            float price = product.getPrice() * quantity;
            Customer c1 = customerBean.findByLogin(facesContext.getExternalContext().getRemoteUser());
            Orders o1;
            if (c1.getOrderInCart() != null) {
                o1 = c1.getOrderInCart();
                o1.setPrice(price + o1.getPrice());
            } else {
                o1 = new Orders(dateCreated, dateShipped, "In cart", price);
                ordersBean.create(o1);
                c1.addOrder(o1);
            }

            OrderLine ol1 = new OrderLine(quantity);
            orderLineBean.create(ol1);

            o1.addOrderLine(ol1);
            ol1.addProduct(product);

            customerBean.update(c1);
            ordersBean.update(o1);
            orderLineBean.update(ol1);

            product.setQuantity(product.getQuantity() - quantity);
            productBean.update(product);

            return "cart.xhtml";
        } else {
            message = "Quantity superior than quantity max allowed !!!";
            return "index.xhtml";
        }
    }

    /**
     * Delete an orderline
     *
     * @return to the page that shows what is inside the cart
     */
    public String deleteOrderline() {
        OrderLine ol = orderLineBean.findByOrderlineID(orderline.getOrderLineID());
        float price = 0;
        for (Product p : ol.getProducts()) {
            price += ol.getQuantity() * p.getPrice();
            p.setQuantity(p.getQuantity() + ol.getQuantity());
            productBean.update(p);
        }

        orderLineBean.delete(ol);
        Customer c = customerBean.findByLogin(facesContext.getExternalContext().getRemoteUser());

        for (Orders o : c.getOrders()) {
            if (o.getOrderLines().isEmpty()) {
                for (OrderLine oll : o.getOrderLines()) {
                    System.out.println(oll.toString());
                    orderLineBean.delete(oll);
                }
                c = ordersBean.delete(o, c);
            } else if (o.getStatus().equals("In cart")) {
                o.setPrice(o.getPrice() - price);
            }
        }
        customerBean.update(c);

        return "cart.xhtml";
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name productID
     */
    public String getPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("productID");
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name type
     */
    public String getTypePassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("type");
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name gender
     */
    public String getGenderPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("gender");
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name all
     */
    public String getAllPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("all");
    }

    /**
     * Go to the page that shows article searched
     *
     * @return the page that shows article searched
     */
    public String search() {
        return "search.xhtml";
    }

    /**
     * Get the orderline
     *
     * @return the orderline
     */
    public OrderLine getOrderline() {
        return orderline;
    }

    /**
     * Set the orderline
     *
     * @param orderline new value of orderline
     */
    public void setOrderline(OrderLine orderline) {
        this.orderline = orderline;
    }

    /**
     * Get the search string
     *
     * @return the search string
     */
    public String getSearch() {
        return search;
    }

    /**
     * Set the search string
     *
     * @param search new value of search string
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * Get the product
     *
     * @return the ptoduct
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the product
     *
     * @param product new value of product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Get the message
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
