package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.service.InvoiceServiceAccounting;
import com.axelor.apps.accounting.service.InvoiceServiceAccountingImpl;
import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.service.InvoiceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

public class InvoiceController {


    public void checkEmptyAccountInInvoiceLines(ActionRequest request, ActionResponse response) {
        Context context = request.getContext();
        Invoice invoice = context.asType(Invoice.class);
        InvoiceServiceAccounting invoiceServiceAccounting = Beans.get(InvoiceServiceAccounting.class);
        boolean hasEmptyAccount = invoiceServiceAccounting.hasEmptyAccountInInvoiceLines(invoice);
        if (hasEmptyAccount) {
            response.setError("At least one invoice line has an empty account, please check again!");
        }
    }

    public void generateAccountingEntryForInvoice(ActionRequest request, ActionResponse response) {
        Context context = request.getContext();
        Invoice invoice = context.asType(Invoice.class);
        InvoiceServiceAccounting invoiceServiceAccounting = Beans.get(InvoiceServiceAccounting.class);
        invoiceServiceAccounting.generateAccountingEntryForInvoice(invoice);
        response.setReload(true);
    }

    public void setDefaultValueOfHasGeneratedAccountingEntry(ActionRequest request, ActionResponse response) {
        response.setValue("hasGeneratedAccountingEntry", 0);
    }
}
