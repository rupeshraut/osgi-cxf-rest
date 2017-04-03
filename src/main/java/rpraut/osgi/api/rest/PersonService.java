/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import java.util.Collection;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import rpraut.osgi.api.entity.Person;

/**
 *
 * @author rupesh
 */
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PersonService {

    @GET
    @Path("/{id}")
    Person sayHello(@PathParam("id") Integer id);

    @GET
    @Path("/list")
    Collection<Person> list();
    
    @PUT
    @Path("/add")
    Person save(Person person);

    @POST
    @Path("/update")
    Person update(Person person);

    @DELETE
    @Path("/{id}")
    boolean delete(@PathParam("id") Integer id);
}
