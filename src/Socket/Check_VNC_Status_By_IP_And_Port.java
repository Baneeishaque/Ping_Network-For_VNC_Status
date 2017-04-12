package Socket;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Check_VNC_Status_By_IP_And_Port {

    protected static String server_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5900;
    protected static String client_IP;

    public static void main(String[] args) throws IOException {
        int init = 0;

        /*try {
            InetAddress iAddress = InetAddress.getLocalHost();
            client_IP = iAddress.getHostAddress();
            System.out.println("Current IP address : " + client_IP);
        } catch (UnknownHostException e) {
        }*/
        try {
            Socket socket = new Socket(server_IP, SERVER_PORT);
            init = initialize(socket);

        } catch (SocketException e) {
            System.out.println("Error: Unable to connect to server port ");
        }

        if (init == 0) {
            System.out.println("error: Failed to initialize ");

        }

    }

    private static int initialize(Socket socket) throws IOException {
//        OutputStream os = socket.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        PrintWriter pw = new PrintWriter(os, true);

        System.out.println("server: " + br.readLine());
//        pw.println("192.343.34.321");
        socket.close();
        return 1;

    }

}
