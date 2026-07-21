package com.ananya.event_ticket_booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.event_ticket_booking.dto.EventRequest;
import com.ananya.event_ticket_booking.entity.Event;
import com.ananya.event_ticket_booking.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event saveEvent(@Valid @RequestBody EventRequest request) {
        return eventService.saveEvent(request);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}