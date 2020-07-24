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
public class Check_Network_Get_IPs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<InetAddress> devices = getConnectedDevices("192.168.1.4");

        System.out.println("\nDevices");
//        System.out.println(devices);
        devices.forEach((item) -> {
            System.out.println(item.toString().replace("/", ""));
        });

    }

    public static ArrayList<InetAddress> getConnectedDevices(String YourPhoneIPAddress) {

        System.out.println("Your IP : " + YourPhoneIPAddress);
        ArrayList<InetAddress> ret = new ArrayList<>();

        String[] myIPArray = YourPhoneIPAddress.split("\\.");
        InetAddress currentPingAddr;
        //Check IP Address 0 - Is it System?
        //Check IP Address 1, Discovered (modem, PC, Phone) - Is it System?
        //Check IP Address 255 - Is it System?
        for (int i = 0; i <= 255; i++) {
            try {

                // build the next IP address
                currentPingAddr = InetAddress.getByName(myIPArray[0] + "."
                        + myIPArray[1] + "."
                        + myIPArray[2] + "."
                        + Integer.toString(i));
                System.out.println("Checking IP : " + currentPingAddr.toString().replace("/", ""));
                if (currentPingAddr.equals(InetAddress.getByName(YourPhoneIPAddress))) {
                    continue;
                }
                // 50ms Timeout for the "ping"
                if (currentPingAddr.isReachable(50)) {

                    ret.add(currentPingAddr);
                    System.out.println("Discovered IP : " + currentPingAddr.toString().replace("/", ""));
                }
            } catch (UnknownHostException ex) {
                Logger.getLogger(Check_Network_Get_IPs.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Check_Network_Get_IPs.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return ret;
    }

}
