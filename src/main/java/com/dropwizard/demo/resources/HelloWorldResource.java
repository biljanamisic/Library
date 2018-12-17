package com.dropwizard.demo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("greetings/{name}")
public class HelloWorldResource {

    @GET
    public String getGreetings(@PathParam("name") String name){
        return "Hello "+ name + ":)";
    }
}
