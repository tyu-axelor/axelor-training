package com.axelor.apps.invoicing.module;

import com.axelor.app.AxelorModule;
import com.axelor.apps.invoicing.service.OrderService;
import com.axelor.apps.invoicing.service.OrderServiceImpl;

public class InvoicingModule extends AxelorModule {
    @Override
    protected void configure() {
        bind(OrderService.class).to(OrderServiceImpl.class);
    }
}
