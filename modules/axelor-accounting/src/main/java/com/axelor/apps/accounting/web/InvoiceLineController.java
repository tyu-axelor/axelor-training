package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.db.Account;
import com.axelor.apps.accounting.service.InvoiceLineServiceAccounting;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class InvoiceLineController {
    public void setAccountDefaultValue(ActionRequest request, ActionResponse response) {
        InvoiceLine invoiceLine = request.getContext().asType(InvoiceLine.class);
        InvoiceLineServiceAccounting invoiceLineServiceAccounting = Beans.get(InvoiceLineServiceAccounting.class);
        Account account = invoiceLineServiceAccounting.setDefaultValueOfAccount(invoiceLine);
        response.setValue("account", account);
    }
}
