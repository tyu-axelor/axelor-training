package com.axelor.apps.accounting.service;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.db.AccountingEntryLine;
import com.axelor.apps.accounting.db.repo.AccountingEntryRepository;
import com.google.inject.Inject;

import java.math.BigDecimal;

public class AccountingEntryServiceImpl implements AccountingEntryService {

    protected AccountingEntryRepository accountingEntryRepository;

    @Inject
    public AccountingEntryServiceImpl(AccountingEntryRepository accountingEntryRepository) {
        this.accountingEntryRepository = accountingEntryRepository;


    }

    @Override
    public boolean isSumOfDebitEqualsSumOfCredit(AccountingEntry accountingEntry) {
//        System.out.println("isSumOfDebitEqualsSumOfCredit");
        BigDecimal debitSum = new BigDecimal(0);
        BigDecimal creditSum = new BigDecimal(0);

        for (AccountingEntryLine accountingEntryLine : accountingEntry.getAccountingEntryLineList()) {
            debitSum = debitSum.add(accountingEntryLine.getDebit());
            creditSum = creditSum.add(accountingEntryLine.getCredit());
        }
        return debitSum.equals(creditSum);


    }
}
