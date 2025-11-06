import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        Path downloadPath = Paths.get(userHome, "Downloads");

        RuleLoader rule = new RuleLoader();

        if(Files.notExists(downloadPath) || !Files.isDirectory(downloadPath)){
            System.out.println("Path not Found!!");
            return;
        }

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(downloadPath)){
            for(Path entry: stream){
                System.out.println(entry.getFileName() + " --- " + rule.getFolderFromExtension(getFileExtension(entry)));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getFileExtension(Path path){

        if(Files.isDirectory(path)){
            return "";
        }

        int lastDot = path.getFileName().toString().lastIndexOf('.');
        String fileExtension = "";
        if (lastDot != -1 && lastDot != 0) {
            fileExtension = path.getFileName().toString().substring(lastDot);
        }

        return fileExtension;
    }
}