import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RuleLoader {

    private Properties rules;

    public RuleLoader() {
        rules = new Properties();
        try {
            try (FileInputStream fis = new FileInputStream("src/rules.properties")) {
                rules.load(fis);
            }
        } catch (IOException e) {
            System.err.println("Error: Properties not Found");
        }
    }

    public String getFolderFromExtension (String extension){
        return rules.getProperty(extension);
    }
}
