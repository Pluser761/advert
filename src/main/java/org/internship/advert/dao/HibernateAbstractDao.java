package org.internship.advert.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

public abstract class HibernateAbstractDao<T, PK extends Serializable> implements IGenericDao<T, PK> {
    private final Class<T> type;
    private final SessionFactory sessionFactory;

    protected HibernateAbstractDao(Class<T> type, SessionFactory sessionFactory) {
        this.type = type;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<T> listAll() {
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.type);
        Root<T> root = criteria.from(this.type);
        criteria.select(root);
        Query<T> query = getCurrentSession().createQuery(criteria);
        return query.getResultList();
    }

    public T getById(PK id) {
        return this.getCurrentSession().load(type, id);
    }

    @Transactional
    public T add(T entity) {
        Session session = this.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Transactional
    public T update(T entity) {
        Session session = this.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return entity;
    }

    @Transactional
    public void delete(PK id) {
        Session session = this.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
