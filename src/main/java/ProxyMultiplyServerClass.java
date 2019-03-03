import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ProxyMultiplyServerClass {
    private static final int  SOCKET = 5000;
    private static ServerSocket server;
    private static Socket clientSocket;
    private static final String HOST_NAME= "localhost";
    public static void ConnectToClient() throws IOException {
        Gson gson = new Gson();
        String data;
        server = new ServerSocket(SOCKET);
        clientSocket = server.accept();
        PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while ((data = br.readLine()) != null) {
            double[] arrDouble = gson.fromJson(data,double[].class);
            double result = arrDouble[0] *arrDouble[1];
            String json = gson.toJson(result);
            pw.println(json);
        }

        pw.close();
        br.close();

        clientSocket.close();
    }
}
