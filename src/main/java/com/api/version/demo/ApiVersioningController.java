package com.api.version.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiVersioningController {

    // 1. URL Versioning
    @GetMapping("/api/v1/products")
    public String getProductsV1() {
        return "Products V1: Simple product list\n";
    }

    @GetMapping("/api/v2/products")
    public String getProductsV2() {
        return "Products V2: Product list with details\n";
    }



    // 2. URL Query Parameter Versioning
    @GetMapping("/api/products")
    public String getProductsQueryVersion(
            @RequestParam(value = "version", defaultValue = "1.0") String apiVersion) {
        if ("1.0".equals(apiVersion)) {
            return "Products (Query V1): Product list with details\n";
        }else if ("2.0".equals(apiVersion)) {
            return "Products (Query V2): Product list with details\n";
        } else {
            return "Please pass correct API version\n";
        }
    }

    // 3. Header Versioning
    @GetMapping("/api/productsHeader")
    public String getProductsHeaderVersion(
            @RequestHeader(value = "API-Version", defaultValue = "v1") String apiVersion) {
        if ("v2".equals(apiVersion)) {
            return "Products (Header V2): Product list with details\n";
        } else {
            return "Products (Header V1): Simple product list\n";
        }
    }

    // 4. Media Type Versioning (Accept Header)
    @GetMapping(value = "/api/productsMediaType", produces = {
            "application/vnd.example.com.v1+json",
            "application/vnd.example.com.v2+json"
    })
    public String getProductsMediaTypeVersion(
            @RequestHeader(value = "Accept") String acceptHeader) {
        if (acceptHeader.contains("v2")) {
            return "Products (Media Type V2): Product list with details\n";
        } else {
            return "Products (Media Type V1): Simple product list\n";
        }
    }


}