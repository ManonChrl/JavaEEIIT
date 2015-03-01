/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import Model.Product;
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
public class ProductBean extends AbstractBean<Product> {

    /**
     * Empty Constructor.
     */
    public ProductBean() {
        super(Product.class);
    }

    /**
     * Find all products
     *
     * @return a list of products
     */
    @Override
    public List<Product> findAll() {
        return getEntityManager().createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    /**
     * Find a product with its product ID
     *
     * @param productID product ID
     * @return a product
     */
    public Product findByProductID(int productID) {
        TypedQuery<Product> query = getEntityManager().createNamedQuery("Product.findByProductID", Product.class);
        query.setParameter("productID", productID);
        return query.getSingleResult();
    }

    /**
     * Find products with a specific type and gender
     *
     * @param type type of the product
     * @param gender gender of the product
     * @return a list of product
     */
    public List<Product> findByTypeAndGender(String type, String gender) {
        return getEntityManager().createNamedQuery("Product.findByTypeAndGender", Product.class).setParameter("type", type).setParameter("gender", gender).getResultList();
    }

    /**
     * Find products with a specific gender
     *
     * @param gender gender of the product
     * @return a list of product
     */
    public List<Product> findByGender(String gender) {
        return getEntityManager().createNamedQuery("Product.findByGender", Product.class).setParameter("gender", gender).getResultList();
    }

    /**
     * Find products with a specific type
     *
     * @param type type of the product
     * @return a list of product
     */
    public List<Product> findByType(String type) {
        return getEntityManager().createNamedQuery("Product.findByType", Product.class).setParameter("type", type).getResultList();
    }

    /**
     * Find products with a specific brand
     *
     * @param brand brand of the product
     * @return a list of product
     */
    public List<Product> findByBrand(String brand) {
        return getEntityManager().createNamedQuery("Product.findByBrand", Product.class).setParameter("brand", brand).getResultList();
    }

    /**
     * Find products with a specific color
     *
     * @param color color of the product
     * @return a list of product
     */
    public List<Product> findByColor(String color) {
        return getEntityManager().createNamedQuery("Product.findByColor", Product.class).setParameter("color", color).getResultList();
    }

    /**
     * Find products with a specific description
     *
     * @param description description of the product
     * @return a list of product
     */
    public List<Product> findByDescription(String description) {
        return getEntityManager().createNamedQuery("Product.findByDescription", Product.class).setParameter("description", "%" + description + "%").getResultList();
    }

    @Override
    public void delete(Product unmanagedP) {
        Product managedP = getEntityManager().find(Product.class, unmanagedP.getProductID());

        managedP.setBrand(unmanagedP.getBrand());
        managedP.setColor(unmanagedP.getColor());
        managedP.setDescription(unmanagedP.getDescription());
        managedP.setGender(unmanagedP.getGender());
        managedP.setPrice(unmanagedP.getPrice());
        managedP.setSizeProduct(unmanagedP.getSizeProduct());
        managedP.setType(unmanagedP.getType());

        getEntityManager().remove(managedP);
    }

}
