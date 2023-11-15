package com.axelor.apps.events.service;

import com.axelor.apps.events.db.Event;
import com.axelor.apps.events.db.repo.EventEventRepository;
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
    public long setEndDateAndComputeDuration(Event event) {
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
        eventObject.setHasEnded(true);
        eventRepository.save(eventObject);
        return seconds;


    }
}
