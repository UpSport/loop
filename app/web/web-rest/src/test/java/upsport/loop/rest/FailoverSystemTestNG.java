package upsport.loop.rest;

import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.test.JerseyTestNg;
import org.testng.annotations.Test;

import upsport.loop.model.User;
import upsport.loop.rest.fixture.InvalidConfigProvider;

public class FailoverSystemTestNG extends JerseyTestNg.ContainerPerClassTest {

    @Override
    protected Application configure() {
        ApiApplication app = new ApiApplication();
        app.registerInstances(new AbstractBinder() {

            @Override
            protected void configure() {
                addActiveDescriptor(InvalidConfigProvider.class);
            }
        });

        return app;
    }

    @Test(priority = 1)
    public void givenValidGreetingBirthdayGreetingsResourceShouldReturnServiceUnavailable() {
        User greeting = new User("firstname", "lastname", "upsport@gmail.com");
        Response response = target("greetings")
                .path("birthday")
                .request()
                .post(entity(greeting, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(SERVICE_UNAVAILABLE.getStatusCode());
    }

    @Test(priority = 1)
    public void givenValidGreetingHolidaysGreetingsResourceShouldReturnOK() {
        User greeting = new User("firstname", "lastname", "upsport@gmail.com");
        Response response = target("greetings")
                .path("holidays")
                .request()
                .post(entity(greeting, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatus()).isEqualTo(OK.getStatusCode());
    }
}
