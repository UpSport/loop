package upsport.loop.rest.feature.jackson;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Jackson2Feature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        Configuration configuration = context.getConfiguration();
        boolean isJacksonRegistered = configuration.isRegistered(Jackson2ContextResolver.class);

        if (!isJacksonRegistered) {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(MapperFeature.USE_ANNOTATIONS, true)
                    .configure(MapperFeature.AUTO_DETECT_GETTERS, true)
                    .configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true)
                    .configure(MapperFeature.AUTO_DETECT_SETTERS, true)
                    .configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, false)
                    .configure(MapperFeature.AUTO_DETECT_FIELDS, true)
                    .configure(SerializationFeature.INDENT_OUTPUT, false)
                    .configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true)
                    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                    .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);

            Jackson2ContextResolver resolver = new Jackson2ContextResolver(mapper);
            context.register(resolver);
            isJacksonRegistered = true;
        }

        return isJacksonRegistered;
    }

}
