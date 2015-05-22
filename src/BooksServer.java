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
    static int sendlock;
    public static void main(String[] arg) throws IOException
    {

        System.out.println("I am in the background");
        bookserverSocket=new ServerSocket(7111);
        while (true) {
            bookSocket=bookserverSocket.accept();
            System.out.println("Accepted");
            // buffer it reads from server  , requires inputstreamreader  -> inputstream -> socket.getInputStream()  ,
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bookSocket.getInputStream()));
            String read="";
            // read reading values from bufferreader
            read = bufferedReader.readLine();
            System.out.println(read);
                if(read.contains("getbooks"))
                {
                    Files.walk(Paths.get("Books")).forEach(filePath -> {
                        if (Files.isRegularFile(filePath)) {

                            try {
                                SendToClient(filePath.getFileName().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }else if(!read.contains("getbooks"))
                {
                    System.out.println("the book is : "+read);
                }


            }



            // first step to get all the files in the books path

    }

    public static void SendToClient(String text) throws IOException {

        Socket socket = new Socket("192.168.1.105", 7222);
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
