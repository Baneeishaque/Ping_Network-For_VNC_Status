/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DK_win10-Asus_3ghz
 */
public class Check_Network_Get_Node_Names {

    public static void main(String[] args) {
        ArrayList<String> hosts = scanSubNet("192.168.1.");
    }

    private static ArrayList<String> scanSubNet(String subnet) {
        ArrayList<String> hosts = new ArrayList<>();

        InetAddress inetAddress;
        for (int i = 0; i < 255; i++) {
            System.out.println("Trying: " + subnet + String.valueOf(i));
            try {
                inetAddress = InetAddress.getByName(subnet + String.valueOf(i));
                if (inetAddress.isReachable(50)) {
                    hosts.add(inetAddress.getHostName());
                    System.out.println("Found : " + inetAddress.getHostName());
                }
            } catch (UnknownHostException e) {
                Logger.getLogger(Check_Network_Get_Node_Names.class.getName()).log(Level.SEVERE, null, e);
            } catch (IOException e) {
                Logger.getLogger(Check_Network_Get_Node_Names.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return hosts;
    }

}
