package com.ananya.event_ticket_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String venue;

    private LocalDateTime dateTime;

    private Integer totalSeats;

    private Integer availableSeats;

    @OneToMany(mappedBy = "event")
    private List<Booking> bookings;
}