package com.mochousoft.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import com.mochousoft.gwt.shared.MethodService;
import com.mochousoft.gwt.shared.OrderConfirmation;
import com.mochousoft.gwt.shared.Pizza;
import com.mochousoft.gwt.shared.PizzaOrder;
import com.mochousoft.gwt.shared.PizzaService;
import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestServiceProxy;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Button button = new Button("Place Pizza Order");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                placeOrder();
            }
        });
        RootPanel.get().add(button);

        button = new Button("Do Put");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doPut();
            }
        });
        RootPanel.get().add(button);

        button = new Button("Baidu Jsonp Api");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doJsonp();
            }
        });

        RootPanel.get().add(button);
    }

    protected void doPut() {
        MethodService service = GWT.create(MethodService.class);
        Resource resource = new Resource("/test/method");
        ((RestServiceProxy) service).setResource(resource);
        service.put(new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Error x: " + exception);
            }

            @Override
            public void onSuccess(Method method, String response) {
                RootPanel.get().add(new Label("done."));
            }
        });
    }

    private void placeOrder() {
        PizzaService service = GWT.create(PizzaService.class);
        Resource resource = new Resource("pizza-service");
        ((RestServiceProxy) service).setResource(resource);

        PizzaOrder order = new PizzaOrder();
        order.delivery = true;
        order.delivery_address.add("3434 Pinerun Ave.");
        order.delivery_address.add("Tampa, FL  33734");

        Pizza pizza = new Pizza();
        pizza.crust = "thin";
        pizza.quantity = 1;
        pizza.size = 16;
        pizza.toppings.add("ham");
        pizza.toppings.add("pineapple");
        order.pizzas.add(pizza);

        pizza = new Pizza();
        pizza.crust = "thin";
        pizza.quantity = 1;
        pizza.size = 16;
        pizza.toppings.add("extra cheese");
        order.pizzas.add(pizza);

        service.order(order, new MethodCallback<OrderConfirmation>() {
            @Override
            public void onSuccess(Method method, OrderConfirmation receipt) {
                RootPanel.get().add(new Label("got receipt: " + receipt));
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Error: " + exception);
            }
        });
    }

    protected void doJsonp() {
        Resource resource = new Resource("http://127.0.0.1:8080/jsonp-service?callback=list");
        resource.jsonp().send(new JsonCallback() {
            @Override
            public void onSuccess(Method method, JSONValue response) {
                JSONObject obj = (JSONObject) ((JSONObject) response).get("ResultSet");
                RootPanel.get().add(new Label("Search Results Available: " + obj.get("totalResultsAvailable")));
            }

            @Override
            public void onFailure(Method method, Throwable exception) {
                Window.alert("Error x: " + exception);
            }
        });
    }
}
