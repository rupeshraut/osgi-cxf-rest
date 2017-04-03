package rpraut.osgi.api.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import rpraut.osgi.api.rest.HelloService;

/**
 *
 * @author rupesh
 */
@Component(//
        service = HelloRestCommand.class, //
        property = {//
            "osgi.command.scope=hello", //
            "osgi.command.function=hello", //
            "osgi.command.function=today"//
        })
public class HelloRestCommand {

    private HelloService helloService;

    public HelloService getHelloService() {
        return helloService;
    }

    @Reference(service = HelloService.class, unbind = "unsetHelloService")
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public void unsetHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public void hello(String name) {
        System.out.println(getHelloService().sayHello(name));
    }

    public void today() {
        System.out.println(getHelloService().getDate());
    }

}
