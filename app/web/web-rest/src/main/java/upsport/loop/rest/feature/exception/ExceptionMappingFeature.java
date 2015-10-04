package upsport.loop.rest.feature.exception;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class ExceptionMappingFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        Configuration configuration = context.getConfiguration();

//        if (!configuration.isRegistered(ServiceKernalExceptionMapper.class)) {
//            context.register(ServiceKernalExceptionMapper.class);
//        }
//
//        if (!configuration.isRegistered(EmailTemplateExceptionMapper.class)) {
//            context.register(EmailTemplateExceptionMapper.class);
//        }

        return true;
    }

}
