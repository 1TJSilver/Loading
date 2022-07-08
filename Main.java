import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("Games//savegames//zip.zip", "Games//savegames//");
        openProgress("Games//savegames//Game1");
    }
    public static String openZip(String pathOfZip, String pathOfIssue){
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(pathOfZip))){
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null){
                name = entry.getName();
                try (FileOutputStream fos = new FileOutputStream(name)){
                    for (int c = zis.read(); c != -1; c = zis.read()) {
                        fos.write(c);
                    }
                    fos.flush();
                } catch (IOException ex){
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return pathOfIssue;
    }
    public static void openProgress (String path){
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)){
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress.toString());
    }
}
