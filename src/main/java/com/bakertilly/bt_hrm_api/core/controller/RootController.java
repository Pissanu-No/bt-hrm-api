package com.bakertilly.bt_hrm_api.core.controller;

import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    private final ObjectProvider<InfoEndpoint> infoEndpointProvider;

    public RootController(ObjectProvider<InfoEndpoint> infoEndpointProvider) {
        this.infoEndpointProvider = infoEndpointProvider;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> root() {
        InfoEndpoint infoEndpoint = infoEndpointProvider.getIfAvailable();
        if (infoEndpoint == null) {
            return Map.of("application", "bt-hrm-api");
        }
        return infoEndpoint.info();
    }
}
