package com.ananya.event_ticket_booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ananya.event_ticket_booking.dto.EventRequest;
import com.ananya.event_ticket_booking.dto.EventResponse;
import com.ananya.event_ticket_booking.entity.Event;
import com.ananya.event_ticket_booking.exception.ResourceNotFoundException;
import com.ananya.event_ticket_booking.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponse saveEvent(EventRequest request) {

        Event event = new Event();

        event.setName(request.getName());
        event.setVenue(request.getVenue());
        event.setDateTime(request.getDateTime());
        event.setTotalSeats(request.getTotalSeats());

        event.setAvailableSeats(request.getTotalSeats());

        return toResponse(eventRepository.save(event));
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Event getEventById(Long id) {

        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));
    }

    public void deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        eventRepository.delete(event);
    }

    private EventResponse toResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getName(),
                event.getVenue(),
                event.getDateTime(),
                event.getTotalSeats(),
                event.getAvailableSeats());
    }
}
