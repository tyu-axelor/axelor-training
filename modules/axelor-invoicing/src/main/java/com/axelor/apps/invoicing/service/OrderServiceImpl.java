package com.axelor.apps.invoicing.service;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.OrderLine;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    protected OrderRepository orderRepository;

    @Inject
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional(rollbackOn = {Exception.class})
    public Order generateInvoiceForTheOrder(Order curOrder) {
//        The type of OrderLineList is ArrayList
//        System.out.println(curOrder.getOrderLineList().getClass().getName());
        List<OrderLine> orderLineList = curOrder.getOrderLineList();
        Invoice curInvoice = new Invoice();
        List<InvoiceLine> generatedInvoiceLineList = generateInvoiceLineListFromOrderLineList(orderLineList, curInvoice);
//        System.out.println("Show InvoiceLineList" + invoiceLineList.toString());
        curInvoice.setInvoiceLineList(generatedInvoiceLineList);
        curInvoice.setCustomer(curOrder.getCustomer());
        curInvoice.setInvoiceDate(java.time.LocalDate.now());
        curInvoice.setStateSelect(curOrder.getStateSelect());
        curInvoice.setExTaxTotal(curOrder.getExTaxTotal());
        curInvoice.setTotal(curOrder.getTotal());

        curInvoice.setOrders(curOrder);
        curOrder.setInvoice(curInvoice);

        orderRepository.save(curOrder);
//        System.out.println(curInvoice.getOrders());
//        System.out.println(curOrder.getInvoice().getOrders());
        return curOrder;
    }

    @Override
    public List<InvoiceLine> generateInvoiceLineListFromOrderLineList(List<OrderLine> orderLineList, Invoice invoice) {
        List<InvoiceLine> resultInvoiceLineList = new ArrayList<>();
        // orderLine : product, description, qty, unitPrice, exTaxTotal, taxRate, total,
        for(OrderLine orderLine: orderLineList){
            InvoiceLine invoiceLine = new InvoiceLine();
            invoiceLine.setInvoice(invoice);
            invoiceLine.setProduct(orderLine.getProduct());
            invoiceLine.setDescription(orderLine.getDescription());
            invoiceLine.setQty(orderLine.getQty());
            invoiceLine.setUnitPrice(orderLine.getUnitPrice());
            invoiceLine.setExTaxTotal(orderLine.getExTaxTotal());
            invoiceLine.setTaxRate(orderLine.getTaxRate());
            invoiceLine.setTotal(orderLine.getTotal());
            resultInvoiceLineList.add(invoiceLine);
        }
        return resultInvoiceLineList;
    }
}
