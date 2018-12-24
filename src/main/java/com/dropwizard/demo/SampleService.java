package com.dropwizard.demo;

import com.dropwizard.demo.client.RestClient;
import com.dropwizard.demo.dao.LibraryDao;
import com.dropwizard.demo.exception.MyJerseyViolationExceptionMapper;
import com.dropwizard.demo.exception.WebExceptionMapper;
import com.dropwizard.demo.model.Authors;
import com.dropwizard.demo.model.Books;
import com.dropwizard.demo.resources.LibraryResourceImpl;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class SampleService extends Application<SampleConfiguration> {
    public static void main(String[] args) throws Exception {
       new SampleService().run(args);
    }

    public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    private final HibernateBundle<SampleConfiguration> hibernateBundle =
            new HibernateBundle<SampleConfiguration>(Books.class, Authors.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(SampleConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public void run(SampleConfiguration sampleConfiguration, Environment environment) throws Exception {
        final LibraryDao dao = new LibraryDao(hibernateBundle.getSessionFactory());
        environment.jersey().register(new MyJerseyViolationExceptionMapper());
        environment.jersey().register(new WebExceptionMapper());
        environment.jersey().register(new LibraryResourceImpl(dao));
        JerseyClientConfiguration conf = sampleConfiguration.getJerseyClientConfiguration();
        conf.setChunkedEncodingEnabled(false);

        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000);
        config.property(ClientProperties.READ_TIMEOUT, 5000);
        Client client = ClientBuilder.newClient(config);

        environment.jersey().register(new RestClient(client));
    }
}
