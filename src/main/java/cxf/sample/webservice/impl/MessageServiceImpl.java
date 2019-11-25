package cxf.sample.webservice.impl;

import cxf.sample.webservice.MessageService;

import javax.ws.rs.core.Response;

public class MessageServiceImpl implements MessageService {

    @Override
    public Response hello() {
        return Response.ok("hello").build();
    }
}