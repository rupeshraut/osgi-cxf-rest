/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 *
 * @author rupesh
 */
@Api(tags = {"hello"})
@Path("")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface HelloService {

    @ApiOperation(value = "Say Hello", notes = "Returns a single task", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "'String sayHello(name)' not found")
    })
    @GET
    @Path("/{name}")
    String sayHello(@PathParam("name") String name);

    @ApiOperation(value = "Get current date", notes = "Returns a current date", response = Map.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "'Map<String, Object> getDate()' not found")
    })
    @GET
    @Path("/today")        
    Map<String, Object> getDate();
}
//http://localhost:8181/cxf/tasks/api-docs?url=../swagger.json