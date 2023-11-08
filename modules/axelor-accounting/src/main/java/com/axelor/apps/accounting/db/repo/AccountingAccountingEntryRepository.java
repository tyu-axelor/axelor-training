package com.axelor.apps.accounting.db.repo;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.service.AccountingEntryService;
import com.axelor.inject.Beans;

import javax.persistence.PersistenceException;

public class AccountingAccountingEntryRepository extends AccountingEntryRepository {
    @Override
    public AccountingEntry save(AccountingEntry accountingEntry){

        AccountingEntryService accountingEntryService = Beans.get(AccountingEntryService.class);
        boolean isEqual = accountingEntryService.isSumOfDebitEqualsSumOfCredit(accountingEntry);
        if (!isEqual) {
            throw new PersistenceException("The sum of debit is not equal to the sum of credit");
        } else {
            return super.save(accountingEntry);
        }


    }
}
