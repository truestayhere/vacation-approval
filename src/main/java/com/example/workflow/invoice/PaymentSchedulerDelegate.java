package com.example.workflow.invoice;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component("paymentScheduler")
public class PaymentSchedulerDelegate implements JavaDelegate {
    private final Logger log = LoggerFactory.getLogger(PaymentSchedulerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String invoiceNumber = (String) execution.getVariable("invoiceNumber");
        Long amount = (Long) execution.getVariable("amount");

        log.info("Счет N{} на сумму {} подтвержден и отправлен на оплату", invoiceNumber, amount);
    }
}