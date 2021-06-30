package org.internship.advert.rest;

import org.internship.advert.model.User;
import org.internship.advert.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final IUserService userService;

    @Autowired
    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> listUsers() {
        return new ResponseEntity<>(this.userService.listUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        User user = null;
        try {
            user = this.userService.getUserById(id);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            user = this.userService.addUser(user);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.userService.addUser(user), HttpStatus.OK);
    }
}
