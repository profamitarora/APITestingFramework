package frameworkAPIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import io.restassured.response.Response;
import setUp.BaseTest;

public class CreateCustomerAPI extends BaseTest{
	
	public static Response sendPostRequestToCreateCustomerWithValidSecretKey(Hashtable<String,String> data)
	{
		
		Response response =	given().auth().basic(config.getProperty("validSecretKey"),"")
	            .formParams("name",data.get("name")).formParams("email",data.get("email"))
	            .formParams("description",data.get("description")).post(config.getProperty("CustomerAPIEndPoint"));
		
		return response;
		
	}
	
	public static Response sendPostRequestToCreateCustomerWithInvalidSecretKey(Hashtable<String,String> data)
	{
		Response response =	given().auth().basic(config.getProperty("invalidSecretKey"),"")
	            .formParams("name",data.get("name")).formParams("email",data.get("email"))
	            .formParams("description",data.get("description")).post(config.getProperty("CustomerAPIEndPoint"));
		
		return response;
		
	}


}
