import sun.awt.shell.ShellFolder;

import javax.swing.*;
import javax.swing.tree.ExpandVetoException;
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
///
    static ServerSocket bookserverSocket;
    static ServerSocket ftpserver;
    static Socket bookSocket;
    static int sendlock;
    static int flowcontrol=0;
    public static void main(String[] arg) throws Exception {

        JDialog jDialog=new JDialog();
        jDialog.setTitle("hello");
        jDialog.show();

        bookserverSocket = new ServerSocket(7221);
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

                                SocketThread socketThread=new SocketThread(new Socket("192.168.1.109", 7333),filePath.getFileName().toString());
                                socketThread.run();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }
                    });

                }else if(!read.contains("getbooks"))
                {
                    SocketThread socketThread=new SocketThread(new Socket("192.168.1.109", 7333),"start");
                    socketThread.run();
                    //SendFiletoClient(read);
                    sendfile(read);
                    System.out.println("the book is : "+read);



            }}

    // first step to get all the files in the books path

    }



    public static void sendfile(String read) throws Exception
    {
       // bookserverSocket.close();
        ServerSocket welcomeSocket = new ServerSocket(7225);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedOutputStream outToClient = new BufferedOutputStream(connectionSocket.getOutputStream());
            if (outToClient != null) {
                File myFile = new File("/Users/androiddevelopment/IdeaProjects/TcpIpAssignment/Books/"+read);
                byte[] mybytearray = new byte[(int) myFile.length()];
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
                bis.read(mybytearray, 0, mybytearray.length);
                outToClient.write(mybytearray, 0, mybytearray.length);
                outToClient.flush();
                outToClient.close();
                connectionSocket.close();
                welcomeSocket.close();
                SocketThread socketThread=new SocketThread(new Socket("192.168.1.109", 7333),"finish");
                socketThread.run();

                return;
            }
        }

    }

}
