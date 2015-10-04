package upsport.loop.rest;

import static org.glassfish.jersey.server.ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK;
import static org.glassfish.jersey.server.ServerProperties.BV_SEND_ERROR_IN_RESPONSE;
import static org.glassfish.jersey.server.ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR;

import org.glassfish.jersey.server.ResourceConfig;

import upsport.loop.rest.feature.exception.ExceptionMappingFeature;
import upsport.loop.rest.feature.hk2.HK2Feature;
import upsport.loop.rest.feature.jackson.Jackson2Feature;

public class ApiApplication extends ResourceConfig {

    public ApiApplication() {
        this.setApplicationName("Upsport")
                .packages("upsport.loop.rest.resource")
                .register(HK2Feature.class)
                .register(Jackson2Feature.class)
                .register(ExceptionMappingFeature.class)
                .property(BV_SEND_ERROR_IN_RESPONSE, true)
                .property(BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true)
                .property(RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }

}
