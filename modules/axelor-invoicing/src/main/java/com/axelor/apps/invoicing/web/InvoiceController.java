package com.axelor.apps.invoicing.web;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.service.OrderService;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *  Set the initial value of the fields listed below:
     *  1. date of invoice (by default today's date)
     *
     *
     * @param request
     * @param response
     */
    public void setInvoiceDateInitValue(ActionRequest request, ActionResponse response){
//        Invoice invoice = request.getContext().asType(Invoice.class);
        response.setValue("invoiceDate", java.time.LocalDate.now());
//        response.setReload(true);
    }

    public void addInvoice(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        Order order = ctx.asType(Order.class);
        order =Beans.get(OrderRepository.class).find(order.getId());
//        System.out.println(order.getOrderLineList());

//        System.out.println("------");

        OrderService orderService = Beans.get(OrderService.class);
        Order orderAfterProcess = orderService.generateInvoiceForTheOrder(order);
        System.out.println(orderAfterProcess);
        System.out.println(orderAfterProcess.getInvoice().toString());
//        response.setValue("invoice", orderAfterProcess.getInvoice());
//        response.setValue("orders", order);
        response.setReload(true);
    }


}