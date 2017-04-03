/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.route.impl;

import org.apache.camel.builder.RouteBuilder;
import rpraut.osgi.api.route.TestRoute;

/**
 *
 * @author rupesh
 */
public class TestRouteImpl extends RouteBuilder implements TestRoute {

    @Override
    public void configure() throws Exception {
        from("direct:test").log("test");
    }

}
