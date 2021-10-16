package testCases;


import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import frameworkAPIs.DeleteCustomerAPI;
import io.restassured.response.Response;
import setUp.BaseTest;
import utilities.DataProviding;
import utilities.TestAssertion;

public class DeleteCustomerTest extends BaseTest {
	
	//TEST CASES WITHOUT USING HASHTABLE AND AUTO-EXCEL DRIVEN TECHNIQUE 
	// --- check CreateCustomerAPI test for complete reference of above line -- Not valid for this test
	
	//TEST CASES USING HASHTABLE AND AUTO-EXCEL DRIVEN TECHNIQUE 
	@Test(dataProviderClass=DataProviding.class,dataProvider ="data")
	public void validateDeleteCustomerAPIWithValidCustomerID(Hashtable<String,String> data)
	{
		Response response =	DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerWithValidCustomerID(data);
	
		response.prettyPrint();
		int statusCodeReceived = response.getStatusCode();
		System.out.println(statusCodeReceived);
		Assert.assertEquals(statusCodeReceived, 200);
		
		/*String expected = data.get("id");
		String actual_id = response.jsonPath().get("id");
		
		Assert.assertEquals(actual_id, expected, "ID does not match"); */
		
		//one way to test id ID is present
		/*JSONObject json = new JSONObject(response.asString());
		String actual_id = json.get("id").toString();
		String expected = data.get("id");
		Assert.assertTrue(json.has("id"),"ID is not present"); */
		
		System.out.println("Presence check for Object key in response : "+TestAssertion.jsonHasKey(response.asString(),"object"));
		System.out.println("Presence check for Deleted key in response : "+TestAssertion.jsonHasKey(response.asString(),"deleted"));
		//another to test if ID is present -- TestAssertion class created with method 'jsonHasKey' -- common utility
		Assert.assertTrue(TestAssertion.jsonHasKey(response.asString(),"id"),"Does not has ID");
		
		//another METHOD in TestAssertion to get ID as string and compare it with expected -- common utility
		String actual_id = TestAssertion.getJsonKeyValue(response.asString(), "id");
		String expected = data.get("id");
		Assert.assertEquals(actual_id, expected, "ID does not match");
		System.out.println("Object key value in response : "+TestAssertion.getJsonKeyValue(response.asString(),"object"));
		System.out.println("deleted key value in response : "+TestAssertion.getJsonKeyValue(response.asString(),"deleted"));
	}
	

}
