package roughWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP{

	public static void main(String[] args) throws UnknownHostException {
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		System.out.println(ip);
		String address = "http://"+ip;

	}

}
