package upsport.loop.rest.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.glassfish.jersey.server.ManagedAsync;

import upsport.loop.business.UserBusiness;
import upsport.loop.model.User;

@Path("users")
public class UsersResource {

    private final UserBusiness userBusiness;

    @Inject
    UsersResource(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @Path("create")
    @POST
    @Consumes({ APPLICATION_FORM_URLENCODED, APPLICATION_JSON })
    @Produces(APPLICATION_JSON)
    @ManagedAsync
    public void post(@Suspended final AsyncResponse asyncResponse,
            @Valid final User user) {
        userBusiness.save(user);
        asyncResponse.resume(ok());
    }

    @GET
    @Produces(APPLICATION_JSON)
    @ManagedAsync
    public void get(@Suspended final AsyncResponse asyncResponse) {
        List<User> users = userBusiness.getAll();
        asyncResponse.resume(ok(users).build());
    }
}
