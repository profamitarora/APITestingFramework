package setUp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utilities.ExcelReader;

public class BaseTest {
	
	public static Properties config = new Properties();
	public FileInputStream fis;
	public ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	
	@BeforeSuite
	public void setUp() throws IOException
	{
		fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);
	
		RestAssured.baseURI=config.getProperty("baseURI");
		RestAssured.basePath=config.getProperty("basePath");
	}

	@AfterSuite
	public void tearDown()
	{
		
	}

}
