package com.axelor.apps.invoicing.service;

import com.axelor.apps.invoicing.db.InvoiceLine;

public interface InvoiceLineService {
    public InvoiceLine setUnitPriceFromProduct(InvoiceLine curInvoiceLine);
}
