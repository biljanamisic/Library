package com.dropwizard.demo.exception;

import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;

public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(final WebApplicationException e) {

        int status = e.getResponse() == null ? 500 : e.getResponse().getStatus();
        final String msg = e.getMessage() == null ?
                HttpStatus.getMessage(status) : e.getMessage();
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new HashMap() { {
                    put("error", msg);
                } }).build();
    }

}
