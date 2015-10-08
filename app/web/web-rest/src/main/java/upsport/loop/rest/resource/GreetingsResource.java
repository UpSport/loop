package upsport.loop.rest.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.glassfish.jersey.server.ManagedAsync;

import upsport.loop.model.User;

@Path("greetings")
public class GreetingsResource {

    @Inject
    GreetingsResource() {
    }

    @Path("{greetingType}")
    @POST
    @Consumes({ APPLICATION_FORM_URLENCODED, APPLICATION_JSON })
    @Produces(APPLICATION_JSON)
    @ManagedAsync
    public void post(@Suspended final AsyncResponse asyncResponse,
            @PathParam("greetingType") final String greetingType,
            @Valid final User user) {
        // String email = user.getEmail();
        // String personal = format("%s %s", user.getFirstName(),
        // user.getLastName());

        System.out.println("test");

        // Map<String, Object> context = ImmutableMap.<String, Object> builder()
        // .put("email", email)
        // .put("firstName", user.getFirstName())
        // .put("lastName", user.getLastName())
        // .build();
        //
        // EmailMessage message = new EmailMessage(email, personal,
        // greetingType, context);
        // emailService.send(message);
        asyncResponse.resume(ok());
    }
}
