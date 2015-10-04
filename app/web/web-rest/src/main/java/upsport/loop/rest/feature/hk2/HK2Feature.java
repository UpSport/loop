package upsport.loop.rest.feature.hk2;

import static org.glassfish.hk2.extras.ExtrasUtilities.enableTopicDistribution;
import static org.glassfish.hk2.utilities.ServiceLocatorUtilities.enableImmediateScope;
import static org.glassfish.hk2.utilities.ServiceLocatorUtilities.enableInheritableThreadScope;
import static org.glassfish.hk2.utilities.ServiceLocatorUtilities.enableLookupExceptions;
import static org.glassfish.hk2.utilities.ServiceLocatorUtilities.enablePerThreadScope;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ServiceLocator;

public class HK2Feature implements Feature {

    private final ServiceLocator locator;
    private final DynamicConfigurationService dcs;

    @Inject
    HK2Feature(ServiceLocator locator, DynamicConfigurationService dcs) {
        this.locator = locator;
        this.dcs = dcs;
    }

    @Override
    public boolean configure(FeatureContext context) {
        try {
            dcs.getPopulator().populate(new HK2DescriptorFileFinder());

            enableImmediateScope(locator);
            enableInheritableThreadScope(locator);
            enableLookupExceptions(locator);
            enablePerThreadScope(locator);
            enableTopicDistribution(locator);

            return true;
        } catch (IOException | MultiException e) {
            return false;
        }
    }

}
