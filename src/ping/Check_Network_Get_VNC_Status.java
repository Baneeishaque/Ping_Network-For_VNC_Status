/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK_win10-Asus_3ghz
 */
public class Check_Network_Get_VNC_Status {

    static HashMap<String, Boolean> status = new HashMap<>();

    public static void main(String[] args) {
        HashMap<String, String> devices = scanSubNet("192.168.1.2");
        System.out.println("\nDevices");

        /* Display content using Iterator*/
        Set set = devices.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
        
        System.out.println("\nStatus");
        /* Display content using Iterator*/
        set = status.entrySet();
        iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
        }
    }

    private static HashMap<String, String> scanSubNet(String YourPhoneIPAddress) {
        /* This is how to declare HashMap */
        HashMap<String, String> hosts = new HashMap<>();

        String subnet = YourPhoneIPAddress.substring(0, YourPhoneIPAddress.lastIndexOf("."));

        InetAddress inetAddress;
        for (int i = 0; i <= 255; i++) {

            String checking_ip = subnet + "." + String.valueOf(i);
            /*if (checking_ip.equals(YourPhoneIPAddress)) {
                continue;
            }*/
            System.out.println("Trying: " + checking_ip);

            try {
                inetAddress = InetAddress.getByName(checking_ip);

                if (inetAddress.isReachable(50)) {
                    hosts.put(checking_ip, inetAddress.getHostName());
                    status.put(checking_ip, port_check(checking_ip, 5900));
                    System.out.println("Found : " + inetAddress);
                }
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }

        return hosts;
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
            Logger.getLogger(Check_Network_Get_VNC_Status.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Check_Network_Get_VNC_Status.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;

    }

}
