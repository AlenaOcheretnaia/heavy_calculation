import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket servSocket = new ServerSocket(9999);

        while (true) {
            try (Socket socket = servSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                while (((line = in.readLine()) != null) && (!line.equals("end"))) {
                    int num = Integer.parseInt(line);
                    if (num > 0) {
                        int answer = fiboNum(num);
                        out.println(line + " fibonacci number is " + answer);
                    } else {
                        out.println("There is no such fibonacci number");
                    }

                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static int fiboNum(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fiboNum(n - 1) + fiboNum(n - 2);
        }
    }
}
