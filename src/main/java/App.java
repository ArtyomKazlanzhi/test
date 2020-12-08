import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.classpath.ClasspathConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;

import java.nio.file.Paths;
import java.util.Collections;

public class App {

  public static void main(String[] args) {
    DbConfig dbConfig = loadConfigProperties();
    System.out.println(dbConfig.pwd());
  }

  public static DbConfig loadConfigProperties() {
    // Specify which files to load. Configuration from both files will be merged.
    ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get("application.properties"));

    // Use classpath repository as configuration store
    ConfigurationSource source = new ClasspathConfigurationSource(configFilesProvider);

    // Create provider
    ConfigurationProvider configProvider = new ConfigurationProviderBuilder()
        .withConfigurationSource(source)
        .build();
    return configProvider.bind("db", DbConfig.class);
  }
}
