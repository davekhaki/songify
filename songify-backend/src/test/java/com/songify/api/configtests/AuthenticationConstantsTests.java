package com.songify.api.configtests;

import com.songify.api.config.AuthenticationConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationConstantsTests {

    @Test
    void exceptionThrowsWhenMakingInstanceOfConstantsClass(){
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            AuthenticationConstants constants = AuthenticationConstants.create();
            assertNotNull(constants);
            assertNotNull(AuthenticationConstants.create());
        });

        String expectedMessage = "Utility class";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
