
import java.io.*;

public class SListflie {
    public static void main(String[] args) throws IOException {

        File folder = new File("C:\\Users\\Sniper");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File : " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory : " + listOfFiles[i].getName());
            }
        }
    }}
