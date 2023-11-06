package com.axelor.apps.accounting.service;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.db.repo.AccountingEntryRepository;
import com.google.inject.Inject;

public interface AccountingEntryService {


    public boolean isSumOfDebitEqualsSumOfCredit(AccountingEntry accountingEntry);

}
