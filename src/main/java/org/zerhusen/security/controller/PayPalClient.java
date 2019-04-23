package org.zerhusen.security.controller;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class PayPalClient {
    /**
     *Set up the PayPal Java SDK environment with PayPal access credentials.
     *This sample uses SandboxEnvironment. In production, use ProductionEnvironment.
     */
    private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
        "carlhe_0508-facilitator@163.com",
        "12345678");
    
    /**
     *PayPal HTTP client instance with environment that has access
     *credentials context. Use to invoke PayPal APIs.
     */
    PayPalHttpClient client = new PayPalHttpClient(environment);
    
    /**
     *Method to get client object
     *
     *@return PayPalHttpClient client
     */
    public PayPalHttpClient client() {
        return this.client;
    }
}

