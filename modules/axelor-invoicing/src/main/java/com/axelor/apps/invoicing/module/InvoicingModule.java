package com.axelor.apps.invoicing.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.invoicing.service.*;

public class InvoicingModule extends AxelorModule {
    @Override
    protected void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class);
        bind(InvoiceLineService.class).to(InvoiceLineServiceImpl.class);
        bind(InvoiceService.class).to(InvoiceServiceImpl.class);
    }
}
