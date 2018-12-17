package com.dropwizard.demo;

import com.dropwizard.demo.dao.DbAdapter;
import com.dropwizard.demo.resources.HelloWorldResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class SampleService extends Service<SampleConfiguration> {
    public static void main(String[] args) throws Exception {
       new SampleService().run(args);
        DbAdapter dbAdapter = new DbAdapter();
        dbAdapter.connect();
        dbAdapter.disconnect();

    }
    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {

    }

    public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
        environment.addResource(new HelloWorldResource());
    }
}
