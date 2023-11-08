package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.service.InvoiceService;
import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class InvoiceController {
    public void checkEmptyAccountInInvoiceLines(ActionRequest request, ActionResponse response) {
        Context context = request.getContext();
        Invoice invoice = context.asType(Invoice.class);
        InvoiceService invoiceService = Beans.get(InvoiceService.class);
        boolean hasEmptyAccount = invoiceService.hasEmptyAccountInInvoiceLines(invoice);
        if (hasEmptyAccount) {
            response.setError("At least one invoice line has an empty account, please check again!");
        }
    }

    public void generateAccountingEntryForInvoice(ActionRequest request, ActionResponse response) {
        Context context = request.getContext();
        Invoice invoice = context.asType(Invoice.class);
        InvoiceService invoiceService = Beans.get(InvoiceService.class);
        invoiceService.generateAccountingEntryForInvoice(invoice);
        response.setReload(true);
    }

    public void setDefaultValueOfHasGeneratedAccountingEntry(ActionRequest request, ActionResponse response) {
        response.setValue("hasGeneratedAccountingEntry", 0);
    }
}
