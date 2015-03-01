/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import EJB.OrdersBean;
import Model.OrderLine;
import Model.Orders;
import java.util.ArrayList;
import java.util.List;
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
public class OrdersController extends AbstractController {

    @EJB
    private OrdersBean ordersBean;

    private Orders order;

    /**
     * Empty Constructor that instanciate an order.
     */
    public OrdersController() {
        order = new Orders();
    }

    /**
     * Get the order
     *
     * @return the order
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * Set the order
     *
     * @param order new value of order
     */
    public void setOrder(Orders order) {
        this.order = order;
    }

    /**
     * Display every orderlinesID from an order
     *
     * @param order the order
     * @return the list of orderlinesID
     */
    public String displayOrdersLinesID(Orders order) {
        List<String> orderLineList = new ArrayList<>();
        for (OrderLine ol : order.getOrderLines()) {
            orderLineList.add("" + ol.getOrderLineID());
        }
        return String.join(",", orderLineList);
    }

    /**
     * Edit an order.
     *
     * @return the page that shows the list of customer
     */
    public String editOrder() {
        Orders o = ordersBean.findByOrderID(order.getOrderID());
        o.setStatus(order.getStatus());
        ordersBean.update(o);
        order = new Orders();
        return "empCust.xhtml";
    }

    /**
     * Order the order that is in a cart
     *
     * @return the list of orders from the customer
     */
    public String orderCartToOrder() {
        int orderID = Integer.parseInt(getPassedParameter());
        order = ordersBean.find(orderID);
        order.setStatus("Preparation");
        ordersBean.update(order);
        order = new Orders();
        return "ordersCust.xhtml";
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name orderID
     */
    public String getPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("orderID");
    }

    /**
     * Watch the passed parameter
     *
     * @return the passed parameter with name orderlineID
     */
    public String getOLPassedParameter() {

        return (String) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("orderlineID");
    }

}
