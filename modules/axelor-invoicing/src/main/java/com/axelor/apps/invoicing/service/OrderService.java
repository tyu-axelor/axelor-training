package com.axelor.apps.invoicing.service;

import com.axelor.apps.sales.db.Order;

public interface OrderService {
    public Order generateInvoiceForTheOrder(Order curOrder);
}
