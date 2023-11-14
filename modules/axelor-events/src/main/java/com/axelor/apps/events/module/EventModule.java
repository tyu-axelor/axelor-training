package com.axelor.apps.events.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.events.db.repo.EventEventRepository;
import com.axelor.apps.events.db.repo.EventRepository;
import com.axelor.apps.events.service.EventService;
import com.axelor.apps.events.service.EventServiceImpl;

public class EventModule extends AxelorModule {
    @Override
    protected void configure() {

        bind(EventRepository.class).to(EventEventRepository.class);
        bind(EventService.class).to(EventServiceImpl.class);
    }
}
