package dev.doda.team.lib;

import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class to retrieve the version of the library.
 */
public class VersionUtil {

    private static final String PROPERTIES_FILE = "version.properties";
    private static final String VERSION_KEY = "version";
    private static final String UNKNOWN_VERSION = "UNKNOWN";

    private String version;

    /**
     * Retrieves the version of the library from the packaged properties file.
     *
     * @return The version string or "UNKNOWN" if not found.
     */
    public String getVersion() {
        if (version != null) {
            return version;
        }

        try (InputStream stream = VersionUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (stream == null) {
                System.err.println("VersionUtil: Could not find " + PROPERTIES_FILE);
                version = UNKNOWN_VERSION;
                return version;
            }

            Properties props = new Properties();
            props.load(stream);

            version = props.getProperty(VERSION_KEY, UNKNOWN_VERSION);
            return version;

        } catch (Exception e) {
            System.err.println("VersionUtil: Error reading properties file: " + e.getMessage());
            version = UNKNOWN_VERSION;
            return version;
        }
    }

}
