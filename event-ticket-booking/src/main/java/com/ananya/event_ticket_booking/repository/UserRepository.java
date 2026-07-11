package com.ananya.event_ticket_booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ananya.event_ticket_booking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}