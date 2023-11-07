package com.axelor.apps.accounting.service;

import com.axelor.apps.invoicing.db.Invoice;

public interface InvoiceService {
    public boolean hasEmptyAccountInInvoiceLines(Invoice invoice);
}
