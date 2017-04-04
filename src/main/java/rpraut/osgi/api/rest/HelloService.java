/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 *
 * @author rupesh
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface HelloService {

    @GET
    @Path("/{name}")
    String sayHello(@PathParam("name") String name);
    
    @GET
    @Path("/today")        
    Map<String, Object> getDate();
}
