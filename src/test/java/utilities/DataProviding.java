package utilities;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import setUp.BaseTest;

public class DataProviding extends BaseTest {
	
	public static String datasheet = "TestCases";
	//public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata1.xlsx");
	
	// to use data in a test case without using HashTable technique
	/*@DataProvider(name="data")
	public Object[][] getData(Method m)
	{
		String sheetname = m.getName();
		int rows = excel.getRowCount(sheetname);
		int cols = excel.getColumnCount(sheetname);
		
		System.out.println("SheetName : "+sheetname);
		System.out.println("Total number of Rows : "+rows);
		System.out.println("Total number of Columns : "+cols);
		
		//excel.getCellData(sheetname, 0, 2);   ---------- it brings data at first column of 2nd row -- first entry NAME(Kiran Arora)
		
		Object[][] data= new Object[rows-1][cols];
		
		/*data[0][0] = excel.getCellData(sheetname, 0, 2);
		data[0][1] = excel.getCellData(sheetname, 1, 2);
		data[0][2] = excel.getCellData(sheetname, 2, 2);
		
		data[1][0] = excel.getCellData(sheetname, 0, 3);
		data[1][1] = excel.getCellData(sheetname, 1, 3);
		data[1][2] = excel.getCellData(sheetname, 2, 3);   */
		
		/*for(int rownum=2;rownum<=rows;rownum++)
		{
			for(int colnum=0;colnum<cols;colnum++)
			{
				data[rownum-2][colnum]=excel.getCellData(sheetname, colnum, rownum);
			}
		}
		
		return data;
	}*/
	
	// to use data in a test case using HashTable technique
	@DataProvider(name="data", parallel=true)
	public Object[][] getData(Method m) {
		
		//ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");
		

		int totalRowsInSheet = excel.getRowCount(datasheet);
		System.out.println("Total Rows : " + totalRowsInSheet);

		String testName = m.getName();
		System.out.println("Test Name is : "+testName);

		int testCaseRowNum = 1;

		// to find the test case start row
		for (testCaseRowNum = 1; testCaseRowNum <= totalRowsInSheet; testCaseRowNum++) {
			String testCaseName = excel.getCellData(datasheet, 0, testCaseRowNum);
			if (testCaseName.equalsIgnoreCase(testName)) {
				break;

			}

		}
		System.out.println("Test Case starts from Row Number : " + testCaseRowNum);

		// to find total number of test rows in a testcase
		int dataStartRowNum = testCaseRowNum + 2; // (+2 is done as per format in sheet where data is present)
		int dataRows = 0;

		while (!excel.getCellData(datasheet, 0, dataStartRowNum + dataRows).equals("")) {
			dataRows++;
		}

		System.out.println("Total Data Rows in Test Case : " + dataRows);

		// to find total number of columns in a test case
		int ColumnStartColNum = testCaseRowNum + 1; // (+1 is done as per format in sheet where data is present)
		int dataColumns = 0;
		while (!excel.getCellData(datasheet, dataColumns, ColumnStartColNum).equals("")) {
			dataColumns++;
		}
		System.out.println("Total Data Columns in Test Case : " + dataColumns);

		// to fetch complete data in a test case without using HashTable technique		
		/*Object[][] data = new Object[dataRows][dataColumns];
		
		for (int rNum = dataStartRowNum; rNum <(dataStartRowNum + dataRows); rNum++) {
			for (int cNum = 0; cNum < dataColumns; cNum++) {
				//System.out.print(excel.getCellData(datasheet, cNum, rNum) + "\t");
				data[rNum-dataStartRowNum][cNum]=excel.getCellData(datasheet, cNum, rNum);
			}
			//System.out.println("");
		}
		
		return data;*/
		
		// to fetch complete data in a test case using HashTable technique
		Object[][] data = new Object[dataRows][1];
		
		int i=0;
		for (int rNum = dataStartRowNum; rNum <(dataStartRowNum + dataRows); rNum++) 
		{
			Hashtable<String,String> table = new Hashtable<String, String>();
			
			for (int cNum = 0; cNum < dataColumns; cNum++)
			{
				String testData = excel.getCellData(datasheet, cNum, rNum);
				String colName = excel.getCellData(datasheet, cNum, ColumnStartColNum);
				table.put(colName, testData);
				//System.out.print(excel.getCellData(datasheet, cNum, rNum) + "\t");
				//data[rNum-dataStartRowNum][cNum]=excel.getCellData(datasheet, cNum, rNum);
			}
			//System.out.println("");
			data[i][0] = table;
			i++;
		}
		
		return data;

	}

}
