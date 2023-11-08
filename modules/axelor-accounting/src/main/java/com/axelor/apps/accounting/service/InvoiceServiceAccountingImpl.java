package com.axelor.apps.accounting.service;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.db.AccountingEntryLine;
import com.axelor.apps.accounting.db.repo.AccountingAccountingEntryRepository;
import com.axelor.apps.invoicing.db.Invoice;
import com.axelor.apps.invoicing.db.InvoiceLine;
import com.axelor.apps.invoicing.db.repo.InvoiceRepository;
import com.axelor.apps.invoicing.service.InvoiceServiceImpl;
import com.axelor.inject.Beans;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceAccountingImpl extends InvoiceServiceImpl implements InvoiceServiceAccounting{
    protected InvoiceRepository invoiceRepository;
    protected AccountingAccountingEntryRepository accountingAccountingEntryRepositoryEntryRepository;

    @Inject
    public InvoiceServiceAccountingImpl(InvoiceRepository invoiceRepository, AccountingAccountingEntryRepository accountingAccountingEntryRepositoryEntryRepository) {
        this.invoiceRepository = invoiceRepository;
        this.accountingAccountingEntryRepositoryEntryRepository = accountingAccountingEntryRepositoryEntryRepository;
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
        try {
            Invoice invoice1 = Beans.get(InvoiceRepository.class).find(invoice.getId());
            AccountingEntry accountingEntry = new AccountingEntry();
            accountingEntry.setInvoiceDate(invoice1.getInvoiceDate());
            List<AccountingEntryLine> accountingEntryLineList = new ArrayList<>();
            List<InvoiceLine> invoiceLineList = invoice1.getInvoiceLineList();
            for (InvoiceLine invoiceLine : invoiceLineList) {
                AccountingEntryLine accountingEntryLine = new AccountingEntryLine();
                accountingEntryLine.setCredit(invoiceLine.getTotal());
                accountingEntryLine.setAccount(invoiceLine.getAccount());
                accountingEntryLine.setAccountingEntry(accountingEntry);
                accountingEntryLineList.add(accountingEntryLine);
            }
            AccountingEntryLine accountingEntryLineForDebit = new AccountingEntryLine();
            accountingEntryLineForDebit.setDebit(invoice1.getTotal());
            accountingEntryLineForDebit.setAccount(invoice1.getCustomerAccount());
            accountingEntryLineForDebit.setAccountingEntry(accountingEntry);
            accountingEntryLineList.add(accountingEntryLineForDebit);
            accountingEntry.setAccountingEntryLineList(accountingEntryLineList);
            invoice1.setHasGeneratedAccountingEntry(1);
            invoiceRepository.save(invoice1);
            accountingAccountingEntryRepositoryEntryRepository.save(accountingEntry);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
