package com.axelor.apps.accounting.web;

import com.axelor.apps.invoicing.service.OrderService;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class OrderController {
    public void generateInvoiceFromOrder(ActionRequest request, ActionResponse response) {
        Order order = request.getContext().asType(Order.class);
        order = Beans.get(OrderRepository.class).find(order.getId());
//        OrderAccountingService orderService = Beans.get(OrderAccountingService.class);
        OrderService orderService = Beans.get(OrderService.class);
        orderService.generateInvoiceForTheOrder(order);
        response.setReload(true);
    }

    public void generateInvoiceForLateOrders(ActionRequest request, ActionResponse response) {
        OrderService orderService = Beans.get(OrderService.class);
        orderService.generateInvoiceForLateOrder();
    }

}
