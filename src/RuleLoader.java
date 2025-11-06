import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RuleLoader {

    private Properties rules;

    public RuleLoader() {
        rules = new Properties();
        try {
            InputStream input = RuleLoader.class.getClassLoader().getResourceAsStream("rules.properties");

            if (input == null) {
                System.err.println("Error: Cannot find 'rules.properties' in Classpath. Check if file is in SRC folder.");
                return;
            }
            try (InputStream safeInput = input) {
                rules.load(safeInput);
            }

        } catch (IOException e) {
            System.err.println("Error reading rules.properties file.");
            e.printStackTrace();
        }
    }

    public String getFolderFromExtension (String extension){
        return rules.getProperty(extension);
    }
}
