package com.framework.api.builder;

import java.util.HashMap;
import java.util.Map;

import config.ConfigReader;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

/**
 * Request Builder for creating REST API requests with fluent interface
 * Uses direct RestAssured approach to avoid assertionClosure issues
 */
public class RequestBuilder {
    
    private String baseUrl;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private Map<String, String> formParams = new HashMap<>();
    private String contentType;
    private Object body;
        
    public RequestBuilder() {
        this.baseUrl = ConfigReader.get("base.url");
        // Set default content type from configuration
        this.contentType = ConfigReader.get("api.header.content.type.json");
        // Set default headers from configuration
        String acceptHeader = ConfigReader.get("api.header.accept");
        if (acceptHeader != null && !acceptHeader.isEmpty()) {
            this.headers.put("Accept", acceptHeader);
        }
    }
    
    /**
     * Sets content type to JSON
     */
    public RequestBuilder setJsonContentType() {
        this.contentType = ConfigReader.get("api.header.content.type.json");
        return this;
    }
    
    /**
     * Sets content type to form-urlencoded
     */
    public RequestBuilder setFormContentType() {
        this.contentType = ConfigReader.get("api.header.content.type.form");
        return this;
    }
    
    /**
     * Adds form parameter
     */
    public RequestBuilder addFormParam(String key, String value) {
        this.formParams.put(key, value);
        return this;
    }
    
    /**
     * Adds multiple form parameters
     */
    public RequestBuilder addFormParams(Map<String, String> params) {
        this.formParams.putAll(params);
        return this;
    }
    
    /**
     * Adds query parameter
     */
    public RequestBuilder addQueryParam(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }
    
    /**
     * Adds header
     */
    public RequestBuilder addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }
    
    /**
     * Sets request body
     */
    public RequestBuilder setBody(Object body) {
        this.body = body;
        return this;
    }
    
    /**
     * Builds the RequestSpecification using direct RestAssured approach
     */
    public RequestSpecification build() {
        RequestSpecification request = RestAssured.given();
        
        // Set base URI
        request.baseUri(baseUrl);
        
        // Set content type
        request.contentType(contentType);
        
        // Add headers
        for (Map.Entry<String, String> header : headers.entrySet()) {
            request.header(header.getKey(), header.getValue());
        }
        
        // Add query parameters
        for (Map.Entry<String, String> param : queryParams.entrySet()) {
            request.queryParam(param.getKey(), param.getValue());
        }
        
        // Add form parameters
        for (Map.Entry<String, String> param : formParams.entrySet()) {
            request.formParam(param.getKey(), param.getValue());
        }
        
        // Set body if present
        if (body != null) {
            request.body(body);
        }
        
        return request;
    }
}
