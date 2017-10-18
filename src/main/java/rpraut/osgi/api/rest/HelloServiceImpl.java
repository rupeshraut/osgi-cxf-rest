/*
 * 
 */
package rpraut.osgi.api.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import rpraut.osgi.api.TestService;

@Component(//
        name = "rpraut.osgi.api.rest.HelloService", //
        service = HelloService.class, //
        scope = ServiceScope.PROTOTYPE,//
        property = {//
            "service.exported.interfaces=rpraut.osgi.api.rest.HelloService",//
            "service.exported.configs=org.apache.cxf.rs",//
            "org.apache.cxf.rs.address=/hello", //
            "service.exported.intents=jackson", //  
            "cxf.bus.prop.skip.default.json.provider.registration=false",//
            "org.apache.cxf.ws.features=org.apache.cxf.jaxrs.swagger.Swagger2Feature(basePath=/api-docs)"
        })
public class HelloServiceImpl implements HelloService {

    private final static Logger LOGGER = Logger.getLogger(HelloServiceImpl.class.getName());

    TestService testService;

    public TestService getTestService() {
        return testService;
    }

    @Reference(unbind = "unsetTestService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public void unsetTestService(TestService testService) {
        this.testService = null;
    }

    public String sayHello(String name) {
        return "hello " + name;
    }

    public Map<String, Object> getDate() {
        Map<String, Object> map = new HashMap<String, Object>(1);
        map.put("today", new Date());
        return map;
    }

}
