package com.ds.ise.data.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class CommonDAO<T> implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public CommonDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    protected void delete(Object id, Class<T> clazz) {
        T entityToRemove = entityManager.getReference(clazz, id);
        entityManager.remove(entityToRemove);
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public T find(int entityID) {
        return entityManager.find(entityClass, entityID);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findAll() {
        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        try {
            Query query = entityManager.createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (T) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error while running query: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> findFilteredResults(String filterBy, Object compareWith) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root e = criteriaQuery.from(entityClass);
        Expression<Boolean> whereClause = criteriaBuilder.in(e.get(filterBy)).value(compareWith);
        criteriaQuery.select(e).where(whereClause);
        Query query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public long getSumOfColumn(String column) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root e = criteriaQuery.from(entityClass);
        criteriaQuery.select(criteriaBuilder.sum(e.get(column)));
        Query query = entityManager.createQuery(criteriaQuery);

        return (Long) query.getSingleResult();
    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
}