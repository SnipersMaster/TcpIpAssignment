import java.io.*;
import java.net.Socket;

/**
 * Created by androiddevelopment on 5/24/15.
 */
public class SocketThread implements Runnable {
    protected Socket clientSocket = null;
    protected String serverText   = null;

    public SocketThread(Socket clientSocket, String sendtext) {
        this.clientSocket = clientSocket;
        this.serverText = sendtext;
    }

    @Override
    public void run() {
        try {

            OutputStream outputStream = this.clientSocket.getOutputStream();
            // bufferwriter it sends to the server ->OutputStreamWriter -> OutputStream -> socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            // to send
            bufferedWriter.write(this.serverText);
            // to refresh
            bufferedWriter.flush();
            // close
            this.clientSocket.close();

        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
