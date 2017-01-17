package cxf.sample.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/message")
public interface MessageService {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    Response hello();
}
