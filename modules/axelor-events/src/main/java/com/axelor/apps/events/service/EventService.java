package com.axelor.apps.events.service;

import com.axelor.apps.events.db.Event;

public interface EventService {
    public void setEndDateAndComputeDuration(Event event);

    public void startEvent(Event event);
    public void postponeEvent(Event event);
    public void cancelEvent(Event event);

}
