import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String userHome = System.getProperty("user.home");
        Path willBeSortFolder = Paths.get(userHome, "Downloads");
        Path targetFolderAfterSort = Paths.get(userHome, "Downloads");

        RuleLoader rule = new RuleLoader();

        if(Files.notExists(willBeSortFolder) || !Files.isDirectory(willBeSortFolder)){
            System.out.println("Path not Found!!");
            return;
        }

        /*
          Read all file in the will_be_sort_folder
          then put the file in their right folder in the target folder
         */
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(willBeSortFolder)){
            for(Path entry: stream){
                if(Files.isDirectory(entry)) continue;
                String targetFolder = rule.getFolderFromExtension(getFileExtension(entry));
                if(targetFolder == null) continue;
                Path targetPath = targetFolderAfterSort.resolve(targetFolder);
                moveFile(entry, targetPath);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Extracts the file extension from a Path
     * @param path path of the file
     * @return the extension, .mp4 or something else
     */
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

    /**
     * move the file from source to target
     * change the name if it already exists
     * @param source
     * @param target
     * @throws IOException
     */
    public static void moveFile(Path source, Path target) throws IOException{
        Path originalFileName = source.getFileName();
        String fileName = originalFileName.toString();


        int lastDot = fileName.lastIndexOf('.');
        String baseName = "";
        if(lastDot >= 1) {
            baseName = fileName.substring(0, lastDot);
        } else {
            baseName = fileName;
        }

        String extension = getFileExtension(source);

        Path targetPath = target.resolve(originalFileName);
        int counter = 0;

        while (Files.exists(targetPath)) {
            counter++;
            String newFileName = baseName + " (" + counter + ")" + extension;
            targetPath = target.resolve(newFileName);
        }
        Files.createDirectories(targetPath.getParent());
        Files.move(source, targetPath);
    }
}