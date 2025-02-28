package Helpers;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getToken() {
        return properties.getProperty("token");
    }
    public static String getUsername() {
        return properties.getProperty("username");
    }
    public static String getPassword() {
        return properties.getProperty("password");
    }
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}
