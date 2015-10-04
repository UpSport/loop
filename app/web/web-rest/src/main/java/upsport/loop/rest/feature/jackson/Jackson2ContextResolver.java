package upsport.loop.rest.feature.jackson;

import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2ContextResolver implements ContextResolver<ObjectMapper> {

    private final ObjectMapper objectMapper;

    Jackson2ContextResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }

}
