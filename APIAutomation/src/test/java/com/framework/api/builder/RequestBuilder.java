package com.framework.api.builder;

import java.util.Map;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Request Builder for creating REST API requests with fluent interface
 * Provides abstraction over RestAssured RequestSpecBuilder
 */
public class RequestBuilder {
    
    private RequestSpecBuilder requestSpecBuilder;
    
    static {
        // Initialize RestAssured static configuration once
        RestAssured.baseURI = ConfigReader.get("base.url");
    }
    
    public RequestBuilder() {
        this.requestSpecBuilder = new RequestSpecBuilder();
        setBaseConfiguration();
    }
    
    /**
     * Sets base URL and common configuration
     */
    private void setBaseConfiguration() {
        requestSpecBuilder.setBaseUri(ConfigReader.get("base.url"));
    }
    
    /**
     * Sets content type to JSON
     */
    public RequestBuilder setJsonContentType() {
        requestSpecBuilder.setContentType("application/json");
        return this;
    }
    
    /**
     * Sets content type to form-urlencoded
     */
    public RequestBuilder setFormContentType() {
        requestSpecBuilder.setContentType("application/x-www-form-urlencoded");
        return this;
    }
    
    /**
     * Adds form parameter
     */
    public RequestBuilder addFormParam(String key, String value) {
        requestSpecBuilder.addFormParam(key, value);
        return this;
    }
    
    /**
     * Adds multiple form parameters
     */
    public RequestBuilder addFormParams(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            requestSpecBuilder.addFormParam(entry.getKey(), entry.getValue());
        }
        return this;
    }
    
    /**
     * Adds query parameter
     */
    public RequestBuilder addQueryParam(String key, String value) {
        requestSpecBuilder.addQueryParam(key, value);
        return this;
    }
    
    /**
     * Adds header
     */
    public RequestBuilder addHeader(String key, String value) {
        requestSpecBuilder.addHeader(key, value);
        return this;
    }
    
    /**
     * Sets request body
     */
    public RequestBuilder setBody(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }
    
    /**
     * Builds the RequestSpecification
     */
    public RequestSpecification build() {
        return requestSpecBuilder.build();
    }
}
