/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Manon
 * @param <T> entity class
 */
public abstract class AbstractBean<T> {

    @PersistenceContext(unitName = "Mchancer_FP_PU")
    private EntityManager em;

    private final Class<T> entityClass;

    /**
     * Set the entity class
     *
     * @param entityClass the entity class
     */
    protected AbstractBean(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Get the entity manager
     *
     * @return entity manager
     */
    protected EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * Create an entity
     *
     * @param entity the entity
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     * Find an object
     *
     * @param id id of object
     * @return the object
     */
    public T find(Object id) {
        return em.find(entityClass, id);
    }

    /**
     * Find all objects from an entity
     *
     * @return the list of objects
     */
    public abstract List<T> findAll();

    /**
     * Update an entity
     *
     * @param entity the entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * Delete an entity
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        em.remove(entity);
    }

}
