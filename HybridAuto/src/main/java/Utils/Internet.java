package Utils;

import com.google.common.net.InetAddresses;

import java.io.IOException;
import java.net.InetAddress;

public class Internet {
    public static boolean isConnected() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000);
        } catch (IOException e) {
            return false;
        }
    }
}
