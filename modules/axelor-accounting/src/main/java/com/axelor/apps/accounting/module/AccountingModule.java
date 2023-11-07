package com.axelor.apps.accounting.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.accounting.service.*;

public class AccountingModule extends AxelorModule {
    @Override
    protected void configure(){
        bind(AccountingEntryService.class).to(AccountingEntryServiceImpl.class);
        bind(InvoiceLineService.class).to(InvoiceLineServiceImpl.class);
        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(InvoiceService.class).to(InvoiceServiceImpl.class);

    }
}
