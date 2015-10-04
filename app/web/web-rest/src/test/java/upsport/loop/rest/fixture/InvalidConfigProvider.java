package upsport.loop.rest.fixture;

import static java.util.Collections.unmodifiableMap;

import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.Rank;
import org.yaml.snakeyaml.Yaml;

public class InvalidConfigProvider implements Factory<Map<String, Object>> {

    private final Yaml yaml;

    @Inject
    InvalidConfigProvider(Yaml yaml) {
        this.yaml = yaml;
    }

    @Rank(Integer.MAX_VALUE)
    @Singleton
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> provide() {
        // load config.yml from the classpath
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("invalid-config.yml");

        return unmodifiableMap(yaml.loadAs(inputStream, Map.class));
    }

    @Override
    public void dispose(Map<String, Object> map) {
        map = null;
    }

}
