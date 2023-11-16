package com.axelor.apps.events.service;

import com.axelor.apps.events.db.Event;
import com.axelor.apps.events.db.repo.EventRepository;
import com.axelor.inject.Beans;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


import java.time.Duration;
import java.time.LocalDateTime;

public class EventServiceImpl implements EventService {

    protected EventRepository eventRepository;

    @Inject
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    @Override
    public void setEndDateAndComputeDuration(Event event) {
        Event eventObject = Beans.get(EventRepository.class).find(event.getId());
        LocalDateTime startDate = eventObject.getStartDate();
        LocalDateTime endDate = eventObject.getEndDate();
        if (endDate == null) {
            endDate = java.time.LocalDateTime.now();
            eventObject.setEndDate(endDate);
        }
        Duration duration = Duration.between(startDate, endDate);
        long seconds = duration.getSeconds();
        eventObject.setDuration(seconds);
        eventObject.setStatus(2);
        eventRepository.save(eventObject);
    }

    @Transactional
    @Override
    public void startEvent(Event event) {
        Event eventObject = Beans.get(EventRepository.class).find(event.getId());
        if (eventObject.getStartDate() == null) {
            eventObject.setStartDate(java.time.LocalDateTime.now());
        }
        eventObject.setStatus(1);
        eventRepository.save(eventObject);
    }


    @Transactional
    @Override
    public void postponeEvent(Event event) {
        Event eventObject = Beans.get(EventRepository.class).find(event.getId());
        eventObject.setStatus(4);
        eventRepository.save(eventObject);
    }

    @Transactional
    @Override
    public void cancelEvent(Event event) {
        Event eventObject = Beans.get(EventRepository.class).find(event.getId());
        eventObject.setStatus(3);
        eventObject.setEndDate(null);
    }
}
