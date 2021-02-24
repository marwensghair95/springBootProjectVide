package com.fivepoints.spring.services;

import com.fivepoints.spring.entities.User;
import com.fivepoints.spring.exceptions.ResourceNotFoundException;
import com.fivepoints.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveNewUser(User user)
    {
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    public User findUserByID(long id)
    {
        Optional<User> userData = this.userRepository.findById(id);
        // Return statement if user exist or throw exception
        return userData.orElseThrow(() -> new ResourceNotFoundException("User not found"));

    }

    public String updateUserByID(long id, User user)
    {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            User existingUser = userData.orElse(null);
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            // save existingUser in the database
            this.userRepository.save(existingUser);
            // return statement
            return "User updated successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public String deleteUserById(long id)
    {
        Optional<User> userData = this.userRepository.findById(id);
        if (userData.isPresent()) {
            this.userRepository.deleteById(id);
            return "User deleted successfully!";
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findByEmail(email);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

}
