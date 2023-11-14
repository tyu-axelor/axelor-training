package com.axelor.apps.events.service;

import com.axelor.apps.events.db.Event;

public interface EventService {
    public long setEndDateAndComputeDuration(Event event);
}
