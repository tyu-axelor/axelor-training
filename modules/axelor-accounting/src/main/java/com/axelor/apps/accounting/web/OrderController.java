package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.service.OrderService;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class OrderController {
    public void generatedInvoiceFromOrder(ActionRequest request, ActionResponse response){
        Order order = request.getContext().asType(Order.class);
        order = Beans.get(OrderRepository.class).find(order.getId());
        OrderService orderService = Beans.get(OrderService.class);
        orderService.generateInvoiceForOrder(order);
        response.setReload(true);
    }
}
