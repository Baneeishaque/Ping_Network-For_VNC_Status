/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK_win10-Asus_3ghz
 */
public class Check_VNC_Status_Demo {

    public static void main(String[] args) throws IOException {
        System.out.println(port_check("192.168.1.4", 5900));
    }

    static boolean port_check(String server_ip, int server_port) {


        /*try {
            InetAddress iAddress = InetAddress.getLocalHost();
            client_IP = iAddress.getHostAddress();
            System.out.println("Current IP address : " + client_IP);
        } catch (UnknownHostException e) {
        }*/
        try (Socket socket = new Socket(server_ip, server_port)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = br.readLine();
            System.out.println("server: " + response);
            if (response.equals("RFB 003.008")) {
                return true;
            }

        } catch (SocketException e) {
            System.out.println("Error: Unable to connect to server port ");
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Check_VNC_Status_Demo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;

    }

}
