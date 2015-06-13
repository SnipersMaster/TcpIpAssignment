import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static javax.swing.JOptionPane.*;

/**
 * Created by Sniper on 22/05/2015.
 */
public class Client {
    static Socket socket;
    static ServerSocket serverSocket;
    static int flowcontrol = 0;
    static String dire;
    public static void main(String[] arg) throws IOException
    {
        GUI f = new GUI();
        f.setVisible(true);
        SendToServer("getbooks");
        serverSocket=new ServerSocket(7333);

        while (true) {
            switch(flowcontrol) {
                case 0:
                    socket = serverSocket.accept();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String read;
                    read = bufferedReader.readLine();
                    if(read.equals("start")){flowcontrol = flowcontrol + 1;}
                    System.out.println(read);
                    if(!read.equals("start")&& !read.equals("finish")){GUI.books.addElement(read);}
                    if(read.equals("finish")){
                        showMessageDialog(null,"Finish","Finish",JOptionPane.INFORMATION_MESSAGE);}
                    dire = GUI.dir;
                    break;

                case 1:
                    Socket socket = new Socket(String.valueOf(IPs.SERVERIP), 7225);
                    if (socket.isConnected()){
                    byte[] aByte = new byte[1];
                    int bytesRead;
                    InputStream is = socket.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while(true){
                        if (is != null) {
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dire));
                            bytesRead = is.read(aByte, 0, aByte.length);
                            do {
                                baos.write(aByte);
                                bytesRead = is.read(aByte);
                            } while (bytesRead != -1);
                            bos.write(baos.toByteArray());
                            bos.flush();
                            bos.close();
                            socket.close();
                        }
                            flowcontrol=0;
                        break;
                        }
                    }}
            }

        }
    public static void SendToServer(String text) throws IOException {
        socket = new Socket(String.valueOf(IPs.SERVERIP), 7221);
        SocketThread se = new SocketThread(socket,text);
        new Thread(se).run();
    }
}

