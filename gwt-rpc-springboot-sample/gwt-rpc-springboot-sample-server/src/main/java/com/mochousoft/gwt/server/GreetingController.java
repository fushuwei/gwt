package com.mochousoft.gwt.server;

import com.mochousoft.gwt.shared.FieldVerifier;
import com.mochousoft.gwt.shared.GreetingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo
 *
 * @author fushuwei
 */
@RestController
@RequestMapping("/app")
public class GreetingController {

    @GetMapping("restgreet")
    public GreetingResponse greetServer(String input) throws IllegalArgumentException {
        // Verify that the input is valid.
        if (!FieldVerifier.isValidName(input)) {
            // If the input is not valid, throw an IllegalArgumentException back to
            // the client.
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }

        GreetingResponse response = new GreetingResponse();

        // response.setServerInfo(getServletContext().getServerInfo());
        // response.setUserAgent(getThreadLocalRequest().getHeader("User-Agent"));

        response.setGreeting("Hello, " + input + "!");

        return response;
    }
}
