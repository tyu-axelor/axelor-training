package com.axelor.apps.events.web;

import com.axelor.apps.events.db.Event;
import com.axelor.apps.events.service.EventService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class EventController {

    public void setDefaultValue(ActionRequest request, ActionResponse response) {
        response.setValue("isSave", false);
    }

    public void startEvent(ActionRequest request, ActionResponse response) {

//        System.out.println("Event Started!");
        response.setValue("hasStarted", true);
        response.setValue("hasPostponed", 0);
        response.setValue("status", 1);
        if (request.getContext().asType(Event.class).getStartDate() == null) {
            response.setValue("startDate", java.time.LocalDateTime.now());
        }

    }

    public void endEvent(ActionRequest request, ActionResponse response) {
//        System.out.println("Event Ended!");
        response.setValue("hasEnded", true);
        response.setValue("hasPostponed", 0);
        response.setValue("status", 2);
        Event event = request.getContext().asType(Event.class);
        EventService eventService = Beans.get(EventService.class);
        long duration = eventService.setEndDateAndComputeDuration(event);
        response.setReload(true);
    }

    public void postponeEvent(ActionRequest request, ActionResponse response) {
//        System.out.println("Event postponed!");
        response.setValue("hasPostponed", true);
        response.setValue("hasStarted", 0);
    }

    public void cancelEvent(ActionRequest request, ActionResponse response) {

//        System.out.println("Event canceled!");
        response.setValue("hasCanceled", true);
        response.setValue("status", 3);
        response.setValue("endDate", null);

    }


}
