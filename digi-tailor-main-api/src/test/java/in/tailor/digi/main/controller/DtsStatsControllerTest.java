package in.tailor.digi.main.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DtsStatsControllerTest {
    @InjectMocks
    private DtsStatsController dtsStatsController;

    @Test
    public void testApi_ShouldReturnOk() {
        var result = dtsStatsController.testApi();
        Assertions.assertEquals("Stats Api working", result.getBody());
    }
}