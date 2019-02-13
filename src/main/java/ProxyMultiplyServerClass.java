import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ProxyMultiplyServerClass {
    private static final int  SOCKET = 5000;
    private static ServerSocket server;
    private static Socket clientSocket;
    private static final String HOST_NAME= "localhost";
    public static void ConnectToClient() throws IOException {
        byte[] bytes = null;
        server = new ServerSocket(SOCKET);
        clientSocket = server.accept();
        InputStream input= clientSocket.getInputStream();
        input.read(bytes);
        ByteBuffer buf2 = ByteBuffer.wrap(bytes);
        double[] arrDouble = new double[bytes.length / 8];
        for (int i = 0; i < arrDouble.length; i++) {
            arrDouble[i] = buf2.getDouble(i * 8);
        }

        double result = arrDouble[0] * arrDouble[1];
        OutputStream outStream = clientSocket.getOutputStream();
        byte[] byteResult = new byte[8];
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        buf.putDouble(result);
        outStream.write(byteResult);
    }
}
