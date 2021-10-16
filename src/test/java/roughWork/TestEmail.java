package roughWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilities.MonitoringMail;
import utilities.TestConfig;

public class TestEmail {
	
	static String messageBody;

	public static void main(String[] args) throws AddressException, MessagingException, UnknownHostException {
		
		String ip = InetAddress.getLocalHost().getHostAddress();
		String messageBody = "http://"+ip+":8080/job/ApiTestingFramework%20-%20GIT/EXTENT_20HTML_20Report/";
		
		MonitoringMail mail = new MonitoringMail();
		
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);

	}

}
