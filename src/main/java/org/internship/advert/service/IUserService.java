package org.internship.advert.service;

import org.internship.advert.model.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listUsers();
}
