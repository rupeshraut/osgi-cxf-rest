/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api;

import java.util.ArrayList;
import java.util.List;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.scr.AbstractCamelRunner;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import rpraut.osgi.api.route.TestRoute;

/**
 *
 * @author rupesh
 */
//@Component(immediate = true, scope = ServiceScope.SINGLETON, configurationPolicy = ConfigurationPolicy.OPTIONAL ,property = {"camelContextId=AppContext", "active=true"})
public class CamelScr extends AbstractCamelRunner {

    private TestRoute testRoute;

    public TestRoute getTestRoute() {
        return testRoute;
    }

    @Reference(service =TestRoute.class, unbind = "unbindTestRoute")
    public void bindTestRoute(TestRoute testRoute) {
        this.testRoute = testRoute;
    }

    @Reference
    public void unbindTestRoute(TestRoute testRoute) {
        this.testRoute = null;
    }

    
    
    
    /**
     *
     * @return @throws Exception
     */
    @Override
    protected List<RoutesBuilder> getRouteBuilders() {
        List<RoutesBuilder> builders = new ArrayList<RoutesBuilder>();
        builders.add(testRoute);
        return builders;
    }

}
