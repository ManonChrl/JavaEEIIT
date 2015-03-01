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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manon
 */
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductID", query = "SELECT p FROM Product p WHERE p.productID = :productID"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description LIKE :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findBySize", query = "SELECT p FROM Product p WHERE p.sizeProduct = :sizeProduct"),
    @NamedQuery(name = "Product.findByColor", query = "SELECT p FROM Product p WHERE p.color = :color"),
    @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE p.brand = :brand"),
    @NamedQuery(name = "Product.findByTypeAndGender", query = "SELECT p FROM Product p WHERE p.type = :type AND p.gender=:gender"),
    @NamedQuery(name = "Product.findByType", query = "SELECT p FROM Product p WHERE p.type = :type"),
    @NamedQuery(name = "Product.findByGender", query = "SELECT p FROM Product p WHERE p.gender = :gender")})

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID", unique = true)
    private int productID;

    @NotNull(message = "Description cannot be null")
    @Size(max = 2000, message = "Description cannot be more than 2000 characters")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price cannot be under 1")
    @Column(name = "price")
    private float price;

    @NotNull(message = "Size could not be null")
    @Size(max = 4, message = "Size must be less than five characters")
    @Column(name = "sizeProduct")
    private String sizeProduct;

    @Column(name = "color")
    private String color;

    @Column(name = "brand")
    private String brand;

    @NotNull(message = "Type cannot be null")
    @Column(name = "type")
    private String type;

    @NotNull(message = "Gender cannot be null")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "Quantity cannot be null")
    @Column(name = "quantity")
    private int quantity;

    @ManyToMany
    private List<OrderLine> orderLines = new ArrayList<OrderLine>();

    /**
     * Empty constructor for Product.
     *
     */
    public Product() {
    }

    /**
     * Full constructor for Product
     *
     * @param description description of product
     * @param price price of product
     * @param sizeProduct size of product
     * @param color color of product
     * @param brand brand of product
     * @param type type of product
     * @param gender gender of product 'user'
     * @param quantity quantity of product available
     */
    public Product(String description, float price, String sizeProduct, String color, String brand, String type, String gender, int quantity) {
        this.description = description;
        this.price = price;
        this.sizeProduct = sizeProduct;
        this.color = color;
        this.brand = brand;
        this.type = type;
        this.gender = gender;
        this.quantity = quantity;
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
     * Get the value of productID
     *
     * @return the value of productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Set the value of productID
     *
     * @param productID new value of productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
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
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of sizeProduct
     *
     * @return the value of sizeProduct
     */
    public String getSizeProduct() {
        return sizeProduct;
    }

    /**
     * Set the value of sizeProduct
     *
     * @param sizeProduct new value of sizeProduct
     */
    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    /**
     * Get the value of color
     *
     * @return the value of color
     */
    public String getColor() {
        return color;
    }

    /**
     * Set the value of color
     *
     * @param color new value of color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get the value of brand
     *
     * @return the value of brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set the value of brand
     *
     * @param brand new value of brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the value of gender
     *
     * @return the value of gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the value of gender
     *
     * @param gender new value of gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the list of orderlines
     *
     * @return the list of orderlines
     */
    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * Set the value of orderlines
     *
     * @param orderLines new value of orderLines
     */
    public void setOrderLine(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Product");
        sb.append("{product ID='").append(productID).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price='").append(price).append('\'');
        sb.append(", size='").append(sizeProduct).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
