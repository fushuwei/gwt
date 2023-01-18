package com.mochousoft.gwt.basic.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>MessageService</code>.
 *
 * @author fushuwei
 */
public interface MessageServiceAsync {
    void sendMessage(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
