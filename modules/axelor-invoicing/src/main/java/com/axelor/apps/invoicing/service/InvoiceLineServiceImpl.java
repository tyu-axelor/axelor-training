package com.axelor.apps.invoicing.service;

import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.db.repo.InvoiceLineRepository;
import com.google.inject.Inject;

public class InvoiceLineServiceImpl implements InvoiceLineService{

    protected InvoiceLineRepository invoiceLineRepository;

    @Inject
    public InvoiceLineServiceImpl(InvoiceLineRepository invoiceLineRepository){
        this.invoiceLineRepository = invoiceLineRepository;
    }


    @Override
    public InvoiceLine setUnitPriceFromProduct(InvoiceLine curInvoiceLine) {



        return null;

    }
}
