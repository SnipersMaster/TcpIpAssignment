import sun.awt.shell.ShellFolder;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by androiddevelopment on 5/22/15.
 */
public class BooksServer  {

    static ServerSocket bookserverSocket;
    static Socket bookSocket;
    public static void main(String[] arg) throws IOException
    {

        System.out.println("I am in the background");
        bookserverSocket=new ServerSocket(7169);
        while (true) {
            bookSocket=bookserverSocket.accept();
            BufferedInputStream bookiBufferedInputStream=new BufferedInputStream(bookSocket.getInputStream());
            BufferedOutputStream bookBufferedOutputStream=new BufferedOutputStream(bookSocket.getOutputStream());


            System.out.println("I am in the background 2");
            // first step to get all the files in the books path
            Files.walk(Paths.get("Books")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {

                    try {
                        SendToClient(filePath.getFileName().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
            bookBufferedOutputStream.close();
        }
    }

    public static void SendToClient(String text) throws IOException {

        Socket socket = new Socket("localhost", 7170);
        OutputStream outputStream = socket.getOutputStream();
        // bufferwriter it sends to the server ->OutputStreamWriter -> OutputStream -> socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        // to send
        bufferedWriter.write(text);
        // to refresh
        bufferedWriter.flush();
        // close
        socket.close();
    }
}
