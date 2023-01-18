package com.mochousoft.gwt.basic.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mochousoft.gwt.basic.shared.MessageService;

import java.time.LocalDateTime;

/**
 * The server-side implementation of the RPC service.
 *
 * @author fushuwei
 */
public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

    public String sendMessage(String message) throws IllegalArgumentException {
        if (message == null) {
            throw new IllegalArgumentException("message is null");
        }

        return "Hello, " + message + "!<br><br> Time received: " + LocalDateTime.now();
    }
}
