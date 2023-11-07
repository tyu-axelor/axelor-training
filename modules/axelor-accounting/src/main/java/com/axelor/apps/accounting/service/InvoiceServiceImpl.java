package com.axelor.apps.accounting.service;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.db.AccountingEntryLine;
import com.axelor.apps.accounting.db.repo.AccountingEntryRepository;
import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.db.repo.InvoiceRepository;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    protected InvoiceRepository invoiceRepository;
    protected AccountingEntryRepository accountingEntryRepository;

    @Inject
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, AccountingEntryRepository accountingEntryRepository) {
        this.invoiceRepository = invoiceRepository;
        this.accountingEntryRepository = accountingEntryRepository;
    }

    @Override
    public boolean hasEmptyAccountInInvoiceLines(Invoice invoice) {
        List<InvoiceLine> invoiceLineList = invoice.getInvoiceLineList();
        for (InvoiceLine invoiceLine : invoiceLineList) {
            if (invoiceLine.getAccount() == null)
                return true;
        }
        return false;
    }

    @Transactional(rollbackOn = {Exception.class})
    @Override
    public void generateAccountingEntryForInvoice(Invoice invoice) {
        AccountingEntry accountingEntry = new AccountingEntry();
        accountingEntry.setInvoiceDate(invoice.getInvoiceDate());
        List<AccountingEntryLine> accountingEntryLineList = new ArrayList<>();
        List<InvoiceLine> invoiceLineList = invoice.getInvoiceLineList();
        for (InvoiceLine invoiceLine : invoiceLineList) {
            AccountingEntryLine accountingEntryLine = new AccountingEntryLine();
            accountingEntryLine.setCredit(invoiceLine.getTotal());
            accountingEntryLine.setAccount(invoiceLine.getAccount());
            accountingEntryLine.setAccountingEntry(accountingEntry);
            accountingEntryLineList.add(accountingEntryLine);
        }
        AccountingEntryLine accountingEntryLineForDebit = new AccountingEntryLine();
        accountingEntryLineForDebit.setDebit(invoice.getTotal());
        accountingEntryLineForDebit.setAccount(invoice.getCustomerAccount());
        accountingEntryLineForDebit.setAccountingEntry(accountingEntry);
        accountingEntryLineList.add(accountingEntryLineForDebit);
        accountingEntry.setAccountingEntryLineList(accountingEntryLineList);

        accountingEntryRepository.save(accountingEntry);

    }
}
