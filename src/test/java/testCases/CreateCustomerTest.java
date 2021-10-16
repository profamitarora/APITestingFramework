package testCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkAPIs.CreateCustomerAPI;
import io.restassured.response.Response;
import setUp.BaseTest;
import utilities.DataProviding;

public class CreateCustomerTest extends BaseTest {
	
	//TEST CASES WITHOUT USING HASHTABLE AND AUTO-EXCEL DRIVEN TECHNIQUE 
	/*@Test(dataProviderClass=DataProviding.class,dataProvider ="data")
	public void validCreateCustomerAPI(String name, String email, String desc)
	{
		Response response =	given().auth().basic(config.getProperty("validSecretKey"),"")
				            .formParams("name",name).formParams("email",email).formParams("description",desc)
				            .post(config.getProperty("CustomerAPIEndPoint"));
	
		response.prettyPrint();
		int statusCodeReceived = response.getStatusCode();
		System.out.println(statusCodeReceived);
		Assert.assertEquals(statusCodeReceived, 200);
	}
	
	@Test(dataProviderClass=DataProviding.class,dataProvider ="data")
	public void invalidCreateCustomerAPI(String name, String email, String desc)
	{
		Response response =	given().auth().basic(config.getProperty("invalidSecretKey"),"")
				            .formParams("name",name).formParams("email",email).formParams("description",desc)
	    				    .post(config.getProperty("CustomerAPIEndPoint"));
		
		response.prettyPrint();
		int statusCodeReceived = response.getStatusCode();
		System.out.println(statusCodeReceived);
		Assert.assertEquals(statusCodeReceived,200);
	}*/
	
	//TEST CASES USING HASHTABLE AND AUTO-EXCEL DRIVEN TECHNIQUE 
	@Test(dataProviderClass=DataProviding.class,dataProvider ="data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data)
	{
		Response response =	CreateCustomerAPI.sendPostRequestToCreateCustomerWithValidSecretKey(data);
	
		response.prettyPrint();
		int statusCodeReceived = response.getStatusCode();
		System.out.println(statusCodeReceived);
		Assert.assertEquals(statusCodeReceived, 200);
	}
	
	@Test(dataProviderClass=DataProviding.class,dataProvider ="data")
	public void validateCreateCustomerAPIWithInvalidSecretKey(Hashtable<String,String> data)
	{
		Response response =	CreateCustomerAPI.sendPostRequestToCreateCustomerWithInvalidSecretKey(data);
		
		response.prettyPrint();
		int statusCodeReceived = response.getStatusCode();
		System.out.println(statusCodeReceived);
		Assert.assertEquals(statusCodeReceived,200);
	}
	

}
