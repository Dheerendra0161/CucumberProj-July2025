package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    public static Properties loadProperties(String env) {
        Properties prop = new Properties();
        String fileName = "config-" + env + ".properties";
        String path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", fileName).toString();

        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
            System.out.println("✅ Loaded configuration: " + path);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config file: " + path + "\n" + e.getMessage());
        }

        return prop;
    }
}
