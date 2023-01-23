package com.mochousoft.gwt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 *
 * @author fushuwei
 */
public interface GreetingServiceAsync {

    void greetServer(String input, AsyncCallback<GreetingResponse> callback) throws IllegalArgumentException;
}