/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpraut.osgi.api.rest;

import rpraut.osgi.api.entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

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
