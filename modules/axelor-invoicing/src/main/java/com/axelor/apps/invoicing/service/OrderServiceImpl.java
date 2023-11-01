package com.axelor.apps.invoicing.service;

import com.axelor.apps.sales.db.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class OrderServiceImpl implements OrderService{

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @Override
    public Order generateInvoiceForTheOrder(Order curOrder) {
        System.out.println("Entered ServiceImpl");

        System.out.println("Quiting ServiceImpl");
        return curOrder;
    }
}
