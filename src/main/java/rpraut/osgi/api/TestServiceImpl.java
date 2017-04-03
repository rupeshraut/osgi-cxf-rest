/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api;

import org.osgi.service.component.annotations.Component;

/**
 *
 * @author rupesh
 */
@Component(name = "TestService", immediate = true, enabled = true, service = TestService.class)
public class TestServiceImpl implements TestService {

    /**
     *
     * @param message
     */
    public void doTest(String message) {
        System.out.println("Hello " + message);
    }

}
