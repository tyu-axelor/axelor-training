package com.axelor.apps.invoicing.web;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.service.OrderService;
import com.axelor.apps.sales.db.Order;
import com.axelor.apps.sales.db.Product;
import com.axelor.apps.sales.db.repo.OrderRepository;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceController {
//    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

    public void computeInvoiceExTaxTotal(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        Invoice invoice = ctx.asType(Invoice.class);
        List<InvoiceLine> invoiceLineList = invoice.getInvoiceLineList();
        BigDecimal exTaxTotalTemp = new BigDecimal(0);
        for(InvoiceLine curInvoiceLine : invoiceLineList){
            exTaxTotalTemp = exTaxTotalTemp.add(curInvoiceLine.getExTaxTotal());
        }
        response.setValue("exTaxTotal", exTaxTotalTemp);
//        System.out.println(exTaxTotalTemp);
    }

    public void computeInvoiceTotal(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        Invoice invoice = ctx.asType(Invoice.class);
        List<InvoiceLine> invoiceLineList = invoice.getInvoiceLineList();
        BigDecimal totalTemp = new BigDecimal(0);
        for(InvoiceLine curInvoiceLine : invoiceLineList){
            totalTemp = totalTemp.add(curInvoiceLine.getTotal());
        }
        response.setValue("total", totalTemp);
    }

    public void setStateSelectToValidate(ActionRequest request, ActionResponse response){
        response.setValue("stateSelect", 1);
    }




}
