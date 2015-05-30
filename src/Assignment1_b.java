import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by androiddevelopment on 5/31/15.
 */
public class Assignment1_b {

    static int i=0;
    public static void main(String arg[]) throws IOException
    {
        System.out.println("The Files in this Directory are : ");

        Files.walk(Paths.get("Books")).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {

                System.out.println("File :"+(++i)+filePath.getFileName());


            }
        });
    }
}
