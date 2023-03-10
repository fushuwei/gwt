/**
 * Copyright (C) 2009-2012 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mochousoft.gwt.shared;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 *
 * @author fushuwei
 */
public class OrderConfirmation {

    public long order_id;

    public PizzaOrder order;

    public double price;
    public Long ready_time;

    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    public interface OrderConfirmationJED extends JsonEncoderDecoder<OrderConfirmation> {

    }

    @Override
    public String toString() {
        if (GWT.isClient()) {
            // Shows how to access the code generated json encoder/decoder.
            // Only works in client code, won't work on the server side.
            OrderConfirmationJED jed = GWT.create(OrderConfirmationJED.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }
}
