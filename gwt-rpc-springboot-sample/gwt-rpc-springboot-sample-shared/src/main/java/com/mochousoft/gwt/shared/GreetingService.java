package com.mochousoft.gwt.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 *
 * @author fushuwei
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

    GreetingResponse greetServer(String name) throws IllegalArgumentException;
}
