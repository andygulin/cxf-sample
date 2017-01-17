package cxf.sample.webservice;

import cxf.sample.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public interface UserService {

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response all();

    @GET
    @Path("/get/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response get(@PathParam("id") Integer id);

    @POST
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    Response post(User user);

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    Response hello();
}
