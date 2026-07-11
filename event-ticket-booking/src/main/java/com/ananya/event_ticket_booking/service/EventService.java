package com.ananya.event_ticket_booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ananya.event_ticket_booking.entity.Event;
import com.ananya.event_ticket_booking.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}