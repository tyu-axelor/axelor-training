package com.axelor.apps.invoicing.service;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.OrderLine;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    public Order generateInvoiceForTheOrder(Order curOrder);

    public List<InvoiceLine> generateInvoiceLineListFromOrderLineList(List<OrderLine> orderLineList, Invoice invoice);

    public void generateInvoiceForLateOrder();

    public void generateInvoiceForEachOrder(List<Order> orderList);
}
