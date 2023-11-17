package com.axelor.apps.events.service;

import com.axelor.apps.contact.db.Contact;
import com.axelor.apps.contact.db.repo.ContactRepository;
import com.axelor.apps.events.db.Event;
import com.axelor.apps.events.db.repo.EventRepository;
import com.axelor.apps.sales.db.Product;
import com.axelor.apps.sales.db.repo.ProductRepository;
import com.axelor.inject.Beans;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


import java.time.Duration;
import java.time.LocalDateTime;

public class EventServiceImpl implements EventService {

    protected EventRepository eventRepository;
    protected ContactRepository contactRepository;
    protected ProductRepository productRepository;

    @Inject
    public EventServiceImpl(EventRepository eventRepository, ContactRepository contactRepository, ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.contactRepository = contactRepository;
        this.productRepository = productRepository;
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

    @Transactional
    @Override
    public void setAssociatedTo(Event event) {
        Event eventObject = Beans.get(EventRepository.class).find(event.getId());
        String type = eventObject.getAssociatedToSelect();
        Long objectId = eventObject.getAssociatedToSelectId();
        if("com.axelor.apps.sales.db.Product".equals(type)){
            Product product = Beans.get(ProductRepository.class).find(objectId);
            product.addEventListItem(eventObject);
            eventObject.setProduct(product);
            productRepository.save(product);
            eventRepository.save(eventObject);
        }
        else{
            Contact contact = Beans.get(ContactRepository.class).find(objectId);
            contact.addEventListItem(eventObject);
            eventObject.setContact(contact);
            contactRepository.save(contact);
            eventRepository.save(eventObject);
        }

    }
}
