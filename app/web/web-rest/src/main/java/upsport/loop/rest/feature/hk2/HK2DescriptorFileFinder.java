package upsport.loop.rest.feature.hk2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.glassfish.hk2.api.DescriptorFileFinder;

public class HK2DescriptorFileFinder implements DescriptorFileFinder {

    public static final String JAR_DEFAULT = RESOURCE_BASE + "default";
    public static final String WEB_DEFAULT = "hk2-locator/default";
    public static final String WEB_APP = "hk2-locator/application";

    private final ClassLoader classLoader;

    public HK2DescriptorFileFinder() {
        this(HK2DescriptorFileFinder.class.getClassLoader());
    }

    public HK2DescriptorFileFinder(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public List<InputStream> findDescriptorFiles() throws IOException {
        List<InputStream> files = new LinkedList<>();

        Enumeration<URL> jarResources = classLoader.getResources(JAR_DEFAULT);
        Enumeration<URL> webDefaultResources = classLoader.getResources(WEB_DEFAULT);
        Enumeration<URL> webApplicationResources = classLoader.getResources(WEB_APP);

        while (jarResources.hasMoreElements()) {
            URL url = jarResources.nextElement();

            files.add(url.openStream());
        }

        while (webDefaultResources.hasMoreElements()) {
            URL url = webDefaultResources.nextElement();
            files.add(url.openStream());
        }

        while (webApplicationResources.hasMoreElements()) {
            URL url = webApplicationResources.nextElement();
            files.add(url.openStream());
        }

        return files;
    }

}
