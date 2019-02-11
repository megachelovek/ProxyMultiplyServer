import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ProxyMultiplyServerClass {
    private static final int  SOCKET = 5000;
    private static final String HOST_NAME= "localhost";
    public static void ConnectToSetver() throws IOException {
        byte[] bytes = null;
        Socket socket = new Socket(HOST_NAME,SOCKET);
        InputStream input= socket.getInputStream();
        input.read(bytes);
        ByteBuffer buf2 = ByteBuffer.wrap(bytes);
        double[] arrDouble = new double[bytes.length / 8];
        for (int i = 0; i < arrDouble.length; i++) {
            arrDouble[i] = buf2.getDouble(i * 8);
        }

        double result = arrDouble[0] * arrDouble[1];
        OutputStream outStream = socket.getOutputStream();
        byte[] byteResult = new byte[8];
        ByteBuffer buf = ByteBuffer.wrap(bytes);
            buf.putDouble(result);
        outStream.write(byteResult);
    }
}
