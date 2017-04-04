/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 *
 * @author rupesh
 */
@Component(name = "TestComponent", immediate = true)
public class TestComponent {

    private TestService testService;

    /**
     *
     * @return
     */
    public TestService getTestService() {
        return testService;
    }

    /**
     *
     * @param testService
     */
    @Reference(unbind = "unbindTestService")
    public void bindTestService(TestService testService) {
        this.testService = testService;
    }

    /**
     *
     * @param testService
     */
    public void unbindTestService(TestService testService) {
        this.testService = null;
    }

    /**
     *
     * @param config
     */
    @Activate
    public void activate(Map<String, Object> config) {
        modified(config);
    }

    /**
     *
     * @param config
     */
    @Modified
    public void modified(Map<String, Object> config) {
    }

    /**
     *
     * @param config
     */
    @Deactivate
    public void deactivate(Map<String, Object> config) {
    }

}
