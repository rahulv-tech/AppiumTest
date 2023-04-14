package Practise.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="InputData")
	public static Object[][] getDataForEditField()
	
	{
		String s="Hello";
		String s1="234@#$";
		//2 sets of data "hello" and "23423@#$5"
		Object [][] obj= new Object[][]
				
				{
			
			{s},{s1}
				};
				return obj;
		
		
	}

}
