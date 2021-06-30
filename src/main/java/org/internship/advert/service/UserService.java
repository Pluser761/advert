package org.internship.advert.service;

import org.internship.advert.model.User;
import org.internship.advert.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@ComponentScan("org.internship.advert.repo")
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User updateUser) {
        return this.userRepository.findById(updateUser.getId())
                .map(user -> {
                    user.setEmail(updateUser.getEmail());
                    user.setFirstName(updateUser.getFirstName());
                    user.setLastName(updateUser.getLastName());
                    user.setLogin(updateUser.getLogin());
                    return this.userRepository.save(user);
                });
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.getById(id);
    }
}
