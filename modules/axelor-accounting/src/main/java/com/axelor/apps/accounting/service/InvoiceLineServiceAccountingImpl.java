package com.axelor.apps.accounting.service;

import com.axelor.apps.accounting.db.Account;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.db.repo.InvoiceLineRepository;
import com.axelor.apps.invoicing.service.InvoiceLineServiceImpl;
import com.axelor.apps.sales.db.Product;
import com.google.inject.Inject;

public class InvoiceLineServiceAccountingImpl extends InvoiceLineServiceImpl implements InvoiceLineServiceAccounting{

    protected InvoiceLineRepository invoiceLineRepository;

    @Inject
    public InvoiceLineServiceAccountingImpl(InvoiceLineRepository invoiceLineRepository) {
        super(invoiceLineRepository);
    }

    @Override
    public Account setDefaultValueOfAccount(InvoiceLine invoiceLine) {
        Product product = invoiceLine.getProduct();
        return product.getAccount();
    }
}
