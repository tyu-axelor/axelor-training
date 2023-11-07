package com.axelor.apps.accounting.service;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.OrderLine;
import com.axelor.apps.sales.db.repo.OrderRepository;

import java.util.List;

public interface OrderService {

    public List<InvoiceLine> generateInvoiceLineListFromOrderLineList(List<OrderLine> orderLineList, Invoice invoice);
    public void generateInvoiceForOrder(Order order);

    public void generateInvoiceForLateOrders();

    public void generateInvoiceForEachLateOrder(List<Order> orderList);
}
