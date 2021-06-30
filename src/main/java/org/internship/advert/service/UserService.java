package org.internship.advert.service;

import org.internship.advert.dao.IGenericDao;
import org.internship.advert.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService implements IUserService {

    private final IGenericDao<User, Long> userDAO;

    @Autowired
    public UserService(IGenericDao<User, Long> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return this.userDAO.listAll();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        return this.userDAO.add(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return this.userDAO.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        this.userDAO.delete(id);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return this.userDAO.getById(id);
    }
}
