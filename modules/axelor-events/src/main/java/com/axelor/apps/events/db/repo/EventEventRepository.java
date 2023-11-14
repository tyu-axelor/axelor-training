package com.axelor.apps.events.db.repo;

import com.axelor.apps.events.db.Event;
import com.axelor.inject.Beans;

public class EventEventRepository extends EventRepository{
    @Override
    public Event save(Event entity) {
        entity.setIsSave(true);
        return super.save(entity);
    }
}
