package rpraut.osgi.api.rest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import rpraut.osgi.api.TestService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component(//
        name = "rpraut.osgi.api.rest.HelloService", //
        service = HelloService.class, //
        scope = ServiceScope.PROTOTYPE,//
        property = {//
            "service.exported.interfaces=*",//
            "service.exported.configs=org.apache.cxf.rs",//
            "org.apache.cxf.rs.httpservice.context=/rest",//
            "service.exported.intents=jackson", //  
            "cxf.bus.prop.skip.default.json.provider.registration=true"//
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
