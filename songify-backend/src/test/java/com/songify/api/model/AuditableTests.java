package com.songify.api.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuditableTests {

    @Test
    void auditableGetAndSetCreatedByTest(){
        Auditable<String> auditableClass = Mockito.mock(
                Auditable.class,
            Mockito.CALLS_REAL_METHODS);

        auditableClass.setCreatedBy("me");
        assertEquals("me", auditableClass.getCreatedBy());

    }
}
