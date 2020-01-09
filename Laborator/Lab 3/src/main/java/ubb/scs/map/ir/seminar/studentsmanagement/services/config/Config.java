package ubb.scs.map.ir.seminar.studentsmanagement.services.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    public static String CONFIG_LOCATION;

    public static Properties getProperties() {
        if (CONFIG_LOCATION == null) {
            try {
                CONFIG_LOCATION = Paths.get(Config.class.getClassLoader()
                        .getResource("config.properties").toURI()).toAbsolutePath().toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIG_LOCATION));
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find config properties at " + CONFIG_LOCATION);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config properties from " + CONFIG_LOCATION);
        }
    }
}
