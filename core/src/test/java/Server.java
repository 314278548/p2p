import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 5000);
        try(Scanner scanner = new Scanner(System.in); OutputStream o = socket.getOutputStream()){
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println("客户端"+":"+next);
            byte[] bytes = next.getBytes("utf-8");
            socket.getOutputStream().write(bytes);
        }}
    }
}
