import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
/**
 * Created by Sniper on 22/05/2015.
 */
public class Client {
    static Socket socket;
    static ServerSocket serverSocketftp;
    // to send data to server
    static BufferedWriter bufferedWriter;
    static ServerSocket serverSocket;
    public static void main(String[] arg) throws IOException
    {
        GUI f = new GUI();
        f.setVisible(true);
        SendToServer("getbooks");
        serverSocket=new ServerSocket(7222);
        while (true) {
            Socket socket = serverSocket.accept();
            // buffer it reads from server  , requires inputstreamreader  -> inputstream -> socket.getInputStream()  ,
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read;
            // read reading values from bufferreader
            read = bufferedReader.readLine();
            System.out.println(read);
            //Socket ftpSocket=serverSocketftp.accept();
            GUI.books.addElement(read);
        }
    }
    public static void SendToServer(String text) throws IOException {
        socket = new Socket("172.18.210.153", 7112);
        SocketThread se = new SocketThread(socket,text);
        new Thread(se).run();
        socket = new Socket("172.18.210.153", 7111);
        SocketThread re = new SocketThread(socket,text);
        new Thread(re).run();
    }
    public static void RecieveFile(String text)throws IOException{
        int filesize=2022386;
        int bytesRead;
        int currentTot = 0;
        Socket socket = new Socket("172.18.210.153", 7333);
        byte [] bytearray  = new byte [filesize];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(text);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bytesRead = is.read(bytearray,0,bytearray.length);
        currentTot = bytesRead;
        do {
            bytesRead =
                    is.read(bytearray, currentTot, (bytearray.length-currentTot));
            if(bytesRead >= 0) currentTot += bytesRead;
        } while(bytesRead > -1);
        bos.write(bytearray, 0 , currentTot);
        bos.flush();
        bos.close();
        socket.close();
    }
}

