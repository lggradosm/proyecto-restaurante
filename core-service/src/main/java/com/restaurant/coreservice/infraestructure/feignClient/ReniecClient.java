package com.restaurant.coreservice.infraestructure.feignClient;

import com.restaurant.coreservice.aggregates.response.ResponseReniec;
import com.restaurant.coreservice.aggregates.response.ResponseSunat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "reniec-client",url = "https://api.apis.net.pe/v2/reniec/")

public interface ReniecClient {
    @GetMapping("/dni")
    ResponseReniec getInfoReniec (@RequestParam("numero") String numero, @RequestHeader("Authorization") String authorizationHeader);
}
