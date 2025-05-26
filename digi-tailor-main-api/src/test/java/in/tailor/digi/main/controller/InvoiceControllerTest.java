package in.tailor.digi.main.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {
    @InjectMocks
    private InvoiceController invoiceController;

    @Test
    public void testApi_ShouldReturnOk() throws Exception {
        var result = invoiceController.testApi();
        Assertions.assertEquals("Invoice Api working", result.getBody());
    }
}