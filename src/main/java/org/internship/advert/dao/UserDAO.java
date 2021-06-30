package org.internship.advert.dao;

import org.hibernate.SessionFactory;
import org.internship.advert.model.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO extends HibernateAbstractDao<User, Long> {

    public UserDAO(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }
}
