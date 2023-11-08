package com.axelor.apps.accounting.service;

import com.axelor.apps.invoicing.db.Invoice;

public interface InvoiceServiceAccounting {
    public boolean hasEmptyAccountInInvoiceLines(Invoice invoice);

    public void generateAccountingEntryForInvoice(Invoice invoice);
}
