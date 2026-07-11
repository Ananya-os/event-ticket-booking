package com.ananya.event_ticket_booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ananya.event_ticket_booking.entity.User;
import com.ananya.event_ticket_booking.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}