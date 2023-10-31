package com.axelor.apps.invoicing.web;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class InvoiceController {
    private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     *  Set the initial value of the fields listed below:
     *  1. date of invoice (by default today's date)
     *
     *
     * @param request
     * @param response
     */
    public void setInvoiceDateInitValue(ActionRequest request, ActionResponse response){
        Invoice invoice = request.getContext().asType(Invoice.class);
        response.setValue("invoiceDate", java.time.LocalDate.now());
//        response.setReload(true);
    }
}
