package com.example.workflow.invoice;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Component("archiveInvoice")
public class ArchiveInvoiceDelegate implements JavaDelegate {
    private final Logger log = LoggerFactory.getLogger(ArchiveInvoiceDelegate.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String invoiceNumber = (String) execution.getVariable("invoiceNumber");

        log.warn("Счет N{} отклонен и заархивирован", invoiceNumber);
    }
}