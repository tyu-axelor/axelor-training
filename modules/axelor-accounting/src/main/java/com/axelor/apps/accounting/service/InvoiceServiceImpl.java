package com.axelor.apps.accounting.service;

import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.db.repo.InvoiceRepository;
import com.google.inject.Inject;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceService{
    protected InvoiceRepository invoiceRepository;

    @Inject
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public boolean hasEmptyAccountInInvoiceLines(Invoice invoice) {
        List<InvoiceLine> invoiceLineList = invoice.getInvoiceLineList();
        for (InvoiceLine invoiceLine: invoiceLineList) {
            if(invoiceLine.getAccount() == null)
                return true;
        }
        return false;
    }
}
