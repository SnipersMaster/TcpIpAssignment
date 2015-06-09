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
    static ServerSocket ftpserver;
    static Socket bookSocket;
    static int sendlock;
    public static void main(String[] arg) throws IOException {


        bookserverSocket = new ServerSocket();
    //    ftpserver = new ServerSocket(7112);

//        ServerPool serverPool = new ServerPool(bookserverSocket);
//        ServerPool serverPool2 = new ServerPool(ftpserver);
//
//        ServerPool[] serverPools = {serverPool, serverPool2};
//        for (int i = 0; i < serverPools.length; i++)
//        {
//            Thread thread =new Thread(serverPools[i]);
//            thread.run();
//        }


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

                                SocketThread socketThread=new SocketThread(new Socket("172.18.213.165", 7222),filePath.getFileName().toString());
                                socketThread.run();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }else if(!read.contains("getbooks"))
                {

                    SendFiletoClient(read);
                    System.out.println("the book is : "+read);
                }
        }

    // first step to get all the files in the books path

    }

    public static void SendFiletoClient(String filename) throws IOException
    {   Socket socket = new Socket("172.16.15.191", 7333);
        File file=new File("Books/"+filename);
        byte[] filearray=new byte[(int)file.length()];
        FileInputStream fileInputStream=new FileInputStream(file);
        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
        bufferedInputStream.read(filearray,0,filearray.length);
        OutputStream outputStream=socket.getOutputStream();
        System.out.println("sending file");
        outputStream.write(filearray,0,filearray.length);
        outputStream.flush();
        socket.close();
        System.out.println("File Transfer complete");
    }

}
