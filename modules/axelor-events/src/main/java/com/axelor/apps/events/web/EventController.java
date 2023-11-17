package com.axelor.apps.events.web;

import com.axelor.apps.events.db.Event;
import com.axelor.apps.events.service.EventService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

import java.time.LocalDateTime;

public class EventController {

    public void setDefaultValue(ActionRequest request, ActionResponse response) {
        response.setValue("isSave", false);
    }

    public void startEvent(ActionRequest request, ActionResponse response) {

//        System.out.println("Event Started!");
//        response.setValue("status", 1);
//        if (request.getContext().asType(Event.class).getStartDate() == null) {
//            response.setValue("startDate", java.time.LocalDateTime.now());
//        }
        Event event = request.getContext().asType(Event.class);
        EventService eventService = Beans.get(EventService.class);
        eventService.startEvent(event);
        response.setReload(true);

    }

    public void endEvent(ActionRequest request, ActionResponse response) {
//        System.out.println("Event Ended!");
//        response.setValue("status", 2);
        Event event = request.getContext().asType(Event.class);
        EventService eventService = Beans.get(EventService.class);
        eventService.setEndDateAndComputeDuration(event);
        response.setReload(true);
    }

    public void postponeEvent(ActionRequest request, ActionResponse response) {
//        System.out.println("Event postponed!");
//        response.setValue("status", 4);
        Event event = request.getContext().asType(Event.class);
        EventService eventService = Beans.get(EventService.class);
        eventService.postponeEvent(event);
        response.setReload(true);

    }

    public void cancelEvent(ActionRequest request, ActionResponse response) {

//        System.out.println("Event canceled!");
//        response.setValue("status", 3);
//        response.setValue("endDate", null);
        Event event = request.getContext().asType(Event.class);
        EventService eventService = Beans.get(EventService.class);
        eventService.cancelEvent(event);
        response.setReload(true);

    }

    public void startDateOnChange(ActionRequest request, ActionResponse response) {
        Event event = request.getContext().asType(Event.class);
        LocalDateTime startDate = event.getStartDate();
        LocalDateTime endDate = event.getEndDate();
        if (startDate != null && endDate == null) {
            response.setValue("status", 1);
        }
        if (startDate == null) {
            response.setValue("status", 0);
            response.setValue("endDate", null);
        }
    }

    public void endDateOnChange(ActionRequest request, ActionResponse response) {
        Event event = request.getContext().asType(Event.class);
        LocalDateTime endDate = event.getEndDate();
        if (endDate != null) {
            response.setValue("status", 2);
        } else {
            response.setValue("status", 1);
        }
    }

//    public void setAssociatedTo(ActionRequest request, ActionResponse response) {
//        Event event = request.getContext().asType(Event.class);
//        EventService eventService = Beans.get(EventService.class);
//        eventService.setAssociatedTo(event);
//        response.setReload(true);
//    }


}
