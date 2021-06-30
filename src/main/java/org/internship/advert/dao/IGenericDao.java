package org.internship.advert.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {

    List<T> listAll();

    T getById(PK id);

    T add(T entity);

    T update(T entity);

    void delete(PK id);
}
