package com.axelor.apps.accounting.web;

import com.axelor.apps.accounting.db.AccountingEntry;
import com.axelor.apps.accounting.db.AccountingEntryLine;
import com.axelor.apps.accounting.service.AccountingEntryService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.axelor.rpc.Context;

import java.util.List;

public class AccountingEntryController {
    public void checkDebitAndCredit(ActionRequest request, ActionResponse response) {
        Context context = request.getContext();
        AccountingEntry accountingEntry = context.asType(AccountingEntry.class);
//        System.out.println(accountingEntry.getAccountingEntryLineList());
        AccountingEntryService accountingEntryService = Beans.get(AccountingEntryService.class);
        boolean isEqual = accountingEntryService.isSumOfDebitEqualsSumOfCredit(accountingEntry);
        if (!isEqual) {
            response.setError("The sum of debit is not equal to the sum of credit!");
        }
    }
}
