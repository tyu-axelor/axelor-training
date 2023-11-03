package com.axelor.apps.invoicing.web;

import com.axelor.apps.invoicing.service.OrderService;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class OrderController {
    public void addInvoice(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        Order order = ctx.asType(Order.class);
        order = Beans.get(OrderRepository.class).find(order.getId());
//        System.out.println(order.getOrderLineList());

//        System.out.println("------");

        OrderService orderService = Beans.get(OrderService.class);
        Order orderAfterProcess = orderService.generateInvoiceForTheOrder(order);
//        System.out.println(orderAfterProcess);
//        System.out.println(orderAfterProcess.getInvoice().toString());
//        response.setValue("invoice", orderAfterProcess.getInvoice());
//        response.setValue("orders", order);

        response.setReload(true);
//        response.setAttr("invoice", "hideIf","invoice != null");
    }

    public void setDefaultOrderDate(ActionRequest request, ActionResponse response){
        response.setValue("orderDate", java.time.LocalDate.now());
        response.setValue("stateSelect", 0);
    }

    public void generateInvoiceForLateOrders(ActionRequest request, ActionResponse response){
        OrderService orderService = Beans.get(OrderService.class);
        orderService.generateInvoiceForLateOrder();
    }
}
