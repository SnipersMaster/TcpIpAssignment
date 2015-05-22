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
    // to send data to server
    static BufferedWriter bufferedWriter;

    static ServerSocket serverSocket;
    public static void main(String[] arg) throws IOException
    {
        socket = new Socket("localhost", 7169);
        serverSocket=new ServerSocket(7170);
        while (true) {
            Socket socket = serverSocket.accept();
            // buffer it reads from server  , requires inputstreamreader  -> inputstream -> socket.getInputStream()  ,
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read;
            // read reading values from bufferreader
            read = bufferedReader.readLine();
            System.out.println(read);
        }

    }
    public static void SendToServer(String text) throws IOException {

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
