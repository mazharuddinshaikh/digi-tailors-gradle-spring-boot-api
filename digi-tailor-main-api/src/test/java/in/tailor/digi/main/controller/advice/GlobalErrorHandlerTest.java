package in.tailor.digi.main.controller.advice;

import in.tailor.digi.model.DtsApiResponse;
import in.tailor.digi.model.DtsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class GlobalErrorHandlerTest {
    @InjectMocks
    GlobalErrorHandler globalErrorHandler;

    @Test
    void getDtsException() {
        DtsException dtsException = new DtsException(DtsApiResponse.<String>builder().httpStatus(403)
                .message("Something went wrong").build());
        var result = globalErrorHandler.getDtsException(dtsException);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, result.getStatusCode());

    }
}