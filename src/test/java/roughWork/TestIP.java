package roughWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestIP{

	public static void main(String[] args) throws UnknownHostException {
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		String address = "http://"+ip+":8080/job/ApiTestingFramework%20-%20GIT/EXTENT_20HTML_20Report/";

	}

}
