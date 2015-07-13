package cl.inacap.unidad1.utils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

public class Utils {

	
	private static final String WIFI = "wifi";

	public static String wifiIpAddress(Context context) {
	    WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI);
	    int ipAddress = wifiManager.getConnectionInfo().getIpAddress();

	    if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
	        ipAddress = Integer.reverseBytes(ipAddress);
	    }

	    byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

	    String ipAddressString;
	    try {
	        ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
	    } catch (UnknownHostException ex) {
	        Log.e("WIFIIP", "No se puede obtener la ip.");
	        ipAddressString = "";
	    }

	    return ipAddressString;
	}	
	
}
