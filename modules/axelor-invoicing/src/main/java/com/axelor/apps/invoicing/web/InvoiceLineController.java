package com.axelor.apps.invoicing.web;

import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

import java.math.BigDecimal;

public class InvoiceLineController {
    public void loadInvoiceLineUnitPrice(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        InvoiceLine invoiceLine = ctx.asType(InvoiceLine.class);
//        System.out.println(invoiceLine.getProduct());
//        invoiceLine.setUnitPrice(invoiceLine.getProduct().getUnitPrice());
        response.setValue("unitPrice", invoiceLine.getProduct().getUnitPrice());

    }

    public void loadInvoiceLineDescription(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        InvoiceLine invoiceLine = ctx.asType(InvoiceLine.class);
//        System.out.println(invoiceLine.getProduct());
//        invoiceLine.setUnitPrice(invoiceLine.getProduct().getUnitPrice());
        response.setValue("description", invoiceLine.getProduct().getName());
    }

    public void computeInvoiceLineExTaxTotal(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        InvoiceLine invoiceLine = ctx.asType(InvoiceLine.class);
        response.setValue("exTaxTotal", invoiceLine.getUnitPrice().multiply(invoiceLine.getQty()));
    }

    public void computeInvoiceLineTotal(ActionRequest request, ActionResponse response){
        Context ctx = request.getContext();
        InvoiceLine invoiceLine = ctx.asType(InvoiceLine.class);
        response.setValue("total", invoiceLine.getExTaxTotal().multiply(invoiceLine.getTaxRate().divide(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(1))));
//        System.out.println(invoiceLine.getExTaxTotal().multiply(invoiceLine.getTaxRate().divide(BigDecimal.valueOf(100)).add(BigDecimal.valueOf(1))));
    }
}
