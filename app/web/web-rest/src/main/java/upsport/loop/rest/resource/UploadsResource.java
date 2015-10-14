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

import upsport.loop.business.UploadBusiness;
import upsport.loop.model.Upload;

@Path("uploads")
public class UploadsResource {

    private final UploadBusiness uploadBusiness;

    @Inject
    UploadsResource(UploadBusiness UploadBusiness) {
        this.uploadBusiness = UploadBusiness;
    }

    @Path("create")
    @POST
    @Consumes({ APPLICATION_FORM_URLENCODED, APPLICATION_JSON })
    @Produces(APPLICATION_JSON)
    @ManagedAsync
    public void post(@Suspended final AsyncResponse asyncResponse,
            @Valid final Upload upload) {
        uploadBusiness.save(upload);
        asyncResponse.resume(ok());
    }

    @GET
    @Produces(APPLICATION_JSON)
    @ManagedAsync
    public void get(@Suspended final AsyncResponse asyncResponse) {
        List<Upload> uploads = uploadBusiness.getAll();
        asyncResponse.resume(ok(uploads).build());
    }
}
