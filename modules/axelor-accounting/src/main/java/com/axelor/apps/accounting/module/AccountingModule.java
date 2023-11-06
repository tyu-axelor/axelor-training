package com.axelor.apps.accounting.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.accounting.service.AccountingEntryService;
import com.axelor.apps.accounting.service.AccountingEntryServiceImpl;

public class AccountingModule extends AxelorModule {
    @Override
    protected void configure(){
        bind(AccountingEntryService.class).to(AccountingEntryServiceImpl.class);

    }
}
