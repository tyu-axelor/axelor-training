package com.axelor.apps.accounting.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.accounting.db.repo.AccountingAccountingEntryRepository;
import com.axelor.apps.accounting.db.repo.AccountingEntryRepository;
import com.axelor.apps.accounting.service.*;
import com.axelor.apps.invoicing.service.InvoiceLineServiceImpl;
import com.axelor.apps.invoicing.service.InvoiceServiceImpl;
import com.axelor.apps.invoicing.service.OrderServiceImpl;

public class AccountingModule extends AxelorModule {
    @Override
    protected void configure() {
        bind(AccountingEntryService.class).to(AccountingEntryServiceImpl.class);


        bind(InvoiceServiceImpl.class).to(InvoiceServiceAccountingImpl.class);
        bind(InvoiceServiceAccounting.class).to(InvoiceServiceAccountingImpl.class);

        bind(InvoiceLineServiceImpl.class).to(InvoiceLineServiceAccountingImpl.class);
        bind(InvoiceLineServiceAccounting.class).to(InvoiceLineServiceAccountingImpl.class);


        bind(OrderServiceImpl.class).to(OrderServiceAccountingImpl.class);

        bind(AccountingEntryRepository.class).to(AccountingAccountingEntryRepository.class);
    }
}
