package com.songify.api.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuditableTests {

    @Test
    void auditableGetAndSetCreatedByTest(){
        Auditable<String> auditableClass = Mockito.mock(
                Auditable.class,
                Mockito.CALLS_REAL_METHODS);

        auditableClass.setCreatedBy("me");
        assertEquals("me", auditableClass.getCreatedBy());
    }

    @Test
    void auditableGetAndSetCreatedDateTest(){
        Auditable<String> auditableClass = Mockito.mock(
                Auditable.class,
                Mockito.CALLS_REAL_METHODS);

        Date date = new Date();
        date.setTime(2L);
        auditableClass.setCreatedDate(date);
        assertEquals(2L, auditableClass.getCreatedDate().getTime());
    }

    @Test
    void auditableGetAndSetLastModifiedByTest(){
        Auditable<String> auditableClass = Mockito.mock(
                Auditable.class,
                Mockito.CALLS_REAL_METHODS);

        auditableClass.setLastModifiedBy("me");
        assertEquals("me", auditableClass.getLastModifiedBy());
    }

    @Test
    void auditableGetAndSetLastModifiedDateTest(){
        Auditable<String> auditableClass = Mockito.mock(
                Auditable.class,
                Mockito.CALLS_REAL_METHODS);

        Date date = new Date();
        date.setTime(2L);
        auditableClass.setLastModifiedDate(date);
        assertEquals(2L, auditableClass.getLastModifiedDate().getTime());
    }
}
