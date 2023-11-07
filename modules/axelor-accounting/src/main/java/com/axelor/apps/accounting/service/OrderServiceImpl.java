package com.axelor.apps.accounting.service;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.OrderLine;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.db.JPA;
import com.axelor.db.Query;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    protected OrderRepository orderRepository;


    @Inject
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    /**
     * This method generates the Invoice
     * @param curOrder
     */
    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void generateInvoiceForOrder(Order curOrder) {
        List<OrderLine> orderLineList = curOrder.getOrderLineList();
        Invoice curInvoice = new Invoice();
        List<InvoiceLine> generatedInvoiceLineList = generateInvoiceLineListFromOrderLineList(orderLineList, curInvoice);
        //Set the invoiceLineList field.
        curInvoice.setInvoiceLineList(generatedInvoiceLineList);
        curInvoice.setCustomer(curOrder.getCustomer());
        curInvoice.setInvoiceDate(java.time.LocalDate.now());
        curInvoice.setStateSelect(curOrder.getStateSelect());
        curInvoice.setExTaxTotal(curOrder.getExTaxTotal());
        curInvoice.setTotal(curOrder.getTotal());
        //set the customerAccount field in an Invoice Object
        curInvoice.setCustomerAccount(curOrder.getCustomerAccount());


        curInvoice.setOrders(curOrder);
        curOrder.setInvoice(curInvoice);
        curOrder.setBillingDate(curInvoice.getInvoiceDate());




        orderRepository.save(curOrder);


    }

    /**
     * this method generates the InvoiceLineList field of an Invoice object from an orderLineList.
     * @param orderLineList
     * @param invoice
     * @return generated InvoiceLineList
     */
    @Override
    public List<InvoiceLine> generateInvoiceLineListFromOrderLineList(List<OrderLine> orderLineList, Invoice invoice) {
        List<InvoiceLine> resultInvoiceLineList = new ArrayList<>();
        // orderLine : product, description, qty, unitPrice, exTaxTotal, taxRate, total,
        InvoiceLine invoiceLine;
        for (OrderLine orderLine : orderLineList) {
            invoiceLine = new InvoiceLine();
            invoiceLine.setInvoice(invoice);
            invoiceLine.setProduct(orderLine.getProduct());
            invoiceLine.setDescription(orderLine.getDescription());
            invoiceLine.setQty(orderLine.getQty());
            invoiceLine.setUnitPrice(orderLine.getUnitPrice());
            invoiceLine.setExTaxTotal(orderLine.getExTaxTotal());
            invoiceLine.setTaxRate(orderLine.getTaxRate());
            invoiceLine.setTotal(orderLine.getTotal());
            invoiceLine.setAccount(orderLine.getProduct().getAccount());
            resultInvoiceLineList.add(invoiceLine);
        }
        return resultInvoiceLineList;
    }

    @Override
    public void generateInvoiceForLateOrders() {
        Query<Order> orderListQuery = orderRepository.all().filter(
                "self.forecastBillingDate < :curDate AND self.invoice = null"
        );
        orderListQuery.bind("curDate", java.time.LocalDate.now());

        int limit = 50;
        int offSet = 0;
        List<Order> orderList;
        do {
            orderList = orderListQuery.fetch(limit, offSet);
            if(orderList.isEmpty()) break;
            else{
                generateInvoiceForEachLateOrder(orderList);
                JPA.clear();
                offSet += limit;
            }
        }while (true);
    }

    @Override
    public void generateInvoiceForEachLateOrder(List<Order> orderList) {
        for (Order order : orderList) {
            generateInvoiceForOrder(order);
        }
    }
}
