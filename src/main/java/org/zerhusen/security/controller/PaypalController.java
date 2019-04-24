package org.zerhusen.security.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;

import com.braintreepayments.http.HttpResponse;
import com.braintreepayments.http.serializer.Json;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersGetRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 1. Import the PayPal SDK client that was created in `Set up the Server SDK`.
 * This step extends the SDK client.  It's not mandatory to extend the client, you can also inject it.
 */
@RestController
public class PaypalController extends PayPalClient {
    
    //2. Set up your server to receive a call from the client
    
    /**
     * Method to perform sample GET on an order
     *
     * @throws IOException Exceptions from the API, if any
     */
    @RequestMapping(value = "paypal-transaction-complete", method = RequestMethod.POST)
    public void getOrder(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[1024];
        int len;
        while ((len = reader.read(buff)) != -1) {
            sb.append(buff, 0, len);
        }
        Map map= (Map) JSON.parse(sb.toString());
        OrdersGetRequest orderRequest = new OrdersGetRequest(map.get("orderID").toString());
        //3. Call PayPal to get the transaction
        HttpResponse<Order> response = client().execute(orderRequest);
        //4. Save the transaction in your database. Implement logic to save transaction to your database for future reference.
        System.out.println("Full response body:");
        System.out.println(new JSONObject(new Json().serialize(response.result())).toString(4));
    }
    /**
     * This is the driver method that invokes the getOrder function with
     * order ID to retrieve order details.
     * <p>
     * To get the correct order ID, this sample uses createOrder to create
     * a new order and then uses the newly-created order ID as a
     * parameter to getOrder.
     *
     * @param args
     * @throws IOException
     */
//    public static void main(String[] args) throws IOException {
//        new GetOrder().getOrder("REPLACE-WITH-VALID-ORDER-ID");
//    }
}
