package frameworkAPIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import io.restassured.response.Response;
import setUp.BaseTest;

public class DeleteCustomerAPI extends BaseTest{
	
	public static Response sendDeleteRequestToDeleteCustomerWithValidCustomerID(Hashtable<String,String> data)
	{
		
		Response response =	given().auth().basic(config.getProperty("validSecretKey"),"")
				            .delete(config.getProperty("CustomerAPIEndPoint")+"/"+data.get("id"));
		
		return response;
		
	}


}
