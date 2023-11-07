package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.db.Account;
import com.axelor.apps.accounting.service.InvoiceLineService;
import com.axelor.apps.accounting.service.InvoiceLineServiceImpl;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.sales.db.Product;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class InvoiceLineController {
    public void setAccountDefaultValue(ActionRequest request, ActionResponse response) {
        InvoiceLine invoiceLine = request.getContext().asType(InvoiceLine.class);
        InvoiceLineService invoiceLineService = Beans.get(InvoiceLineService.class);
        Account account = invoiceLineService.setDefaultValueOfAccount(invoiceLine);
        response.setValue("account", account);
    }
}
