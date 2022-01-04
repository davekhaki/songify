package com.songify.api.actuator;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "test")
public class TestEndpoint {

    @ReadOperation
    public String get() {
        return "test";
    }
}
